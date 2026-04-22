package com.ecommerce.productservice.mapper;

import com.ecommerce.productservice.dto.request.CreateProductVariantRequest;
import com.ecommerce.productservice.dto.response.ProductVariantResponse;
import com.ecommerce.productservice.entity.ProductVariant;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-22T23:39:33+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (Red Hat, Inc.)"
)
@Component
public class ProductVariantMapperImpl implements ProductVariantMapper {

    @Override
    public ProductVariant toEntity(CreateProductVariantRequest request) {
        if ( request == null ) {
            return null;
        }

        ProductVariant.ProductVariantBuilder productVariant = ProductVariant.builder();

        productVariant.sku( request.getSku() );
        productVariant.size( request.getSize() );
        productVariant.color( request.getColor() );
        productVariant.material( request.getMaterial() );
        productVariant.style( request.getStyle() );
        productVariant.stockQuantity( request.getStockQuantity() );
        productVariant.priceAdjustment( request.getPriceAdjustment() );
        productVariant.imageUrl( request.getImageUrl() );
        productVariant.weight( request.getWeight() );
        productVariant.barcode( request.getBarcode() );
        productVariant.available( request.getAvailable() );

        return productVariant.build();
    }

    @Override
    public ProductVariantResponse toResponse(ProductVariant variant) {
        if ( variant == null ) {
            return null;
        }

        ProductVariantResponse.ProductVariantResponseBuilder productVariantResponse = ProductVariantResponse.builder();

        productVariantResponse.id( variant.getId() );
        productVariantResponse.sku( variant.getSku() );
        productVariantResponse.size( variant.getSize() );
        productVariantResponse.color( variant.getColor() );
        productVariantResponse.material( variant.getMaterial() );
        productVariantResponse.style( variant.getStyle() );
        productVariantResponse.stockQuantity( variant.getStockQuantity() );
        productVariantResponse.priceAdjustment( variant.getPriceAdjustment() );
        productVariantResponse.imageUrl( variant.getImageUrl() );
        productVariantResponse.weight( variant.getWeight() );
        productVariantResponse.barcode( variant.getBarcode() );
        productVariantResponse.available( variant.getAvailable() );
        productVariantResponse.createdAt( variant.getCreatedAt() );
        productVariantResponse.updatedAt( variant.getUpdatedAt() );

        return productVariantResponse.build();
    }

    @Override
    public List<ProductVariantResponse> toResponseList(List<ProductVariant> variants) {
        if ( variants == null ) {
            return null;
        }

        List<ProductVariantResponse> list = new ArrayList<ProductVariantResponse>( variants.size() );
        for ( ProductVariant productVariant : variants ) {
            list.add( toResponse( productVariant ) );
        }

        return list;
    }
}
