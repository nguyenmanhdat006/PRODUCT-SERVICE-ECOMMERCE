-- =====================================================
-- Product Service - Initial Database Schema
-- Version: 1.0.0
-- Description: Creates all tables with indexes for Product Catalog Service
-- =====================================================

-- Enable UUID extension
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- =====================================================
-- BRANDS TABLE
-- =====================================================
CREATE TABLE brands (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(100) NOT NULL,
    slug VARCHAR(120) NOT NULL UNIQUE,
    description TEXT,
    logo_url VARCHAR(500),
    website_url VARCHAR(500),
    active BOOLEAN NOT NULL DEFAULT TRUE,
    meta_title VARCHAR(150),
    meta_description VARCHAR(300),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Indexes for brands
CREATE INDEX idx_brand_slug ON brands(slug);
CREATE INDEX idx_brand_active ON brands(active);

-- =====================================================
-- CATEGORIES TABLE
-- =====================================================
CREATE TABLE categories (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(100) NOT NULL,
    slug VARCHAR(120) NOT NULL UNIQUE,
    description TEXT,
    parent_id UUID,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    display_order INTEGER,
    image_url VARCHAR(500),
    meta_title VARCHAR(150),
    meta_description VARCHAR(300),
    meta_keywords VARCHAR(200),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (parent_id) REFERENCES categories(id) ON DELETE CASCADE
);

-- Indexes for categories
CREATE INDEX idx_category_slug ON categories(slug);
CREATE INDEX idx_category_parent ON categories(parent_id);
CREATE INDEX idx_category_active ON categories(active);

-- =====================================================
-- PRODUCTS TABLE
-- =====================================================
CREATE TABLE products (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(200) NOT NULL,
    slug VARCHAR(220) NOT NULL UNIQUE,
    description TEXT,
    short_description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    compare_at_price DECIMAL(10, 2),
    cost_price DECIMAL(10, 2),
    category_id UUID,
    brand_id UUID,
    status VARCHAR(50) NOT NULL DEFAULT 'DRAFT',
    published BOOLEAN NOT NULL DEFAULT FALSE,
    featured BOOLEAN NOT NULL DEFAULT FALSE,
    stock_quantity INTEGER NOT NULL DEFAULT 0,
    sku VARCHAR(50),
    barcode VARCHAR(100),
    weight DECIMAL(5, 2),
    weight_unit VARCHAR(20),
    length DECIMAL(10, 2),
    width DECIMAL(10, 2),
    height DECIMAL(10, 2),
    dimension_unit VARCHAR(20),
    meta_title VARCHAR(150),
    meta_description VARCHAR(300),
    meta_keywords VARCHAR(200),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    published_at TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE SET NULL,
    FOREIGN KEY (brand_id) REFERENCES brands(id) ON DELETE SET NULL
);

-- Indexes for products
CREATE INDEX idx_product_slug ON products(slug);
CREATE INDEX idx_product_status ON products(status);
CREATE INDEX idx_product_category ON products(category_id);
CREATE INDEX idx_product_brand ON products(brand_id);
CREATE INDEX idx_product_published ON products(published);
CREATE INDEX idx_product_featured ON products(featured);

-- =====================================================
-- PRODUCT_IMAGES TABLE
-- =====================================================
CREATE TABLE product_images (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    product_id UUID NOT NULL,
    image_url VARCHAR(500) NOT NULL,
    alt_text VARCHAR(100),
    is_primary BOOLEAN NOT NULL DEFAULT FALSE,
    display_order INTEGER NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

-- Indexes for product_images
CREATE INDEX idx_product_image_product ON product_images(product_id);
CREATE INDEX idx_product_image_primary ON product_images(product_id, is_primary);

-- =====================================================
-- PRODUCT_VARIANTS TABLE
-- =====================================================
CREATE TABLE product_variants (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    product_id UUID NOT NULL,
    sku VARCHAR(100) NOT NULL UNIQUE,
    size VARCHAR(50),
    color VARCHAR(50),
    material VARCHAR(50),
    style VARCHAR(100),
    stock_quantity INTEGER NOT NULL DEFAULT 0,
    price_adjustment DECIMAL(10, 2),
    image_url VARCHAR(500),
    weight DECIMAL(5, 2),
    barcode VARCHAR(100),
    available BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

-- Indexes for product_variants
CREATE INDEX idx_variant_product ON product_variants(product_id);
CREATE UNIQUE INDEX idx_variant_sku ON product_variants(sku);

-- =====================================================
-- COMMENTS
-- =====================================================
COMMENT ON TABLE brands IS 'Stores product brand information';
COMMENT ON TABLE categories IS 'Stores product categories with hierarchical structure';
COMMENT ON TABLE products IS 'Main products table with all product details';
COMMENT ON TABLE product_images IS 'Multiple images for each product';
COMMENT ON TABLE product_variants IS 'Product variations (size, color, etc.)';

COMMENT ON COLUMN categories.parent_id IS 'Self-referencing foreign key for category hierarchy';
COMMENT ON COLUMN products.status IS 'Product status: DRAFT, PUBLISHED, ARCHIVED, OUT_OF_STOCK';
COMMENT ON COLUMN products.published IS 'Whether product is visible to customers';
COMMENT ON COLUMN products.featured IS 'Whether product is featured on homepage';
COMMENT ON COLUMN product_images.is_primary IS 'Primary image shown in product listings';
COMMENT ON COLUMN product_variants.price_adjustment IS 'Price difference from base product price';

