package com.ecommerce.productservice.mapper;

import com.ecommerce.productservice.dto.request.CreateCategoryRequest;
import com.ecommerce.productservice.dto.response.CategoryResponse;
import com.ecommerce.productservice.entity.Category;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-28T17:51:43+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.10 (JetBrains s.r.o.)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toEntity(CreateCategoryRequest request) {
        if ( request == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.name( request.getName() );
        category.slug( request.getSlug() );
        category.description( request.getDescription() );
        category.active( request.getActive() );
        category.displayOrder( request.getDisplayOrder() );
        category.imageUrl( request.getImageUrl() );
        category.metaTitle( request.getMetaTitle() );
        category.metaDescription( request.getMetaDescription() );
        category.metaKeywords( request.getMetaKeywords() );

        return category.build();
    }

    @Override
    public CategoryResponse toResponse(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryResponse.CategoryResponseBuilder categoryResponse = CategoryResponse.builder();

        categoryResponse.parentId( categoryParentId( category ) );
        categoryResponse.parentName( categoryParentName( category ) );
        categoryResponse.id( category.getId() );
        categoryResponse.name( category.getName() );
        categoryResponse.slug( category.getSlug() );
        categoryResponse.description( category.getDescription() );
        categoryResponse.children( toResponseList( category.getChildren() ) );
        categoryResponse.active( category.getActive() );
        categoryResponse.displayOrder( category.getDisplayOrder() );
        categoryResponse.imageUrl( category.getImageUrl() );
        categoryResponse.metaTitle( category.getMetaTitle() );
        categoryResponse.metaDescription( category.getMetaDescription() );
        categoryResponse.metaKeywords( category.getMetaKeywords() );
        categoryResponse.createdAt( category.getCreatedAt() );
        categoryResponse.updatedAt( category.getUpdatedAt() );

        return categoryResponse.build();
    }

    @Override
    public List<CategoryResponse> toResponseList(List<Category> categories) {
        if ( categories == null ) {
            return null;
        }

        List<CategoryResponse> list = new ArrayList<CategoryResponse>( categories.size() );
        for ( Category category : categories ) {
            list.add( toResponse( category ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromRequest(CreateCategoryRequest request, Category category) {
        if ( request == null ) {
            return;
        }

        if ( request.getName() != null ) {
            category.setName( request.getName() );
        }
        if ( request.getSlug() != null ) {
            category.setSlug( request.getSlug() );
        }
        if ( request.getDescription() != null ) {
            category.setDescription( request.getDescription() );
        }
        if ( request.getActive() != null ) {
            category.setActive( request.getActive() );
        }
        if ( request.getDisplayOrder() != null ) {
            category.setDisplayOrder( request.getDisplayOrder() );
        }
        if ( request.getImageUrl() != null ) {
            category.setImageUrl( request.getImageUrl() );
        }
        if ( request.getMetaTitle() != null ) {
            category.setMetaTitle( request.getMetaTitle() );
        }
        if ( request.getMetaDescription() != null ) {
            category.setMetaDescription( request.getMetaDescription() );
        }
        if ( request.getMetaKeywords() != null ) {
            category.setMetaKeywords( request.getMetaKeywords() );
        }
    }

    private UUID categoryParentId(Category category) {
        if ( category == null ) {
            return null;
        }
        Category parent = category.getParent();
        if ( parent == null ) {
            return null;
        }
        UUID id = parent.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String categoryParentName(Category category) {
        if ( category == null ) {
            return null;
        }
        Category parent = category.getParent();
        if ( parent == null ) {
            return null;
        }
        String name = parent.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
