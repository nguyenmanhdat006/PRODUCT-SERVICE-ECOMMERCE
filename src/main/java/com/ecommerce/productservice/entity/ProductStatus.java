package com.ecommerce.productservice.entity;

public enum ProductStatus {
    DRAFT,          // Not visible to customers
    PUBLISHED,      // Visible and purchasable
    ARCHIVED,       // Hidden but not deleted
    OUT_OF_STOCK    // Visible but not purchasable
}

