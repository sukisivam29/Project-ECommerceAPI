package com.practice.springdemo.repository;

import com.practice.springdemo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    // Find active products
    List<Product> findByActiveTrue();
    
    // Find products by category
    List<Product> findByCategoryAndActiveTrue(String category);
    
    // Find products by name containing (case insensitive)
    List<Product> findByNameContainingIgnoreCaseAndActiveTrue(String name);
    
    // Find products in stock
    List<Product> findByStockQuantityGreaterThanAndActiveTrue(Integer quantity);
    
    // Find products by price range
    List<Product> findByPriceBetweenAndActiveTrue(BigDecimal minPrice, BigDecimal maxPrice);
    
    // Get all categories
    @Query("SELECT DISTINCT p.category FROM Product p WHERE p.active = true")
    List<String> findDistinctCategories();
    
    // Search products with pagination
    @Query("SELECT p FROM Product p WHERE " +
           "(LOWER(p.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(p.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) AND " +
           "p.active = true")
    Page<Product> searchProducts(@Param("searchTerm") String searchTerm, Pageable pageable);
    
    // Find products by category with pagination
    Page<Product> findByCategoryAndActiveTrue(String category, Pageable pageable);
    
    // Find all active products with pagination
    Page<Product> findByActiveTrue(Pageable pageable);
}