package com.ecommerce.productservice.service;

import com.ecommerce.productservice.document.ProductDocument;
import com.ecommerce.productservice.dto.request.ProductSearchRequest;
import com.ecommerce.productservice.dto.response.PageResponse;
import com.ecommerce.productservice.repository.ProductSearchRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductSearchService {

    private final ProductSearchRepository productSearchRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    public PageResponse<ProductDocument> searchProducts(ProductSearchRequest request) {
        log.info("Searching products with keyword: {}", request.getKeyword());

        int page = request.getPage() != null ? request.getPage() : 0;
        int size = request.getSize() != null ? request.getSize() : 20;
        String sortBy = request.getSortBy() != null ? request.getSortBy() : "createdAt";
        String sortDirection = request.getSortDirection() != null ? request.getSortDirection() : "desc";

        Sort sort = sortDirection.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<ProductDocument> resultPage;

        // Simple search by keyword
        if (request.getKeyword() != null && !request.getKeyword().isEmpty()) {
            resultPage = productSearchRepository.findByNameContainingOrDescriptionContaining(
                    request.getKeyword(), request.getKeyword(), pageable);
        }
        // Filter by category
        else if (request.getCategoryId() != null) {
            resultPage = productSearchRepository.findByCategoryId(
                    request.getCategoryId().toString(), pageable);
        }
        // Filter by brand
        else if (request.getBrandId() != null) {
            resultPage = productSearchRepository.findByBrandId(
                    request.getBrandId().toString(), pageable);
        }
        // Filter by price range
        else if (request.getMinPrice() != null && request.getMaxPrice() != null) {
            resultPage = productSearchRepository.findByPriceBetween(
                    request.getMinPrice(), request.getMaxPrice(), pageable);
        }
        // Get featured products
        else if (Boolean.TRUE.equals(request.getFeatured())) {
            resultPage = productSearchRepository.findByFeaturedTrue(pageable);
        }
        // Get all published products
        else {
            resultPage = productSearchRepository.findByPublishedTrue(pageable);
        }

        return buildPageResponse(resultPage);
    }

    public PageResponse<ProductDocument> searchProductsAdvanced(String keyword, int page, int size) {
        log.info("Advanced search for: {}", keyword);

        Pageable pageable = PageRequest.of(page, size);

        // This is a simplified version - you can enhance with more complex queries
        Page<ProductDocument> resultPage = productSearchRepository
                .findByNameContainingOrDescriptionContaining(keyword, keyword, pageable);

        return buildPageResponse(resultPage);
    }

    private PageResponse<ProductDocument> buildPageResponse(Page<ProductDocument> documentPage) {
        return PageResponse.<ProductDocument>builder()
                .content(documentPage.getContent())
                .page(documentPage.getNumber())
                .size(documentPage.getSize())
                .totalElements(documentPage.getTotalElements())
                .totalPages(documentPage.getTotalPages())
                .last(documentPage.isLast())
                .first(documentPage.isFirst())
                .build();
    }
}

