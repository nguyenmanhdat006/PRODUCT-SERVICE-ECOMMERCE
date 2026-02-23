package com.ecommerce.productservice.controller;

import com.ecommerce.productservice.document.ProductDocument;
import com.ecommerce.productservice.dto.request.ProductSearchRequest;
import com.ecommerce.productservice.dto.response.ApiResponse;
import com.ecommerce.productservice.dto.response.PageResponse;
import com.ecommerce.productservice.service.ProductSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductSearchController {

    private final ProductSearchService productSearchService;

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<PageResponse<ProductDocument>>> searchProducts(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String categoryId,
            @RequestParam(required = false) String brandId,
            @RequestParam(required = false) String minPrice,
            @RequestParam(required = false) String maxPrice,
            @RequestParam(required = false) Boolean featured,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDirection) {

        ProductSearchRequest request = ProductSearchRequest.builder()
                .keyword(keyword)
                .categoryId(categoryId != null ? java.util.UUID.fromString(categoryId) : null)
                .brandId(brandId != null ? java.util.UUID.fromString(brandId) : null)
                .minPrice(minPrice != null ? new java.math.BigDecimal(minPrice) : null)
                .maxPrice(maxPrice != null ? new java.math.BigDecimal(maxPrice) : null)
                .featured(featured)
                .page(page)
                .size(size)
                .sortBy(sortBy)
                .sortDirection(sortDirection)
                .build();

        PageResponse<ProductDocument> response = productSearchService.searchProducts(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}

