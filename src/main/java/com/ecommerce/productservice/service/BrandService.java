package com.ecommerce.productservice.service;

import com.ecommerce.productservice.dto.request.CreateBrandRequest;
import com.ecommerce.productservice.dto.response.BrandResponse;
import com.ecommerce.productservice.entity.Brand;
import com.ecommerce.productservice.exception.BadRequestException;
import com.ecommerce.productservice.exception.ResourceNotFoundException;
import com.ecommerce.productservice.mapper.BrandMapper;
import com.ecommerce.productservice.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    public BrandResponse createBrand(CreateBrandRequest request) {
        log.info("Creating brand: {}", request.getName());

        // Validate slug uniqueness
        if (request.getSlug() != null && brandRepository.existsBySlug(request.getSlug())) {
            throw new BadRequestException("Brand slug already exists: " + request.getSlug());
        }

        Brand brand = brandMapper.toEntity(request);
        Brand savedBrand = brandRepository.save(brand);

        log.info("Brand created successfully with ID: {}", savedBrand.getId());
        return brandMapper.toResponse(savedBrand);
    }

    @Transactional(readOnly = true)
    public BrandResponse getBrandById(UUID id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand", "id", id));
        return brandMapper.toResponse(brand);
    }

    @Transactional(readOnly = true)
    public BrandResponse getBrandBySlug(String slug) {
        Brand brand = brandRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Brand", "slug", slug));
        return brandMapper.toResponse(brand);
    }

    @Transactional(readOnly = true)
    public List<BrandResponse> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        return brandMapper.toResponseList(brands);
    }

    @Transactional(readOnly = true)
    public List<BrandResponse> getActiveBrands() {
        List<Brand> brands = brandRepository.findByActiveTrue();
        return brandMapper.toResponseList(brands);
    }

    public BrandResponse updateBrand(UUID id, CreateBrandRequest request) {
        log.info("Updating brand: {}", id);

        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand", "id", id));

        // Validate slug uniqueness if changed
        if (request.getSlug() != null && !request.getSlug().equals(brand.getSlug())) {
            if (brandRepository.existsBySlug(request.getSlug())) {
                throw new BadRequestException("Brand slug already exists: " + request.getSlug());
            }
        }

        brandMapper.updateEntityFromRequest(request, brand);
        Brand updatedBrand = brandRepository.save(brand);

        log.info("Brand updated successfully: {}", id);
        return brandMapper.toResponse(updatedBrand);
    }

    public void deleteBrand(UUID id) {
        log.info("Deleting brand: {}", id);

        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand", "id", id));

        brandRepository.delete(brand);
        log.info("Brand deleted successfully: {}", id);
    }
}

