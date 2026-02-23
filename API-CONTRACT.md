# API CONTRACT - Product Catalog Service

Base URL: `http://localhost:8082`

## Authentication

Most endpoints are public. Admin endpoints require JWT Bearer token in Authorization header:

```
Authorization: Bearer <JWT_TOKEN>
```

## Categories API

### Get All Categories
```http
GET /api/categories
```

**Response:**
```json
{
  "success": true,
  "data": [
    {
      "id": "uuid",
      "name": "Electronics",
      "slug": "electronics",
      "description": "Electronic devices",
      "active": true,
      "children": []
    }
  ]
}
```

### Get Category Tree
```http
GET /api/categories/tree
```

### Get Category by ID
```http
GET /api/categories/{id}
```

### Get Category by Slug
```http
GET /api/categories/slug/{slug}
```

### Create Category (ADMIN)
```http
POST /api/categories
Authorization: Bearer <token>
Content-Type: application/json

{
  "name": "Electronics",
  "slug": "electronics",
  "description": "Electronic devices and accessories",
  "parentId": null,
  "active": true,
  "displayOrder": 1
}
```

### Update Category (ADMIN)
```http
PUT /api/categories/{id}
Authorization: Bearer <token>
```

### Delete Category (ADMIN)
```http
DELETE /api/categories/{id}
Authorization: Bearer <token>
```

## Brands API

### Get All Brands
```http
GET /api/brands
```

### Get Active Brands
```http
GET /api/brands/active
```

### Get Brand by ID
```http
GET /api/brands/{id}
```

### Get Brand by Slug
```http
GET /api/brands/slug/{slug}
```

### Create Brand (ADMIN)
```http
POST /api/brands
Authorization: Bearer <token>
Content-Type: application/json

{
  "name": "Apple",
  "slug": "apple",
  "description": "Think Different",
  "logoUrl": "https://example.com/logo.png",
  "active": true
}
```

### Update Brand (ADMIN)
```http
PUT /api/brands/{id}
Authorization: Bearer <token>
```

### Delete Brand (ADMIN)
```http
DELETE /api/brands/{id}
Authorization: Bearer <token>
```

## Products API

### Get All Products (Paginated)
```http
GET /api/products?page=0&size=20&sortBy=createdAt&sortDirection=desc
```

**Query Parameters:**
- `page` (default: 0) - Page number
- `size` (default: 20) - Page size
- `sortBy` (default: createdAt) - Sort field
- `sortDirection` (default: desc) - Sort direction (asc/desc)

**Response:**
```json
{
  "success": true,
  "data": {
    "content": [
      {
        "id": "uuid",
        "name": "iPhone 15 Pro",
        "slug": "iphone-15-pro",
        "description": "Latest iPhone",
        "price": 999.00,
        "category": {
          "id": "uuid",
          "name": "Smartphones"
        },
        "brand": {
          "id": "uuid",
          "name": "Apple"
        },
        "images": [],
        "variants": [],
        "published": true,
        "featured": true,
        "stockQuantity": 50
      }
    ],
    "page": 0,
    "size": 20,
    "totalElements": 100,
    "totalPages": 5,
    "first": true,
    "last": false
  }
}
```

### Get Published Products
```http
GET /api/products/published?page=0&size=20
```

### Get Product by ID
```http
GET /api/products/{id}
```

### Get Product by Slug
```http
GET /api/products/slug/{slug}
```

### Get Products by Category
```http
GET /api/products/category/{categoryId}?page=0&size=20
```

### Get Products by Brand
```http
GET /api/products/brand/{brandId}?page=0&size=20
```

### Get Featured Products
```http
GET /api/products/featured?page=0&size=20
```

### Get Products by Price Range
```http
GET /api/products/price-range?minPrice=100&maxPrice=500&page=0&size=20
```

### Search Products
```http
GET /api/products/search?keyword=iphone&categoryId=uuid&brandId=uuid&minPrice=100&maxPrice=1000&featured=true&page=0&size=20
```

**Query Parameters:**
- `keyword` - Search keyword (searches in name and description)
- `categoryId` - Filter by category UUID
- `brandId` - Filter by brand UUID
- `minPrice` - Minimum price
- `maxPrice` - Maximum price
- `featured` - Filter featured products (true/false)
- `page` - Page number (default: 0)
- `size` - Page size (default: 20)
- `sortBy` - Sort field (default: createdAt)
- `sortDirection` - Sort direction (default: desc)

### Create Product (ADMIN)
```http
POST /api/products
Authorization: Bearer <token>
Content-Type: application/json

{
  "name": "iPhone 15 Pro",
  "slug": "iphone-15-pro",
  "description": "Latest iPhone with A17 Pro chip",
  "shortDescription": "Premium smartphone",
  "price": 999.00,
  "compareAtPrice": 1099.00,
  "categoryId": "uuid",
  "brandId": "uuid",
  "status": "PUBLISHED",
  "published": true,
  "featured": true,
  "stockQuantity": 50,
  "sku": "IPH15PRO-001",
  "images": [
    {
      "imageUrl": "https://example.com/image.jpg",
      "altText": "iPhone 15 Pro",
      "isPrimary": true,
      "displayOrder": 1
    }
  ],
  "variants": [
    {
      "sku": "IPH15PRO-128-BLK",
      "size": "128GB",
      "color": "Black",
      "stockQuantity": 20,
      "priceAdjustment": 0.00,
      "available": true
    }
  ]
}
```

### Update Product (ADMIN)
```http
PUT /api/products/{id}
Authorization: Bearer <token>
```

### Delete Product (ADMIN)
```http
DELETE /api/products/{id}
Authorization: Bearer <token>
```

### Upload Product Image (ADMIN)
```http
POST /api/products/{id}/images/upload
Authorization: Bearer <token>
Content-Type: multipart/form-data

file: <image_file>
```

### Sync All Products to Elasticsearch (ADMIN)
```http
POST /api/products/sync-elasticsearch
Authorization: Bearer <token>
```

## Product Status Values

- `DRAFT` - Not visible to customers
- `PUBLISHED` - Visible and purchasable
- `ARCHIVED` - Hidden but not deleted
- `OUT_OF_STOCK` - Visible but not purchasable

## Common Error Responses

### 400 Bad Request
```json
{
  "success": false,
  "error": "Validation failed",
  "data": {
    "name": "Product name is required",
    "price": "Price must be greater than 0"
  }
}
```

### 401 Unauthorized
```json
{
  "success": false,
  "error": "Unauthorized"
}
```

### 403 Forbidden
```json
{
  "success": false,
  "error": "Access denied: Insufficient permissions"
}
```

### 404 Not Found
```json
{
  "success": false,
  "error": "Product not found with id: 'uuid'"
}
```

### 500 Internal Server Error
```json
{
  "success": false,
  "error": "Internal server error: <error_message>"
}
```

