package com.ecommerce.productservice.mapper;

import com.ecommerce.productservice.dto.request.CreateProductVariantRequest;
import com.ecommerce.productservice.dto.response.ProductVariantResponse;
import com.ecommerce.productservice.entity.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductVariantMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "product", ignore = true)
    ProductVariant toEntity(CreateProductVariantRequest request);

    ProductVariantResponse toResponse(ProductVariant variant);

    List<ProductVariantResponse> toResponseList(List<ProductVariant> variants);
}

