package com.practice.springdemo.config;

import com.practice.springdemo.entity.Product;
import com.practice.springdemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Initialize with sample data if database is empty
        if (productRepository.count() == 0) {
            initializeSampleProducts();
        }
    }
    
    private void initializeSampleProducts() {
        // Electronics
        productRepository.save(new Product(
                "iPhone 15 Pro", 
                "Latest Apple smartphone with advanced camera system", 
                new BigDecimal("999.99"), 
                50, 
                "Electronics"
        ));
        
        productRepository.save(new Product(
                "Samsung Galaxy S24", 
                "Premium Android smartphone with AI features", 
                new BigDecimal("899.99"), 
                30, 
                "Electronics"
        ));
        
        productRepository.save(new Product(
                "MacBook Air M3", 
                "Lightweight laptop with Apple M3 chip", 
                new BigDecimal("1299.99"), 
                25, 
                "Electronics"
        ));
        
        // Clothing
        productRepository.save(new Product(
                "Classic Denim Jacket", 
                "Timeless denim jacket for casual wear", 
                new BigDecimal("79.99"), 
                100, 
                "Clothing"
        ));
        
        productRepository.save(new Product(
                "Cotton T-Shirt", 
                "Comfortable 100% cotton t-shirt", 
                new BigDecimal("19.99"), 
                200, 
                "Clothing"
        ));
        
        // Books
        productRepository.save(new Product(
                "Spring Boot in Action", 
                "Comprehensive guide to Spring Boot development", 
                new BigDecimal("45.99"), 
                75, 
                "Books"
        ));
        
        productRepository.save(new Product(
                "Clean Code", 
                "A handbook of agile software craftsmanship", 
                new BigDecimal("42.99"), 
                60, 
                "Books"
        ));
        
        // Home & Garden
        productRepository.save(new Product(
                "Coffee Maker", 
                "Programmable coffee maker with thermal carafe", 
                new BigDecimal("129.99"), 
                40, 
                "Home & Garden"
        ));
        
        productRepository.save(new Product(
                "Indoor Plant Set", 
                "Collection of easy-care indoor plants", 
                new BigDecimal("34.99"), 
                80, 
                "Home & Garden"
        ));
        
        // Sports
        productRepository.save(new Product(
                "Yoga Mat", 
                "Non-slip yoga mat for exercise and meditation", 
                new BigDecimal("29.99"), 
                120, 
                "Sports"
        ));
        
        System.out.println("Sample products initialized successfully!");
    }
}