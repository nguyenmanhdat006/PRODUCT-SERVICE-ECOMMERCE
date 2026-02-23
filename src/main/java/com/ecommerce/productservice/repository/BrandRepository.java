package com.ecommerce.productservice.repository;

import com.ecommerce.productservice.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BrandRepository extends JpaRepository<Brand, UUID> {

    Optional<Brand> findBySlug(String slug);

    List<Brand> findByActiveTrue();

    boolean existsBySlug(String slug);
}

