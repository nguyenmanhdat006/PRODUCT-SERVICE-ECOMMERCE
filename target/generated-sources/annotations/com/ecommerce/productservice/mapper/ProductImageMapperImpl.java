package com.ecommerce.productservice.mapper;

import com.ecommerce.productservice.dto.request.CreateProductImageRequest;
import com.ecommerce.productservice.dto.response.ProductImageResponse;
import com.ecommerce.productservice.entity.ProductImage;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-04T09:25:51+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Microsoft)"
)
@Component
public class ProductImageMapperImpl implements ProductImageMapper {

    @Override
    public ProductImage toEntity(CreateProductImageRequest request) {
        if ( request == null ) {
            return null;
        }

        ProductImage.ProductImageBuilder productImage = ProductImage.builder();

        productImage.imageUrl( request.getImageUrl() );
        productImage.altText( request.getAltText() );
        productImage.isPrimary( request.getIsPrimary() );
        productImage.displayOrder( request.getDisplayOrder() );

        return productImage.build();
    }

    @Override
    public ProductImageResponse toResponse(ProductImage image) {
        if ( image == null ) {
            return null;
        }

        ProductImageResponse.ProductImageResponseBuilder productImageResponse = ProductImageResponse.builder();

        productImageResponse.id( image.getId() );
        productImageResponse.imageUrl( image.getImageUrl() );
        productImageResponse.altText( image.getAltText() );
        productImageResponse.isPrimary( image.getIsPrimary() );
        productImageResponse.displayOrder( image.getDisplayOrder() );
        productImageResponse.createdAt( image.getCreatedAt() );

        return productImageResponse.build();
    }

    @Override
    public List<ProductImageResponse> toResponseList(List<ProductImage> images) {
        if ( images == null ) {
            return null;
        }

        List<ProductImageResponse> list = new ArrayList<ProductImageResponse>( images.size() );
        for ( ProductImage productImage : images ) {
            list.add( toResponse( productImage ) );
        }

        return list;
    }
}
