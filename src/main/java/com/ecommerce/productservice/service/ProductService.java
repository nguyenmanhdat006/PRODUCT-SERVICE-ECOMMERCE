package com.ecommerce.productservice.service;

import com.ecommerce.productservice.document.ProductDocument;
import com.ecommerce.productservice.dto.request.CreateProductImageRequest;
import com.ecommerce.productservice.dto.request.CreateProductRequest;
import com.ecommerce.productservice.dto.request.CreateProductVariantRequest;
import com.ecommerce.productservice.dto.response.PageResponse;
import com.ecommerce.productservice.dto.response.ProductResponse;
import com.ecommerce.productservice.entity.*;
import com.ecommerce.productservice.exception.BadRequestException;
import com.ecommerce.productservice.exception.ResourceNotFoundException;
import com.ecommerce.productservice.mapper.*;
import com.ecommerce.productservice.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ProductImageRepository productImageRepository;
    private final ProductVariantRepository productVariantRepository;
    private final Optional<ProductSearchRepository> productSearchRepository;

    private final ProductMapper productMapper;
    private final ProductImageMapper productImageMapper;
    private final ProductVariantMapper productVariantMapper;
    private final ProductDocumentMapper productDocumentMapper;

    public ProductResponse createProduct(CreateProductRequest request) {
        log.info("Creating product: {}", request.getName());

        // Validate slug uniqueness
        if (request.getSlug() != null && productRepository.existsBySlug(request.getSlug())) {
            throw new BadRequestException("Product slug already exists: " + request.getSlug());
        }

        Product product = productMapper.toEntity(request);

        // Set category
        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category", "id", request.getCategoryId()));
            product.setCategory(category);
        }

        // Set brand
        if (request.getBrandId() != null) {
            Brand brand = brandRepository.findById(request.getBrandId())
                    .orElseThrow(() -> new ResourceNotFoundException("Brand", "id", request.getBrandId()));
            product.setBrand(brand);
        }

        // Set published date if published
        if (Boolean.TRUE.equals(request.getPublished())) {
            product.setPublishedAt(LocalDateTime.now());
        }

        Product savedProduct = productRepository.save(product);

        // Add images
        if (request.getImages() != null && !request.getImages().isEmpty()) {
            for (CreateProductImageRequest imageRequest : request.getImages()) {
                ProductImage image = productImageMapper.toEntity(imageRequest);
                savedProduct.addImage(image);
            }
        }

        // Add variants
        if (request.getVariants() != null && !request.getVariants().isEmpty()) {
            for (CreateProductVariantRequest variantRequest : request.getVariants()) {
                // Validate SKU uniqueness
                if (productVariantRepository.existsBySku(variantRequest.getSku())) {
                    throw new BadRequestException("Variant SKU already exists: " + variantRequest.getSku());
                }
                ProductVariant variant = productVariantMapper.toEntity(variantRequest);
                savedProduct.addVariant(variant);
            }
        }

        savedProduct = productRepository.save(savedProduct);

        // Sync to Elasticsearch
        syncToElasticsearch(savedProduct);

        log.info("Product created successfully with ID: {}", savedProduct.getId());
        return productMapper.toResponse(savedProduct);
    }

    @Transactional(readOnly = true)
    public ProductResponse getProductById(UUID id) {
        Product product = productRepository.findByIdWithDetails(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
        return productMapper.toResponse(product);
    }

    @Transactional(readOnly = true)
    public ProductResponse getProductBySlug(String slug) {
        Product product = productRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "slug", slug));
        return productMapper.toResponse(product);
    }

    @Transactional(readOnly = true)
    public PageResponse<ProductResponse> getAllProducts(int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productPage = productRepository.findAll(pageable);

        return buildPageResponse(productPage);
    }

    @Transactional(readOnly = true)
    public PageResponse<ProductResponse> getPublishedProducts(int page, int size, String sortBy, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Product> productPage = productRepository.findByPublishedTrue(pageable);

        return buildPageResponse(productPage);
    }

    @Transactional(readOnly = true)
    public PageResponse<ProductResponse> getProductsByCategory(UUID categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findPublishedByCategoryId(categoryId, pageable);
        return buildPageResponse(productPage);
    }

    @Transactional(readOnly = true)
    public PageResponse<ProductResponse> getProductsByBrand(UUID brandId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findPublishedByBrandId(brandId, pageable);
        return buildPageResponse(productPage);
    }

    @Transactional(readOnly = true)
    public PageResponse<ProductResponse> getFeaturedProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findByFeaturedTrue(pageable);
        return buildPageResponse(productPage);
    }

    @Transactional(readOnly = true)
    public PageResponse<ProductResponse> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> productPage = productRepository.findByPriceRange(minPrice, maxPrice, pageable);
        return buildPageResponse(productPage);
    }

    public ProductResponse updateProduct(UUID id, CreateProductRequest request) {
        log.info("Updating product: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        // Validate slug uniqueness if changed
        if (request.getSlug() != null && !request.getSlug().equals(product.getSlug())) {
            if (productRepository.existsBySlug(request.getSlug())) {
                throw new BadRequestException("Product slug already exists: " + request.getSlug());
            }
        }

        productMapper.updateEntityFromRequest(request, product);

        // Update category
        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category", "id", request.getCategoryId()));
            product.setCategory(category);
        }

        // Update brand
        if (request.getBrandId() != null) {
            Brand brand = brandRepository.findById(request.getBrandId())
                    .orElseThrow(() -> new ResourceNotFoundException("Brand", "id", request.getBrandId()));
            product.setBrand(brand);
        }

        // Update published date
        if (Boolean.TRUE.equals(request.getPublished()) && product.getPublishedAt() == null) {
            product.setPublishedAt(LocalDateTime.now());
        }

        Product updatedProduct = productRepository.save(product);

        // Sync to Elasticsearch
        syncToElasticsearch(updatedProduct);

        log.info("Product updated successfully: {}", id);
        return productMapper.toResponse(updatedProduct);
    }

    public void deleteProduct(UUID id) {
        log.info("Deleting product: {}", id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));

        productRepository.delete(product);

        // Remove from Elasticsearch
        productSearchRepository.ifPresent(repo -> {
            try {
                repo.deleteById(id.toString());
            } catch (Exception e) {
                log.error("Failed to remove product from Elasticsearch: {}", id, e);
            }
        });

        log.info("Product deleted successfully: {}", id);
    }

    public void syncAllProductsToElasticsearch() {
        if (productSearchRepository.isEmpty()) {
            log.info("Skipping Elasticsearch sync because search is disabled");
            return;
        }

        log.info("Syncing all products to Elasticsearch");

        List<Product> products = productRepository.findAll();
        List<ProductDocument> documents = productDocumentMapper.toDocumentList(products);
        productSearchRepository.get().saveAll(documents);

        log.info("Synced {} products to Elasticsearch", products.size());
    }

    private void syncToElasticsearch(Product product) {
        if (productSearchRepository.isEmpty()) {
            return;
        }

        try {
            ProductDocument document = productDocumentMapper.toDocument(product);
            productSearchRepository.get().save(document);
            log.debug("Product synced to Elasticsearch: {}", product.getId());
        } catch (Exception e) {
            log.error("Failed to sync product to Elasticsearch: {}", product.getId(), e);
        }
    }

    private PageResponse<ProductResponse> buildPageResponse(Page<Product> productPage) {
        List<ProductResponse> content = productMapper.toResponseList(productPage.getContent());

        return PageResponse.<ProductResponse>builder()
                .content(content)
                .page(productPage.getNumber())
                .size(productPage.getSize())
                .totalElements(productPage.getTotalElements())
                .totalPages(productPage.getTotalPages())
                .last(productPage.isLast())
                .first(productPage.isFirst())
                .build();
    }
}

