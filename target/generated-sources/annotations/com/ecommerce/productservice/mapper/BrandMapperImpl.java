package com.ecommerce.productservice.mapper;

import com.ecommerce.productservice.dto.request.CreateBrandRequest;
import com.ecommerce.productservice.dto.response.BrandResponse;
import com.ecommerce.productservice.entity.Brand;
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
public class BrandMapperImpl implements BrandMapper {

    @Override
    public Brand toEntity(CreateBrandRequest request) {
        if ( request == null ) {
            return null;
        }

        Brand.BrandBuilder brand = Brand.builder();

        brand.name( request.getName() );
        brand.slug( request.getSlug() );
        brand.description( request.getDescription() );
        brand.logoUrl( request.getLogoUrl() );
        brand.websiteUrl( request.getWebsiteUrl() );
        brand.active( request.getActive() );
        brand.metaTitle( request.getMetaTitle() );
        brand.metaDescription( request.getMetaDescription() );

        return brand.build();
    }

    @Override
    public BrandResponse toResponse(Brand brand) {
        if ( brand == null ) {
            return null;
        }

        BrandResponse.BrandResponseBuilder brandResponse = BrandResponse.builder();

        brandResponse.id( brand.getId() );
        brandResponse.name( brand.getName() );
        brandResponse.slug( brand.getSlug() );
        brandResponse.description( brand.getDescription() );
        brandResponse.logoUrl( brand.getLogoUrl() );
        brandResponse.websiteUrl( brand.getWebsiteUrl() );
        brandResponse.active( brand.getActive() );
        brandResponse.metaTitle( brand.getMetaTitle() );
        brandResponse.metaDescription( brand.getMetaDescription() );
        brandResponse.createdAt( brand.getCreatedAt() );
        brandResponse.updatedAt( brand.getUpdatedAt() );

        return brandResponse.build();
    }

    @Override
    public List<BrandResponse> toResponseList(List<Brand> brands) {
        if ( brands == null ) {
            return null;
        }

        List<BrandResponse> list = new ArrayList<BrandResponse>( brands.size() );
        for ( Brand brand : brands ) {
            list.add( toResponse( brand ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromRequest(CreateBrandRequest request, Brand brand) {
        if ( request == null ) {
            return;
        }

        if ( request.getName() != null ) {
            brand.setName( request.getName() );
        }
        if ( request.getSlug() != null ) {
            brand.setSlug( request.getSlug() );
        }
        if ( request.getDescription() != null ) {
            brand.setDescription( request.getDescription() );
        }
        if ( request.getLogoUrl() != null ) {
            brand.setLogoUrl( request.getLogoUrl() );
        }
        if ( request.getWebsiteUrl() != null ) {
            brand.setWebsiteUrl( request.getWebsiteUrl() );
        }
        if ( request.getActive() != null ) {
            brand.setActive( request.getActive() );
        }
        if ( request.getMetaTitle() != null ) {
            brand.setMetaTitle( request.getMetaTitle() );
        }
        if ( request.getMetaDescription() != null ) {
            brand.setMetaDescription( request.getMetaDescription() );
        }
    }
}
