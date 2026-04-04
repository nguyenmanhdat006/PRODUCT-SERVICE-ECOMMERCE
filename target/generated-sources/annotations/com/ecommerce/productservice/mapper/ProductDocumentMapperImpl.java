package com.ecommerce.productservice.mapper;

import com.ecommerce.productservice.document.ProductDocument;
import com.ecommerce.productservice.entity.Product;
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
public class ProductDocumentMapperImpl implements ProductDocumentMapper {

    @Override
    public ProductDocument toDocument(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDocument.ProductDocumentBuilder productDocument = ProductDocument.builder();

        productDocument.name( product.getName() );
        productDocument.slug( product.getSlug() );
        productDocument.description( product.getDescription() );
        productDocument.shortDescription( product.getShortDescription() );
        productDocument.price( product.getPrice() );
        productDocument.published( product.getPublished() );
        productDocument.featured( product.getFeatured() );
        productDocument.stockQuantity( product.getStockQuantity() );
        productDocument.sku( product.getSku() );
        productDocument.createdAt( product.getCreatedAt() );
        productDocument.updatedAt( product.getUpdatedAt() );

        productDocument.id( product.getId().toString() );
        productDocument.categoryId( product.getCategory() != null ? product.getCategory().getId().toString() : null );
        productDocument.categoryName( product.getCategory() != null ? product.getCategory().getName() : null );
        productDocument.categorySlug( product.getCategory() != null ? product.getCategory().getSlug() : null );
        productDocument.brandId( product.getBrand() != null ? product.getBrand().getId().toString() : null );
        productDocument.brandName( product.getBrand() != null ? product.getBrand().getName() : null );
        productDocument.brandSlug( product.getBrand() != null ? product.getBrand().getSlug() : null );
        productDocument.status( product.getStatus().name() );
        productDocument.imageUrls( mapImageUrls(product) );

        return productDocument.build();
    }

    @Override
    public List<ProductDocument> toDocumentList(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductDocument> list = new ArrayList<ProductDocument>( products.size() );
        for ( Product product : products ) {
            list.add( toDocument( product ) );
        }

        return list;
    }
}
