package com.ecommerce.productservice.mapper;

import com.ecommerce.productservice.dto.request.CreateProductRequest;
import com.ecommerce.productservice.dto.response.ProductResponse;
import com.ecommerce.productservice.entity.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-04T09:25:51+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.18 (Microsoft)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private ProductImageMapper productImageMapper;
    @Autowired
    private ProductVariantMapper productVariantMapper;

    @Override
    public Product toEntity(CreateProductRequest request) {
        if ( request == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.name( request.getName() );
        product.slug( request.getSlug() );
        product.description( request.getDescription() );
        product.shortDescription( request.getShortDescription() );
        product.price( request.getPrice() );
        product.compareAtPrice( request.getCompareAtPrice() );
        product.costPrice( request.getCostPrice() );
        product.status( request.getStatus() );
        product.published( request.getPublished() );
        product.featured( request.getFeatured() );
        product.stockQuantity( request.getStockQuantity() );
        product.sku( request.getSku() );
        product.barcode( request.getBarcode() );
        product.weight( request.getWeight() );
        product.weightUnit( request.getWeightUnit() );
        product.length( request.getLength() );
        product.width( request.getWidth() );
        product.height( request.getHeight() );
        product.dimensionUnit( request.getDimensionUnit() );
        product.metaTitle( request.getMetaTitle() );
        product.metaDescription( request.getMetaDescription() );
        product.metaKeywords( request.getMetaKeywords() );

        return product.build();
    }

    @Override
    public ProductResponse toResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponse.ProductResponseBuilder productResponse = ProductResponse.builder();

        productResponse.id( product.getId() );
        productResponse.name( product.getName() );
        productResponse.slug( product.getSlug() );
        productResponse.description( product.getDescription() );
        productResponse.shortDescription( product.getShortDescription() );
        productResponse.price( product.getPrice() );
        productResponse.compareAtPrice( product.getCompareAtPrice() );
        productResponse.costPrice( product.getCostPrice() );
        productResponse.category( categoryMapper.toResponse( product.getCategory() ) );
        productResponse.brand( brandMapper.toResponse( product.getBrand() ) );
        productResponse.status( product.getStatus() );
        productResponse.published( product.getPublished() );
        productResponse.featured( product.getFeatured() );
        productResponse.stockQuantity( product.getStockQuantity() );
        productResponse.sku( product.getSku() );
        productResponse.barcode( product.getBarcode() );
        productResponse.weight( product.getWeight() );
        productResponse.weightUnit( product.getWeightUnit() );
        productResponse.length( product.getLength() );
        productResponse.width( product.getWidth() );
        productResponse.height( product.getHeight() );
        productResponse.dimensionUnit( product.getDimensionUnit() );
        productResponse.metaTitle( product.getMetaTitle() );
        productResponse.metaDescription( product.getMetaDescription() );
        productResponse.metaKeywords( product.getMetaKeywords() );
        productResponse.images( productImageMapper.toResponseList( product.getImages() ) );
        productResponse.variants( productVariantMapper.toResponseList( product.getVariants() ) );
        productResponse.createdAt( product.getCreatedAt() );
        productResponse.updatedAt( product.getUpdatedAt() );
        productResponse.publishedAt( product.getPublishedAt() );

        return productResponse.build();
    }

    @Override
    public List<ProductResponse> toResponseList(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductResponse> list = new ArrayList<ProductResponse>( products.size() );
        for ( Product product : products ) {
            list.add( toResponse( product ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromRequest(CreateProductRequest request, Product product) {
        if ( request == null ) {
            return;
        }

        if ( request.getName() != null ) {
            product.setName( request.getName() );
        }
        if ( request.getSlug() != null ) {
            product.setSlug( request.getSlug() );
        }
        if ( request.getDescription() != null ) {
            product.setDescription( request.getDescription() );
        }
        if ( request.getShortDescription() != null ) {
            product.setShortDescription( request.getShortDescription() );
        }
        if ( request.getPrice() != null ) {
            product.setPrice( request.getPrice() );
        }
        if ( request.getCompareAtPrice() != null ) {
            product.setCompareAtPrice( request.getCompareAtPrice() );
        }
        if ( request.getCostPrice() != null ) {
            product.setCostPrice( request.getCostPrice() );
        }
        if ( request.getStatus() != null ) {
            product.setStatus( request.getStatus() );
        }
        if ( request.getPublished() != null ) {
            product.setPublished( request.getPublished() );
        }
        if ( request.getFeatured() != null ) {
            product.setFeatured( request.getFeatured() );
        }
        if ( request.getStockQuantity() != null ) {
            product.setStockQuantity( request.getStockQuantity() );
        }
        if ( request.getSku() != null ) {
            product.setSku( request.getSku() );
        }
        if ( request.getBarcode() != null ) {
            product.setBarcode( request.getBarcode() );
        }
        if ( request.getWeight() != null ) {
            product.setWeight( request.getWeight() );
        }
        if ( request.getWeightUnit() != null ) {
            product.setWeightUnit( request.getWeightUnit() );
        }
        if ( request.getLength() != null ) {
            product.setLength( request.getLength() );
        }
        if ( request.getWidth() != null ) {
            product.setWidth( request.getWidth() );
        }
        if ( request.getHeight() != null ) {
            product.setHeight( request.getHeight() );
        }
        if ( request.getDimensionUnit() != null ) {
            product.setDimensionUnit( request.getDimensionUnit() );
        }
        if ( request.getMetaTitle() != null ) {
            product.setMetaTitle( request.getMetaTitle() );
        }
        if ( request.getMetaDescription() != null ) {
            product.setMetaDescription( request.getMetaDescription() );
        }
        if ( request.getMetaKeywords() != null ) {
            product.setMetaKeywords( request.getMetaKeywords() );
        }
    }
}
