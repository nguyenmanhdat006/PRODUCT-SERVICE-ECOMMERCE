package com.ecommerce.productservice.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductImageRequest {

    private String imageUrl;

    private String altText;

    private Boolean isPrimary;

    private Integer displayOrder;
}

