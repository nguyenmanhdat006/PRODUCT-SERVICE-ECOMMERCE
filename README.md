# PRODUCT SERVICE - IMPLEMENTATION GUIDE FOR GITHUB COPILOT

**Project:** E-commerce Fashion Microservices  
**Service:** Product Catalog Service  
**Tech Stack:** Spring Boot 3.2.2, PostgreSQL, Elasticsearch, MinIO  
**Port:** 8082

---

## 🚀 QUICK START (5 MINUTES SETUP)

Before generating code, set up the project structure:

```bash
# 1. Create project root
mkdir product-service
cd product-service

# 2. Copy these files from the package:
# - pom.xml
# - docker-compose.yml
# - src/main/resources/application.yml
# - README.md
# - API-CONTRACT.md

# 3. Create directory structure
mkdir -p src/main/java/com/ecommerce/productservice/{config,entity,document,repository,dto/{request,response},service,controller,mapper,exception}
mkdir -p src/main/resources
mkdir -p src/test/java

# 4. Open in VS Code
code .

# 5. Make sure GitHub Copilot extension is installed and active

# 6. Start creating files following Phase 0 below!
```

---

## 📋 PROJECT OVERVIEW

This is a complete Product Catalog Service for an e-commerce platform. The service handles:
- Product CRUD operations with variants and images
- Category management (hierarchical tree structure)
- Brand management
- Full-text search with Elasticsearch
- Image storage with MinIO
- JWT authentication via Keycloak

---

## 🎯 WHAT'S ALREADY DONE (Setup Only - 0 Java files)

### Infrastructure & Config (100%)
- ✅ `pom.xml` - All Maven dependencies configured
- ✅ `docker-compose.yml` - PostgreSQL, Elasticsearch, MinIO ready
- ✅ `application.yml` - Complete Spring Boot configuration
- ✅ `README.md` - Full documentation
- ✅ `API-CONTRACT.md` - All API specifications

**TOTAL: 0 Java files created yet**

---

## 🔨 WHAT NEEDS TO BE CREATED (100%)

You need to create **ALL 40 Java files** in this exact order:

### 1. Main Application (1 file)
### 2. Configs (3 files)
### 3. Exception Handling (3 files)
### 4. Entities (5 files)
### 5. Elasticsearch Document (1 file)
### 6. Repositories (6 files)
### 7. DTOs - Response (7 files)
### 8. DTOs - Request (8 files)
### 9. Mapper (1 file)
### 10. Services (5 files)
### 11. Controllers (3 files)

**Total: 40 files to generate**

---

## 📁 DIRECTORY STRUCTURE

```
src/main/java/com/ecommerce/productservice/
├── ProductServiceApplication.java ⏳ CREATE
├── config/
│   ├── SecurityConfig.java ⏳ CREATE
│   ├── ElasticsearchConfig.java ⏳ CREATE
│   └── MinioConfig.java ⏳ CREATE
├── entity/
│   ├── Product.java ⏳ CREATE
│   ├── Category.java ⏳ CREATE
│   ├── Brand.java ⏳ CREATE
│   ├── ProductImage.java ⏳ CREATE
│   └── ProductVariant.java ⏳ CREATE
├── document/
│   └── ProductDocument.java ⏳ CREATE
├── repository/
│   ├── ProductRepository.java ⏳ CREATE
│   ├── CategoryRepository.java ⏳ CREATE
│   ├── BrandRepository.java ⏳ CREATE
│   ├── ProductImageRepository.java ⏳ CREATE
│   ├── ProductVariantRepository.java ⏳ CREATE
│   └── ProductSearchRepository.java ⏳ CREATE
├── exception/
│   ├── ResourceNotFoundException.java ⏳ CREATE
│   ├── BadRequestException.java ⏳ CREATE
│   └── GlobalExceptionHandler.java ⏳ CREATE
├── dto/
│   ├── request/
│   │   ├── CreateProductRequest.java ⏳ CREATE
│   │   ├── UpdateProductRequest.java ⏳ CREATE
│   │   ├── CreateCategoryRequest.java ⏳ CREATE
│   │   ├── UpdateCategoryRequest.java ⏳ CREATE
│   │   ├── CreateBrandRequest.java ⏳ CREATE
│   │   ├── UpdateBrandRequest.java ⏳ CREATE
│   │   ├── CreateProductVariantRequest.java ⏳ CREATE
│   │   └── ProductSearchRequest.java ⏳ CREATE
│   └── response/
│       ├── ApiResponse.java ⏳ CREATE
│       ├── PageResponse.java ⏳ CREATE
│       ├── ProductResponse.java ⏳ CREATE
│       ├── CategoryResponse.java ⏳ CREATE
│       ├── BrandResponse.java ⏳ CREATE
│       ├── ProductImageResponse.java ⏳ CREATE
│       └── ProductVariantResponse.java ⏳ CREATE
├── mapper/
│   └── ProductMapper.java ⏳ CREATE
├── service/
│   ├── ProductService.java ⏳ CREATE
│   ├── CategoryService.java ⏳ CREATE
│   ├── BrandService.java ⏳ CREATE
│   ├── ProductSearchService.java ⏳ CREATE
│   └── ImageService.java ⏳ CREATE
└── controller/
    ├── ProductController.java ⏳ CREATE
    ├── CategoryController.java ⏳ CREATE
    └── BrandController.java ⏳ CREATE
```

---

## 🎯 STEP-BY-STEP IMPLEMENTATION WITH COPILOT

**Total Time: 2-3 hours for all 40 files**

### PHASE 0: MAIN APPLICATION & CONFIGS (~20 minutes - 4 files)

#### File: `src/main/java/com/ecommerce/productservice/ProductServiceApplication.java`

**Prompt for Copilot:**
```
Create ProductServiceApplication.java with:
- @SpringBootApplication annotation
- @EnableJpaAuditing annotation for automatic timestamp handling
- Main method: public static void main(String[] args)
- Call SpringApplication.run(ProductServiceApplication.class, args)
- Package: com.ecommerce.productservice
```

#### File: `src/main/java/com/ecommerce/productservice/config/SecurityConfig.java`

**Prompt for Copilot:**
```
Create SecurityConfig.java with:
- @Configuration, @EnableWebSecurity, @EnableMethodSecurity
- @Bean SecurityFilterChain securityFilterChain(HttpSecurity http)
  - Disable CSRF
  - Enable CORS with corsConfigurationSource()
  - Public endpoints (permitAll): GET /api/products/**, GET /api/categories/**, GET /api/brands/**, GET /actuator/**
  - All other endpoints require authentication
  - Configure OAuth2 Resource Server with JWT
  - Use jwtAuthenticationConverter()
  - Stateless session management
- @Bean JwtAuthenticationConverter jwtAuthenticationConverter()
  - Extract roles from JWT "realm_access.roles" claim
  - Extract roles from JWT "resource_access.ecommerce-backend.roles" claim
  - Prefix roles with "ROLE_"
  - Combine all authorities
- @Bean CorsConfigurationSource corsConfigurationSource()
  - Allowed origins: http://localhost:3000, http://localhost:5173, http://localhost:8080
  - Allowed methods: GET, POST, PUT, DELETE, OPTIONS
  - Allowed headers: *
  - Allow credentials: true
- Package: com.ecommerce.productservice.config
- Import necessary Spring Security classes
```

#### File: `src/main/java/com/ecommerce/productservice/config/ElasticsearchConfig.java`

**Prompt for Copilot:**
```
Create ElasticsearchConfig.java with:
- @Configuration
- @EnableElasticsearchRepositories(basePackages = "com.ecommerce.productservice.repository")
- Extends ElasticsearchConfiguration
- @Value("${spring.elasticsearch.uris}") String elasticsearchUrl
- Override clientConfiguration() method
  - Return ClientConfiguration.builder().connectedTo(elasticsearchUrl without http://).build()
- Package: com.ecommerce.productservice.config
```

#### File: `src/main/java/com/ecommerce/productservice/config/MinioConfig.java`

**Prompt for Copilot:**
```
Create MinioConfig.java with:
- @Configuration
- @ConfigurationProperties(prefix = "minio")
- @Data from Lombok
- Fields: String url, accessKey, secretKey, bucketName, Integer imageUrlExpiry
- @Bean MinioClient minioClient()
  - Return MinioClient.builder().endpoint(url).credentials(accessKey, secretKey).build()
- Package: com.ecommerce.productservice.config
```

---

### PHASE 1: ENTITIES & DOCUMENT (~30 minutes - 6 files)

#### File: `src/main/java/com/ecommerce/productservice/entity/Product.java`

**Prompt for Copilot:**
```
Create Product.java entity with:
- @Entity, @Table(name = "products")
- @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder from Lombok
- Indexes on: slug, status, category_id, brand_id, published, featured
- Fields:
  - @Id @GeneratedValue(UUID) UUID id
  - @Column(nullable=false, length=500) String name
  - @Column(unique=true, nullable=false, length=500) String slug
  - @Column(columnDefinition="TEXT") String description, shortDescription
  - @Column(nullable=false, precision=19, scale=2) BigDecimal price
  - @Column(precision=19, scale=2) BigDecimal salePrice, costPrice
  - @Column(nullable=false) Integer stockQuantity (default=0)
  - @Column(nullable=false, length=20) @Enumerated(STRING) ProductStatus status (default=DRAFT)
  - @Column(nullable=false) Boolean featured (default=false), published (default=false)
  - String metaTitle, metaDescription, metaKeywords (SEO)
  - @ManyToOne(LAZY) @JoinColumn(name="category_id") Category category
  - @ManyToOne(LAZY) @JoinColumn(name="brand_id") Brand brand
  - @OneToMany(mappedBy="product", cascade=ALL, orphanRemoval=true) List<ProductImage> images (default=new ArrayList<>())
  - @OneToMany(mappedBy="product", cascade=ALL, orphanRemoval=true) List<ProductVariant> variants (default=new ArrayList<>())
  - @CreationTimestamp LocalDateTime createdAt
  - @UpdateTimestamp LocalDateTime updatedAt
- Helper methods: addImage, removeImage, addVariant, removeVariant
- Enum ProductStatus: DRAFT, PUBLISHED, ARCHIVED, OUT_OF_STOCK
- Package: com.ecommerce.productservice.entity
```

#### File: `src/main/java/com/ecommerce/productservice/entity/Category.java`

**Prompt for Copilot:**
```
Create Category.java entity with:
- @Entity, @Table(name = "categories")
- @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
- Indexes on: slug, parent_id, active
- Fields:
  - @Id @GeneratedValue(UUID) UUID id
  - @Column(nullable=false, length=200) String name
  - @Column(unique=true, nullable=false, length=200) String slug
  - @Column(columnDefinition="TEXT") String description
  - @Column(length=500) String imageUrl
  - @Column(nullable=false) Integer displayOrder (default=0)
  - @Column(nullable=false) Boolean active (default=true)
  - @ManyToOne(LAZY) @JoinColumn(name="parent_id") Category parent (self-reference)
  - @OneToMany(mappedBy="parent", cascade=ALL) List<Category> children (default=new ArrayList<>())
  - @OneToMany(mappedBy="category") List<Product> products (default=new ArrayList<>())
  - @CreationTimestamp LocalDateTime createdAt
  - @UpdateTimestamp LocalDateTime updatedAt
- Package: com.ecommerce.productservice.entity
```

#### File: `src/main/java/com/ecommerce/productservice/entity/Brand.java`

**Prompt for Copilot:**
```
Create Brand.java entity with:
- @Entity, @Table(name = "brands")
- @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
- Indexes on: slug, active
- Fields:
  - @Id @GeneratedValue(UUID) UUID id
  - @Column(nullable=false, unique=true, length=200) String name
  - @Column(unique=true, nullable=false, length=200) String slug
  - @Column(columnDefinition="TEXT") String description
  - @Column(length=500) String logoUrl, websiteUrl
  - @Column(nullable=false) Boolean active (default=true)
  - @OneToMany(mappedBy="brand") List<Product> products (default=new ArrayList<>())
  - @CreationTimestamp LocalDateTime createdAt
  - @UpdateTimestamp LocalDateTime updatedAt
- Package: com.ecommerce.productservice.entity
```

#### File: `src/main/java/com/ecommerce/productservice/entity/ProductImage.java`

**Prompt for Copilot:**
```
Create ProductImage.java entity with:
- @Entity, @Table(name = "product_images")
- @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
- Indexes on: product_id, (product_id, is_primary)
- Fields:
  - @Id @GeneratedValue(UUID) UUID id
  - @ManyToOne(LAZY) @JoinColumn(name="product_id", nullable=false) Product product
  - @Column(nullable=false, length=500) String imageUrl
  - @Column(length=200) String altText
  - @Column(nullable=false) Integer displayOrder (default=0)
  - @Column(nullable=false) Boolean isPrimary (default=false)
  - @CreationTimestamp LocalDateTime createdAt
- Package: com.ecommerce.productservice.entity
```

#### File: `src/main/java/com/ecommerce/productservice/entity/ProductVariant.java`

**Prompt for Copilot:**
```
Create ProductVariant.java entity with:
- @Entity, @Table(name = "product_variants")
- @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
- Indexes on: product_id, sku (unique)
- Fields:
  - @Id @GeneratedValue(UUID) UUID id
  - @ManyToOne(LAZY) @JoinColumn(name="product_id", nullable=false) Product product
  - @Column(unique=true, nullable=false, length=100) String sku
  - @Column(length=100) String size, color, material
  - @Column(precision=19, scale=2) BigDecimal priceAdjustment
  - @Column(nullable=false) Integer stockQuantity (default=0)
  - @Column(length=500) String imageUrl
  - @Column(nullable=false) Boolean active (default=true)
  - @CreationTimestamp LocalDateTime createdAt
  - @UpdateTimestamp LocalDateTime updatedAt
- Package: com.ecommerce.productservice.entity
```

#### File: `src/main/java/com/ecommerce/productservice/document/ProductDocument.java`

**Prompt for Copilot:**
```
Create ProductDocument.java for Elasticsearch with:
- @Document(indexName = "products")
- @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
- Fields with @Field annotations:
  - @Id String id
  - @Field(type=Text, analyzer="standard") String name
  - @Field(type=Keyword) String slug
  - @Field(type=Text) String description, shortDescription
  - @Field(type=Double) BigDecimal price, salePrice
  - @Field(type=Integer) Integer stockQuantity
  - @Field(type=Keyword) String status
  - @Field(type=Boolean) Boolean featured, published
  - @Field(type=Keyword) String categoryId, categorySlug, brandId, brandSlug
  - @Field(type=Text) String categoryName, brandName
  - @Field(type=Keyword) List<String> imageUrls (default=new ArrayList<>())
  - @Field(type=Keyword) String primaryImageUrl
  - @Field(type=Date) LocalDateTime createdAt, updatedAt
- Package: com.ecommerce.productservice.document
```

---

### PHASE 2: REPOSITORIES (~20 minutes - 6 files)

#### File: `src/main/java/com/ecommerce/productservice/repository/ProductRepository.java`

**Prompt for Copilot:**
```
Create ProductRepository.java interface with:
- @Repository
- Extends JpaRepository<Product, UUID>
- Methods:
  - Optional<Product> findBySlug(String slug)
  - boolean existsBySlug(String slug)
  - Page<Product> findByStatus(ProductStatus status, Pageable pageable)
  - Page<Product> findByPublishedTrue(Pageable pageable)
  - Page<Product> findByFeaturedTrue(Pageable pageable)
  - Page<Product> findByCategoryId(UUID categoryId, Pageable pageable)
  - Page<Product> findByBrandId(UUID brandId, Pageable pageable)
  - @Query("SELECT p FROM Product p WHERE (:categoryId IS NULL OR p.category.id = :categoryId) AND (:brandId IS NULL OR p.brand.id = :brandId) AND (:status IS NULL OR p.status = :status) AND p.published = true")
    Page<Product> findByFilters(@Param categoryId, @Param brandId, @Param status, Pageable)
  - @Query("SELECT COUNT(p) FROM Product p WHERE p.category.id = :categoryId")
    long countByCategoryId(@Param UUID categoryId)
  - @Query("SELECT COUNT(p) FROM Product p WHERE p.brand.id = :brandId")
    long countByBrandId(@Param UUID brandId)
- Package: com.ecommerce.productservice.repository
```

#### File: `src/main/java/com/ecommerce/productservice/repository/CategoryRepository.java`

**Prompt for Copilot:**
```
Create CategoryRepository.java interface with:
- @Repository
- Extends JpaRepository<Category, UUID>
- Methods:
  - Optional<Category> findBySlug(String slug)
  - boolean existsBySlug(String slug)
  - List<Category> findByParentIsNullOrderByDisplayOrderAsc()
  - List<Category> findByParentIdOrderByDisplayOrderAsc(UUID parentId)
  - List<Category> findByActiveTrue()
  - @Query("SELECT c FROM Category c WHERE c.parent IS NULL AND c.active = true ORDER BY c.displayOrder ASC")
    List<Category> findActiveRootCategories()
- Package: com.ecommerce.productservice.repository
```

#### File: `src/main/java/com/ecommerce/productservice/repository/BrandRepository.java`

**Prompt for Copilot:**
```
Create BrandRepository.java interface with:
- @Repository
- Extends JpaRepository<Brand, UUID>
- Methods:
  - Optional<Brand> findBySlug(String slug)
  - boolean existsBySlug(String slug)
  - boolean existsByName(String name)
  - List<Brand> findByActiveTrue()
- Package: com.ecommerce.productservice.repository
```

#### File: `src/main/java/com/ecommerce/productservice/repository/ProductImageRepository.java`

**Prompt for Copilot:**
```
Create ProductImageRepository.java interface with:
- @Repository
- Extends JpaRepository<ProductImage, UUID>
- Methods:
  - List<ProductImage> findByProductIdOrderByDisplayOrderAsc(UUID productId)
  - Optional<ProductImage> findByProductIdAndIsPrimaryTrue(UUID productId)
  - @Modifying @Query("UPDATE ProductImage pi SET pi.isPrimary = false WHERE pi.product.id = :productId")
    void unsetPrimaryForProduct(@Param UUID productId)
  - void deleteByProductId(UUID productId)
- Package: com.ecommerce.productservice.repository
```

#### File: `src/main/java/com/ecommerce/productservice/repository/ProductVariantRepository.java`

**Prompt for Copilot:**
```
Create ProductVariantRepository.java interface with:
- @Repository
- Extends JpaRepository<ProductVariant, UUID>
- Methods:
  - List<ProductVariant> findByProductId(UUID productId)
  - List<ProductVariant> findByProductIdAndActiveTrue(UUID productId)
  - Optional<ProductVariant> findBySku(String sku)
  - boolean existsBySku(String sku)
  - void deleteByProductId(UUID productId)
- Package: com.ecommerce.productservice.repository
```

#### File: `src/main/java/com/ecommerce/productservice/repository/ProductSearchRepository.java`

**Prompt for Copilot:**
```
Create ProductSearchRepository.java interface with:
- @Repository
- Extends ElasticsearchRepository<ProductDocument, String>
- Methods:
  - Page<ProductDocument> findByNameContainingOrDescriptionContaining(String name, String description, Pageable pageable)
  - Page<ProductDocument> findByCategoryId(String categoryId, Pageable pageable)
  - Page<ProductDocument> findByBrandId(String brandId, Pageable pageable)
  - Page<ProductDocument> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable)
  - Page<ProductDocument> findByPublishedTrue(Pageable pageable)
  - Page<ProductDocument> findByFeaturedTrue(Pageable pageable)
  - @Query("{\"bool\": {\"must\": [{\"match\": {\"name\": \"?0\"}}]}}")
    Page<ProductDocument> searchByName(String name, Pageable pageable)
- Package: com.ecommerce.productservice.repository
```

---

### PHASE 3: EXCEPTION HANDLING (~10 minutes - 3 files)

#### File: `src/main/java/com/ecommerce/productservice/exception/ResourceNotFoundException.java`

**Prompt for Copilot:**
```
Create ResourceNotFoundException.java that:
- Extends RuntimeException
- Has constructor with String message
- Has constructor with String message and Throwable cause
- Package: com.ecommerce.productservice.exception
```

#### File: `src/main/java/com/ecommerce/productservice/exception/BadRequestException.java`

**Prompt for Copilot:**
```
Create BadRequestException.java that:
- Extends RuntimeException
- Has constructor with String message
- Has constructor with String message and Throwable cause
- Package: com.ecommerce.productservice.exception
```

#### File: `src/main/java/com/ecommerce/productservice/exception/GlobalExceptionHandler.java`

**Prompt for Copilot:**
```
Create GlobalExceptionHandler.java with @RestControllerAdvice that handles:
- ResourceNotFoundException -> return 404 with ApiResponse<Void>
- BadRequestException -> return 400 with ApiResponse<Void>
- MethodArgumentNotValidException -> return 400 with validation errors in ApiResponse<Map<String, String>>
- AccessDeniedException -> return 403 with ApiResponse<Void>
- Exception -> return 500 with ApiResponse<Void>
- Use @ExceptionHandler for each
- Log errors with @Slf4j
- Package: com.ecommerce.productservice.exception
- Import ApiResponse from dto.response package
```

---

### PHASE 4: RESPONSE DTOs (~20 minutes - 7 files)

#### File: `src/main/java/com/ecommerce/productservice/dto/response/ApiResponse.java`

**Prompt for Copilot:**
```
Create ApiResponse.java generic class with:
- Fields: Boolean success, String message, T data, LocalDateTime timestamp
- Use Lombok @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
- Static method: success(T data) - returns ApiResponse with success=true
- Static method: success(String message, T data) - returns ApiResponse with success=true and message
- Static method: error(String message) - returns ApiResponse with success=false
- Default timestamp = LocalDateTime.now()
- Package: com.ecommerce.productservice.dto.response
```

#### File: `src/main/java/com/ecommerce/productservice/dto/response/PageResponse.java`

**Prompt for Copilot:**
```
Create PageResponse.java generic class with:
- Fields: List<T> content, Integer page, Integer size, Long totalElements, Integer totalPages, Boolean isLast
- Use Lombok @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
- Package: com.ecommerce.productservice.dto.response
```

#### File: `src/main/java/com/ecommerce/productservice/dto/response/ProductResponse.java`

**Prompt for Copilot:**
```
Create ProductResponse.java with these fields:
- UUID id, String name, String slug, String description, String shortDescription
- BigDecimal price, BigDecimal salePrice, BigDecimal costPrice
- Integer stockQuantity, String status, Boolean featured, Boolean published
- CategoryResponse category, BrandResponse brand
- List<ProductImageResponse> images (default = new ArrayList<>())
- List<ProductVariantResponse> variants (default = new ArrayList<>())
- String metaTitle, String metaDescription, String metaKeywords
- LocalDateTime createdAt, LocalDateTime updatedAt
- Use Lombok @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
- Package: com.ecommerce.productservice.dto.response
```

#### File: `src/main/java/com/ecommerce/productservice/dto/response/CategoryResponse.java`

**Prompt for Copilot:**
```
Create CategoryResponse.java with:
- UUID id, String name, String slug, String description, String imageUrl
- Integer displayOrder, Boolean active
- UUID parentId, String parentName
- List<CategoryResponse> children (default = new ArrayList<>())
- Long productCount
- LocalDateTime createdAt, LocalDateTime updatedAt
- Use Lombok @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
- Package: com.ecommerce.productservice.dto.response
```

#### File: `src/main/java/com/ecommerce/productservice/dto/response/BrandResponse.java`

**Prompt for Copilot:**
```
Create BrandResponse.java with:
- UUID id, String name, String slug, String description
- String logoUrl, String websiteUrl, Boolean active
- Long productCount
- LocalDateTime createdAt, LocalDateTime updatedAt
- Use Lombok @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
- Package: com.ecommerce.productservice.dto.response
```

#### File: `src/main/java/com/ecommerce/productservice/dto/response/ProductImageResponse.java`

**Prompt for Copilot:**
```
Create ProductImageResponse.java with:
- UUID id, String imageUrl, String altText
- Integer displayOrder, Boolean isPrimary
- LocalDateTime createdAt
- Use Lombok @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
- Package: com.ecommerce.productservice.dto.response
```

#### File: `src/main/java/com/ecommerce/productservice/dto/response/ProductVariantResponse.java`

**Prompt for Copilot:**
```
Create ProductVariantResponse.java with:
- UUID id, String sku, String size, String color, String material
- BigDecimal priceAdjustment, Integer stockQuantity
- String imageUrl, Boolean active
- LocalDateTime createdAt, LocalDateTime updatedAt
- Use Lombok @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
- Package: com.ecommerce.productservice.dto.response
```

---

### PHASE 5: REQUEST DTOs (~25 minutes - 8 files)

#### File: `src/main/java/com/ecommerce/productservice/dto/request/CreateProductRequest.java`

**Prompt for Copilot:**
```
Create CreateProductRequest.java with Jakarta validation:
- @NotBlank @Size(min=3, max=500) String name
- @NotBlank @Size(min=10) String description
- @Size(max=500) String shortDescription
- @NotNull @DecimalMin("0.0") BigDecimal price
- @DecimalMin("0.0") BigDecimal salePrice, BigDecimal costPrice
- @NotNull @Min(0) Integer stockQuantity
- UUID categoryId, UUID brandId
- Boolean featured (default=false), Boolean published (default=false)
- String metaTitle, metaDescription, metaKeywords
- List<String> imageUrls (default = new ArrayList<>())
- Use Lombok @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
- Package: com.ecommerce.productservice.dto.request
```

#### File: `src/main/java/com/ecommerce/productservice/dto/request/UpdateProductRequest.java`

**Prompt for Copilot:**
```
Create UpdateProductRequest.java (all fields optional) with validation:
- @Size(min=3, max=500) String name
- @Size(min=10) String description
- @Size(max=500) String shortDescription
- @DecimalMin("0.0") BigDecimal price, salePrice, costPrice
- @Min(0) Integer stockQuantity
- UUID categoryId, brandId
- Boolean featured, published
- String status, metaTitle, metaDescription, metaKeywords
- Use Lombok @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
- Package: com.ecommerce.productservice.dto.request
```

#### File: `src/main/java/com/ecommerce/productservice/dto/request/CreateCategoryRequest.java`

**Prompt for Copilot:**
```
Create CreateCategoryRequest.java with validation:
- @NotBlank @Size(min=2, max=200) String name
- @Size(max=1000) String description
- String imageUrl
- UUID parentId
- Integer displayOrder (default=0)
- Boolean active (default=true)
- Use Lombok @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
- Package: com.ecommerce.productservice.dto.request
```

#### File: `src/main/java/com/ecommerce/productservice/dto/request/UpdateCategoryRequest.java`

**Prompt for Copilot:**
```
Create UpdateCategoryRequest.java (all fields optional) with validation:
- @Size(min=2, max=200) String name
- @Size(max=1000) String description
- String imageUrl
- UUID parentId
- Integer displayOrder
- Boolean active
- Use Lombok @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
- Package: com.ecommerce.productservice.dto.request
```

#### File: `src/main/java/com/ecommerce/productservice/dto/request/CreateBrandRequest.java`

**Prompt for Copilot:**
```
Create CreateBrandRequest.java with validation:
- @NotBlank @Size(min=2, max=200) String name
- @Size(max=1000) String description
- String logoUrl, String websiteUrl
- Boolean active (default=true)
- Use Lombok @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
- Package: com.ecommerce.productservice.dto.request
```

#### File: `src/main/java/com/ecommerce/productservice/dto/request/UpdateBrandRequest.java`

**Prompt for Copilot:**
```
Create UpdateBrandRequest.java (all fields optional) with validation:
- @Size(min=2, max=200) String name
- @Size(max=1000) String description
- String logoUrl, String websiteUrl
- Boolean active
- Use Lombok @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
- Package: com.ecommerce.productservice.dto.request
```

#### File: `src/main/java/com/ecommerce/productservice/dto/request/CreateProductVariantRequest.java`

**Prompt for Copilot:**
```
Create CreateProductVariantRequest.java with validation:
- @NotBlank String sku
- String size, color, material
- BigDecimal priceAdjustment
- @Min(0) Integer stockQuantity (default=0)
- String imageUrl
- Boolean active (default=true)
- Use Lombok @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
- Package: com.ecommerce.productservice.dto.request
```

#### File: `src/main/java/com/ecommerce/productservice/dto/request/ProductSearchRequest.java`

**Prompt for Copilot:**
```
Create ProductSearchRequest.java with:
- String keyword
- UUID categoryId, UUID brandId
- BigDecimal minPrice, BigDecimal maxPrice
- String sortBy, String sortDirection
- Integer page (default=0), Integer size (default=20)
- Use Lombok @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
- Package: com.ecommerce.productservice.dto.request
```

---

### PHASE 6: MAPPER (~10 minutes - 1 file)

#### File: `src/main/java/com/ecommerce/productservice/mapper/ProductMapper.java`

**Prompt for Copilot:**
```
Create ProductMapper.java using MapStruct with these mappings:
- @Mapper(componentModel = "spring")
- Product toEntity(CreateProductRequest request)
- ProductResponse toResponse(Product product)
- List<ProductResponse> toResponseList(List<Product> products)
- void updateEntityFromRequest(UpdateProductRequest request, @MappingTarget Product product) with @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
- CategoryResponse toCategoryResponse(Category category)
- BrandResponse toBrandResponse(Brand brand)
- ProductImageResponse toImageResponse(ProductImage image)
- ProductVariantResponse toVariantResponse(ProductVariant variant)
- List<ProductImageResponse> toImageResponseList(List<ProductImage> images)
- List<ProductVariantResponse> toVariantResponseList(List<ProductVariant> variants)
- Package: com.ecommerce.productservice.mapper
```

---

### PHASE 7: SERVICES (~30 minutes - 5 files)

#### File: `src/main/java/com/ecommerce/productservice/service/ProductService.java`

**Prompt for Copilot:**
```
Create ProductService.java with:
- @Service, @RequiredArgsConstructor, @Slf4j, @Transactional
- Inject: ProductRepository, CategoryRepository, BrandRepository, ProductMapper
- Method: PageResponse<ProductResponse> getAllProducts(int page, int size, String sortBy, String sortDirection)
  - Use Pageable with sort
  - Map to PageResponse
- Method: ProductResponse getProductById(UUID id)
  - Throw ResourceNotFoundException if not found
- Method: ProductResponse getProductBySlug(String slug)
  - Throw ResourceNotFoundException if not found
- Method: ProductResponse createProduct(CreateProductRequest request)
  - Generate slug from name (lowercase, replace spaces with hyphens, remove special chars)
  - Check if slug exists, if yes append number
  - Set category and brand if provided
  - Save and return response
- Method: ProductResponse updateProduct(UUID id, UpdateProductRequest request)
  - Find by id, throw ResourceNotFoundException if not found
  - Use mapper.updateEntityFromRequest
  - Update category/brand if provided
  - Update status enum if provided
  - Save and return response
- Method: void deleteProduct(UUID id)
  - Find by id, throw ResourceNotFoundException if not found
  - Delete product
- Method: PageResponse<ProductResponse> getProductsByCategory(UUID categoryId, int page, int size)
- Method: PageResponse<ProductResponse> getProductsByBrand(UUID brandId, int page, int size)
- Method: PageResponse<ProductResponse> getFeaturedProducts(int page, int size)
- Method: PageResponse<ProductResponse> getPublishedProducts(int page, int size)
- Private method: String generateSlug(String name)
  - Convert to lowercase
  - Replace spaces with hyphens
  - Remove special characters except hyphens
  - Ensure uniqueness by checking repository
- Package: com.ecommerce.productservice.service
```

#### File: `src/main/java/com/ecommerce/productservice/service/CategoryService.java`

**Prompt for Copilot:**
```
Create CategoryService.java with:
- @Service, @RequiredArgsConstructor, @Slf4j, @Transactional
- Inject: CategoryRepository, ProductRepository, ProductMapper
- Method: List<CategoryResponse> getAllCategories()
- Method: List<CategoryResponse> getActiveCategories()
- Method: List<CategoryResponse> getRootCategories()
- Method: CategoryResponse getCategoryById(UUID id)
  - Include productCount from productRepository.countByCategoryId
- Method: CategoryResponse getCategoryBySlug(String slug)
- Method: CategoryResponse createCategory(CreateCategoryRequest request)
  - Generate slug
  - Set parent if parentId provided
  - Save and return
- Method: CategoryResponse updateCategory(UUID id, UpdateCategoryRequest request)
- Method: void deleteCategory(UUID id)
  - Check if has products, throw BadRequestException if yes
  - Delete category
- Private method: String generateSlug(String name)
- Package: com.ecommerce.productservice.service
```

#### File: `src/main/java/com/ecommerce/productservice/service/BrandService.java`

**Prompt for Copilot:**
```
Create BrandService.java with:
- @Service, @RequiredArgsConstructor, @Slf4j, @Transactional
- Inject: BrandRepository, ProductRepository, ProductMapper
- Method: List<BrandResponse> getAllBrands()
- Method: List<BrandResponse> getActiveBrands()
- Method: BrandResponse getBrandById(UUID id)
  - Include productCount
- Method: BrandResponse getBrandBySlug(String slug)
- Method: BrandResponse createBrand(CreateBrandRequest request)
  - Generate slug
  - Check if name exists, throw BadRequestException if yes
  - Save and return
- Method: BrandResponse updateBrand(UUID id, UpdateBrandRequest request)
- Method: void deleteBrand(UUID id)
  - Check if has products, throw BadRequestException if yes
- Private method: String generateSlug(String name)
- Package: com.ecommerce.productservice.service
```

#### File: `src/main/java/com/ecommerce/productservice/service/ProductSearchService.java`

**Prompt for Copilot:**
```
Create ProductSearchService.java with:
- @Service, @RequiredArgsConstructor, @Slf4j
- Inject: ProductSearchRepository, ProductRepository, ProductMapper
- Method: PageResponse<ProductResponse> searchProducts(ProductSearchRequest request)
  - Build Pageable with sort
  - If keyword exists, use findByNameContainingOrDescriptionContaining
  - If categoryId exists, filter by category
  - If brandId exists, filter by brand
  - If minPrice/maxPrice exists, filter by price range
  - Return PageResponse
- Method: void syncProductToElasticsearch(UUID productId)
  - Get product from ProductRepository
  - Map to ProductDocument
  - Save to ProductSearchRepository
- Method: void syncAllProducts()
  - Get all products from ProductRepository
  - Map each to ProductDocument
  - Save all to ProductSearchRepository
- Method: void deleteProductFromElasticsearch(UUID productId)
- Private method: ProductDocument mapToDocument(Product product)
  - Map all fields including category, brand, images
- Package: com.ecommerce.productservice.service
```

#### File: `src/main/java/com/ecommerce/productservice/service/ImageService.java`

**Prompt for Copilot:**
```
Create ImageService.java with:
- @Service, @RequiredArgsConstructor, @Slf4j
- Inject: MinioClient, MinioConfig
- Method: String uploadImage(MultipartFile file, String folder)
  - Generate unique filename with UUID
  - Upload to MinIO bucket
  - Return public URL: http://localhost:9000/{bucketName}/{folder}/{filename}
- Method: void deleteImage(String imageUrl)
  - Extract filename from URL
  - Delete from MinIO
- Private method: String generateFileName(String originalFilename)
  - UUID + original extension
- Private method: void ensureBucketExists()
  - Check if bucket exists, create if not
- Package: com.ecommerce.productservice.service
```

---

### PHASE 8: CONTROLLERS (~20 minutes - 3 files)

#### File: `src/main/java/com/ecommerce/productservice/controller/ProductController.java`

**Prompt for Copilot:**
```
Create ProductController.java with:
- @RestController, @RequestMapping("/api/products"), @RequiredArgsConstructor, @Slf4j
- Inject: ProductService, ProductSearchService
- @GetMapping - getAllProducts with @RequestParam page, size, sortBy, sortDirection
  - Return ResponseEntity<ApiResponse<PageResponse<ProductResponse>>>
- @GetMapping("/{id}") - getProductById
- @GetMapping("/slug/{slug}") - getProductBySlug
- @GetMapping("/featured") - getFeaturedProducts
- @GetMapping("/category/{categoryId}") - getProductsByCategory
- @GetMapping("/brand/{brandId}") - getProductsByBrand
- @GetMapping("/search") - searchProducts with ProductSearchRequest params
- @PostMapping - createProduct with @Valid @RequestBody CreateProductRequest
  - @PreAuthorize("hasRole('ADMIN')")
  - Return 201 CREATED
- @PutMapping("/{id}") - updateProduct
  - @PreAuthorize("hasRole('ADMIN')")
- @DeleteMapping("/{id}") - deleteProduct
  - @PreAuthorize("hasRole('ADMIN')")
  - Return 204 NO_CONTENT
- @PostMapping("/sync-elasticsearch") - syncAllProducts
  - @PreAuthorize("hasRole('ADMIN')")
- All responses wrapped in ApiResponse
- Package: com.ecommerce.productservice.controller
```

#### File: `src/main/java/com/ecommerce/productservice/controller/CategoryController.java`

**Prompt for Copilot:**
```
Create CategoryController.java with:
- @RestController, @RequestMapping("/api/categories"), @RequiredArgsConstructor, @Slf4j
- Inject: CategoryService
- @GetMapping - getAllCategories
- @GetMapping("/tree") - getRootCategories
- @GetMapping("/active") - getActiveCategories
- @GetMapping("/{id}") - getCategoryById
- @GetMapping("/slug/{slug}") - getCategoryBySlug
- @PostMapping - createCategory with @Valid @RequestBody
  - @PreAuthorize("hasRole('ADMIN')")
- @PutMapping("/{id}") - updateCategory
  - @PreAuthorize("hasRole('ADMIN')")
- @DeleteMapping("/{id}") - deleteCategory
  - @PreAuthorize("hasRole('ADMIN')")
- All responses wrapped in ApiResponse
- Package: com.ecommerce.productservice.controller
```

#### File: `src/main/java/com/ecommerce/productservice/controller/BrandController.java`

**Prompt for Copilot:**
```
Create BrandController.java with:
- @RestController, @RequestMapping("/api/brands"), @RequiredArgsConstructor, @Slf4j
- Inject: BrandService
- @GetMapping - getAllBrands
- @GetMapping("/active") - getActiveBrands
- @GetMapping("/{id}") - getBrandById
- @GetMapping("/slug/{slug}") - getBrandBySlug
- @PostMapping - createBrand with @Valid @RequestBody
  - @PreAuthorize("hasRole('ADMIN')")
- @PutMapping("/{id}") - updateBrand
  - @PreAuthorize("hasRole('ADMIN')")
- @DeleteMapping("/{id}") - deleteBrand
  - @PreAuthorize("hasRole('ADMIN')")
- All responses wrapped in ApiResponse
- Package: com.ecommerce.productservice.controller
```

---

### PHASE 7: OPTIONAL CONFIGS

#### File: `src/main/java/com/ecommerce/productservice/config/ElasticsearchConfig.java`

**Prompt for Copilot:**
```
Create ElasticsearchConfig.java with:
- @Configuration
- @EnableElasticsearchRepositories(basePackages = "com.ecommerce.productservice.repository")
- Extends ElasticsearchConfiguration
- @Value("${spring.elasticsearch.uris}") String elasticsearchUrl
- Override clientConfiguration() method
  - Return ClientConfiguration.builder().connectedTo(elasticsearchUrl without http://).build()
- Package: com.ecommerce.productservice.config
```

#### File: `src/main/java/com/ecommerce/productservice/config/MinioConfig.java`

**Prompt for Copilot:**
```
Create MinioConfig.java with:
- @Configuration
- @ConfigurationProperties(prefix = "minio")
- @Data
- Fields: String url, accessKey, secretKey, bucketName, Integer imageUrlExpiry
- @Bean MinioClient minioClient()
  - Return MinioClient.builder().endpoint(url).credentials(accessKey, secretKey).build()
- Package: com.ecommerce.productservice.config
```

---

## ✅ VERIFICATION CHECKLIST

After generating all files:

```bash
# 1. Check file count
find src/main/java -name "*.java" | wc -l
# Should show: 40 files

# 2. Verify all directories exist
ls -la src/main/java/com/ecommerce/productservice/

# 3. Build project
mvn clean install

# 4. Start infrastructure
docker-compose up -d

# 5. Wait for services to be ready (30 seconds)
sleep 30

# 6. Create MinIO bucket
# Open http://localhost:9001
# Login: minioadmin / minioadmin  
# Create bucket: ecommerce-products
# Set access to Public

# 7. Run service
mvn spring-boot:run

# 8. Test endpoint
curl http://localhost:8082/api/products
# Should return: {"success":true,"data":{"content":[],"page":0,...}}
```

---

## 📊 FILE CREATION PROGRESS TRACKER

Use this to track your progress:

```
Main Application & Configs (4 files):
[ ] ProductServiceApplication.java
[ ] SecurityConfig.java
[ ] ElasticsearchConfig.java
[ ] MinioConfig.java

Entities (5 files):
[ ] Product.java
[ ] Category.java
[ ] Brand.java
[ ] ProductImage.java
[ ] ProductVariant.java

Elasticsearch Document (1 file):
[ ] ProductDocument.java

Repositories (6 files):
[ ] ProductRepository.java
[ ] CategoryRepository.java
[ ] BrandRepository.java
[ ] ProductImageRepository.java
[ ] ProductVariantRepository.java
[ ] ProductSearchRepository.java

Exceptions (3 files):
[ ] ResourceNotFoundException.java
[ ] BadRequestException.java
[ ] GlobalExceptionHandler.java

Response DTOs (7 files):
[ ] ApiResponse.java
[ ] PageResponse.java
[ ] ProductResponse.java
[ ] CategoryResponse.java
[ ] BrandResponse.java
[ ] ProductImageResponse.java
[ ] ProductVariantResponse.java

Request DTOs (8 files):
[ ] CreateProductRequest.java
[ ] UpdateProductRequest.java
[ ] CreateCategoryRequest.java
[ ] UpdateCategoryRequest.java
[ ] CreateBrandRequest.java
[ ] UpdateBrandRequest.java
[ ] CreateProductVariantRequest.java
[ ] ProductSearchRequest.java

Mapper (1 file):
[ ] ProductMapper.java

Services (5 files):
[ ] ProductService.java
[ ] CategoryService.java
[ ] BrandService.java
[ ] ProductSearchService.java
[ ] ImageService.java

Controllers (3 files):
[ ] ProductController.java
[ ] CategoryController.java
[ ] BrandController.java

Total Progress: 0/40 files created
```

---

## 🎯 TIPS FOR GITHUB COPILOT

1. **Start fresh** - Make sure no Java files exist yet in src/main/java
2. **Create directories first** - Run: `mkdir -p src/main/java/com/ecommerce/productservice/{config,entity,document,repository,dto/{request,response},service,controller,mapper,exception}`
3. **Follow the exact order** - Phase 0 → Phase 1 → Phase 2... (Don't skip!)
4. **One file at a time** - Create and verify each file before moving to next
5. **Copy prompts exactly** - Each prompt has all necessary details
6. **Review generated code** - Check package names and imports
7. **Save frequently** - Don't lose your work!
8. **Use Tab to accept** - Review Copilot suggestions before accepting
9. **If generation fails** - Delete the file, restart VS Code, try again
10. **Track progress** - Use the checklist above to mark completed files

### Common Issues:

**Issue: Copilot doesn't generate anything**
- Solution: Make sure you're in the correct file with correct package declaration
- Try: Type the package line first, then prompt in comments

**Issue: Missing imports**
- Solution: Let Copilot auto-import or add manually
- Common imports: `import lombok.*`, `import jakarta.persistence.*`

**Issue: Wrong package name**
- Solution: Delete file, ensure correct directory structure, recreate

**Issue: Compilation errors**
- Solution: Make sure entities are created before repositories
- Make sure DTOs are created before services
- Follow the phase order!

---

## 📚 REFERENCE DOCUMENTS

- `README.md` - Full documentation
- `API-CONTRACT.md` - All API endpoints and TypeScript types
- Existing entities in `entity/` package - Reference for field names and relationships
- Existing repositories in `repository/` package - Reference for query methods

---

## 🚀 AFTER COMPLETION

Once all 40 files are created:

1. **Create MinIO Bucket:**
   - Open http://localhost:9001
   - Login: minioadmin / minioadmin
   - Create bucket: `ecommerce-products`
   - Set access to Public

2. **Test APIs:**
   ```bash
   # Get all products (public)
   curl http://localhost:8082/api/products
   
   # Create product (needs JWT)
   curl -X POST http://localhost:8082/api/products \
     -H "Authorization: Bearer $TOKEN" \
     -H "Content-Type: application/json" \
     -d '{"name":"Test Product","description":"Test description","price":100,"stockQuantity":10}'
   ```

3. **Next Steps:**
   - Integrate with User Service for authentication
   - Create frontend UI
   - Add unit tests
   - Deploy to production

---

**Good luck! 🚀**