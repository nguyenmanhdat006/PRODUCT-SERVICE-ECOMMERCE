-- =====================================================
-- Product Service - Sample Data
-- Version: 1.0.0
-- Description: Inserts sample brands, categories, and products for testing
-- =====================================================

-- =====================================================
-- SAMPLE BRANDS
-- =====================================================
INSERT INTO brands (id, name, slug, description, active, created_at, updated_at) VALUES
('550e8400-e29b-41d4-a716-446655440001', 'Nike', 'nike', 'Just Do It - Leading athletic footwear and apparel brand', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('550e8400-e29b-41d4-a716-446655440002', 'Adidas', 'adidas', 'Impossible is Nothing - Global sports brand', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('550e8400-e29b-41d4-a716-446655440003', 'Puma', 'puma', 'Forever Faster - Sports lifestyle brand', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('550e8400-e29b-41d4-a716-446655440004', 'Zara', 'zara', 'Fast fashion retail brand', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('550e8400-e29b-41d4-a716-446655440005', 'H&M', 'h-m', 'Fashion and quality at the best price', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- =====================================================
-- SAMPLE CATEGORIES (Hierarchical)
-- =====================================================

-- Parent Categories
INSERT INTO categories (id, name, slug, description, parent_id, active, display_order, created_at, updated_at) VALUES
('650e8400-e29b-41d4-a716-446655440001', 'Men''s Fashion', 'mens-fashion', 'Fashion items for men', NULL, TRUE, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('650e8400-e29b-41d4-a716-446655440002', 'Women''s Fashion', 'womens-fashion', 'Fashion items for women', NULL, TRUE, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('650e8400-e29b-41d4-a716-446655440003', 'Kids Fashion', 'kids-fashion', 'Fashion items for kids', NULL, TRUE, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Children Categories - Men's Fashion
INSERT INTO categories (id, name, slug, description, parent_id, active, display_order, created_at, updated_at) VALUES
('650e8400-e29b-41d4-a716-446655440011', 'Men''s Shirts', 'mens-shirts', 'Shirts and tops for men', '650e8400-e29b-41d4-a716-446655440001', TRUE, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('650e8400-e29b-41d4-a716-446655440012', 'Men''s Pants', 'mens-pants', 'Pants and trousers for men', '650e8400-e29b-41d4-a716-446655440001', TRUE, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('650e8400-e29b-41d4-a716-446655440013', 'Men''s Shoes', 'mens-shoes', 'Footwear for men', '650e8400-e29b-41d4-a716-446655440001', TRUE, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Children Categories - Women's Fashion
INSERT INTO categories (id, name, slug, description, parent_id, active, display_order, created_at, updated_at) VALUES
('650e8400-e29b-41d4-a716-446655440021', 'Women''s Dresses', 'womens-dresses', 'Dresses for women', '650e8400-e29b-41d4-a716-446655440002', TRUE, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('650e8400-e29b-41d4-a716-446655440022', 'Women''s Tops', 'womens-tops', 'Tops and blouses for women', '650e8400-e29b-41d4-a716-446655440002', TRUE, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('650e8400-e29b-41d4-a716-446655440023', 'Women''s Shoes', 'womens-shoes', 'Footwear for women', '650e8400-e29b-41d4-a716-446655440002', TRUE, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Grandchildren Categories - Men's Shoes
INSERT INTO categories (id, name, slug, description, parent_id, active, display_order, created_at, updated_at) VALUES
('650e8400-e29b-41d4-a716-446655440031', 'Men''s Sneakers', 'mens-sneakers', 'Athletic and casual sneakers', '650e8400-e29b-41d4-a716-446655440013', TRUE, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('650e8400-e29b-41d4-a716-446655440032', 'Men''s Boots', 'mens-boots', 'Boots for men', '650e8400-e29b-41d4-a716-446655440013', TRUE, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- =====================================================
-- SAMPLE PRODUCTS
-- =====================================================

-- Nike Air Max 90
INSERT INTO products (id, name, slug, description, short_description, price, compare_at_price, category_id, brand_id, status, published, featured, stock_quantity, sku, created_at, updated_at, published_at) VALUES
('750e8400-e29b-41d4-a716-446655440001',
 'Nike Air Max 90',
 'nike-air-max-90',
 'The Nike Air Max 90 stays true to its OG running roots with the iconic Waffle outsole, stitched overlays and classic TPU accents. Classic colors celebrate your fresh look while Max Air cushioning adds comfort to the journey.',
 'Classic Nike sneaker with Air cushioning',
 120.00,
 150.00,
 '650e8400-e29b-41d4-a716-446655440031',
 '550e8400-e29b-41d4-a716-446655440001',
 'PUBLISHED',
 TRUE,
 TRUE,
 50,
 'NIKE-AM90-001',
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP);

-- Adidas Ultraboost
INSERT INTO products (id, name, slug, description, short_description, price, category_id, brand_id, status, published, featured, stock_quantity, sku, created_at, updated_at, published_at) VALUES
('750e8400-e29b-41d4-a716-446655440002',
 'Adidas Ultraboost 22',
 'adidas-ultraboost-22',
 'Made with a series of recycled materials, this upper features at least 50% recycled content. The Ultraboost 22 delivers incredible energy return with every step.',
 'Premium running shoes with Boost technology',
 180.00,
 '650e8400-e29b-41d4-a716-446655440031',
 '550e8400-e29b-41d4-a716-446655440002',
 'PUBLISHED',
 TRUE,
 TRUE,
 30,
 'ADIDAS-UB22-001',
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP);

-- Nike T-Shirt
INSERT INTO products (id, name, slug, description, short_description, price, category_id, brand_id, status, published, featured, stock_quantity, sku, created_at, updated_at, published_at) VALUES
('750e8400-e29b-41d4-a716-446655440003',
 'Nike Sportswear Essential T-Shirt',
 'nike-sportswear-essential-t-shirt',
 'The Nike Sportswear Essential T-Shirt is made from 100% cotton for a soft feel. The relaxed fit gives you plenty of room to move.',
 'Comfortable cotton t-shirt',
 25.00,
 '650e8400-e29b-41d4-a716-446655440011',
 '550e8400-e29b-41d4-a716-446655440001',
 'PUBLISHED',
 TRUE,
 FALSE,
 100,
 'NIKE-TSHIRT-001',
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP);

-- Zara Dress
INSERT INTO products (id, name, slug, description, short_description, price, category_id, brand_id, status, published, featured, stock_quantity, sku, created_at, updated_at, published_at) VALUES
('750e8400-e29b-41d4-a716-446655440004',
 'Zara Floral Print Midi Dress',
 'zara-floral-print-midi-dress',
 'Midi dress featuring a round neckline with ties and short sleeves. Floral print. Side zip closure.',
 'Elegant floral midi dress',
 49.99,
 '650e8400-e29b-41d4-a716-446655440021',
 '550e8400-e29b-41d4-a716-446655440004',
 'PUBLISHED',
 TRUE,
 TRUE,
 40,
 'ZARA-DRESS-001',
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP);

-- H&M Jeans
INSERT INTO products (id, name, slug, description, short_description, price, category_id, brand_id, status, published, stock_quantity, sku, created_at, updated_at, published_at) VALUES
('750e8400-e29b-41d4-a716-446655440005',
 'H&M Slim Fit Jeans',
 'h-m-slim-fit-jeans',
 '5-pocket jeans in washed stretch denim with a regular waist, zip fly with button, and slim legs.',
 'Classic slim fit denim jeans',
 39.99,
 '650e8400-e29b-41d4-a716-446655440012',
 '550e8400-e29b-41d4-a716-446655440005',
 'PUBLISHED',
 TRUE,
 60,
 'HM-JEANS-001',
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP);

-- Draft Product (not published)
INSERT INTO products (id, name, slug, description, short_description, price, category_id, brand_id, status, published, stock_quantity, sku, created_at, updated_at) VALUES
('750e8400-e29b-41d4-a716-446655440006',
 'Puma Future Rider',
 'puma-future-rider',
 'A modern take on the classic running silhouette with bold colors.',
 'Retro-inspired sneakers',
 85.00,
 '650e8400-e29b-41d4-a716-446655440031',
 '550e8400-e29b-41d4-a716-446655440003',
 'DRAFT',
 FALSE,
 0,
 'PUMA-FR-001',
 CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP);

-- =====================================================
-- SAMPLE PRODUCT IMAGES
-- =====================================================

-- Nike Air Max 90 Images
INSERT INTO product_images (id, product_id, image_url, alt_text, is_primary, display_order, created_at) VALUES
('850e8400-e29b-41d4-a716-446655440001', '750e8400-e29b-41d4-a716-446655440001', 'https://picsum.photos/seed/nike1/800/800', 'Nike Air Max 90 - Front View', TRUE, 1, CURRENT_TIMESTAMP),
('850e8400-e29b-41d4-a716-446655440002', '750e8400-e29b-41d4-a716-446655440001', 'https://picsum.photos/seed/nike2/800/800', 'Nike Air Max 90 - Side View', FALSE, 2, CURRENT_TIMESTAMP),
('850e8400-e29b-41d4-a716-446655440003', '750e8400-e29b-41d4-a716-446655440001', 'https://picsum.photos/seed/nike3/800/800', 'Nike Air Max 90 - Back View', FALSE, 3, CURRENT_TIMESTAMP);

-- Adidas Ultraboost Images
INSERT INTO product_images (id, product_id, image_url, alt_text, is_primary, display_order, created_at) VALUES
('850e8400-e29b-41d4-a716-446655440011', '750e8400-e29b-41d4-a716-446655440002', 'https://picsum.photos/seed/adidas1/800/800', 'Adidas Ultraboost 22', TRUE, 1, CURRENT_TIMESTAMP),
('850e8400-e29b-41d4-a716-446655440012', '750e8400-e29b-41d4-a716-446655440002', 'https://picsum.photos/seed/adidas2/800/800', 'Adidas Ultraboost 22 - Side', FALSE, 2, CURRENT_TIMESTAMP);

-- Nike T-Shirt Images
INSERT INTO product_images (id, product_id, image_url, alt_text, is_primary, display_order, created_at) VALUES
('850e8400-e29b-41d4-a716-446655440021', '750e8400-e29b-41d4-a716-446655440003', 'https://picsum.photos/seed/tshirt1/800/800', 'Nike T-Shirt', TRUE, 1, CURRENT_TIMESTAMP);

-- Zara Dress Images
INSERT INTO product_images (id, product_id, image_url, alt_text, is_primary, display_order, created_at) VALUES
('850e8400-e29b-41d4-a716-446655440031', '750e8400-e29b-41d4-a716-446655440004', 'https://picsum.photos/seed/dress1/800/800', 'Zara Floral Dress', TRUE, 1, CURRENT_TIMESTAMP),
('850e8400-e29b-41d4-a716-446655440032', '750e8400-e29b-41d4-a716-446655440004', 'https://picsum.photos/seed/dress2/800/800', 'Zara Floral Dress - Detail', FALSE, 2, CURRENT_TIMESTAMP);

-- H&M Jeans Images
INSERT INTO product_images (id, product_id, image_url, alt_text, is_primary, display_order, created_at) VALUES
('850e8400-e29b-41d4-a716-446655440041', '750e8400-e29b-41d4-a716-446655440005', 'https://picsum.photos/seed/jeans1/800/800', 'H&M Slim Fit Jeans', TRUE, 1, CURRENT_TIMESTAMP);

-- =====================================================
-- SAMPLE PRODUCT VARIANTS
-- =====================================================

-- Nike Air Max 90 Variants
INSERT INTO product_variants (id, product_id, sku, size, color, stock_quantity, price_adjustment, available, created_at, updated_at) VALUES
('950e8400-e29b-41d4-a716-446655440001', '750e8400-e29b-41d4-a716-446655440001', 'NIKE-AM90-001-US8-WHT', 'US 8', 'White', 10, 0.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('950e8400-e29b-41d4-a716-446655440002', '750e8400-e29b-41d4-a716-446655440001', 'NIKE-AM90-001-US9-WHT', 'US 9', 'White', 15, 0.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('950e8400-e29b-41d4-a716-446655440003', '750e8400-e29b-41d4-a716-446655440001', 'NIKE-AM90-001-US10-WHT', 'US 10', 'White', 12, 0.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('950e8400-e29b-41d4-a716-446655440004', '750e8400-e29b-41d4-a716-446655440001', 'NIKE-AM90-001-US8-BLK', 'US 8', 'Black', 8, 5.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('950e8400-e29b-41d4-a716-446655440005', '750e8400-e29b-41d4-a716-446655440001', 'NIKE-AM90-001-US9-BLK', 'US 9', 'Black', 5, 5.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Nike T-Shirt Variants
INSERT INTO product_variants (id, product_id, sku, size, color, stock_quantity, price_adjustment, available, created_at, updated_at) VALUES
('950e8400-e29b-41d4-a716-446655440011', '750e8400-e29b-41d4-a716-446655440003', 'NIKE-TSHIRT-001-S-WHT', 'S', 'White', 30, 0.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('950e8400-e29b-41d4-a716-446655440012', '750e8400-e29b-41d4-a716-446655440003', 'NIKE-TSHIRT-001-M-WHT', 'M', 'White', 40, 0.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('950e8400-e29b-41d4-a716-446655440013', '750e8400-e29b-41d4-a716-446655440003', 'NIKE-TSHIRT-001-L-WHT', 'L', 'White', 20, 0.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('950e8400-e29b-41d4-a716-446655440014', '750e8400-e29b-41d4-a716-446655440003', 'NIKE-TSHIRT-001-XL-WHT', 'XL', 'White', 10, 0.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Zara Dress Variants
INSERT INTO product_variants (id, product_id, sku, size, color, stock_quantity, price_adjustment, available, created_at, updated_at) VALUES
('950e8400-e29b-41d4-a716-446655440021', '750e8400-e29b-41d4-a716-446655440004', 'ZARA-DRESS-001-S-FLR', 'S', 'Floral', 15, 0.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('950e8400-e29b-41d4-a716-446655440022', '750e8400-e29b-41d4-a716-446655440004', 'ZARA-DRESS-001-M-FLR', 'M', 'Floral', 15, 0.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('950e8400-e29b-41d4-a716-446655440023', '750e8400-e29b-41d4-a716-446655440004', 'ZARA-DRESS-001-L-FLR', 'L', 'Floral', 10, 0.00, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

