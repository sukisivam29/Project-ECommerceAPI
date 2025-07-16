package com.practice.springdemo.controller;

import com.practice.springdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {
    
    @Autowired
    private ProductService productService;
    
    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();
        
        // Get basic statistics
        List<String> categories = productService.getAllCategories();
        int totalProducts = productService.getAllProducts().size();
        int productsInStock = productService.getProductsInStock().size();
        
        stats.put("totalProducts", totalProducts);
        stats.put("productsInStock", productsInStock);
        stats.put("totalCategories", categories.size());
        stats.put("categories", categories);
        
        // Calculate out of stock products
        stats.put("outOfStockProducts", totalProducts - productsInStock);
        
        return ResponseEntity.ok(stats);
    }
    
    @GetMapping("/welcome")
    public ResponseEntity<Map<String, String>> getWelcomeMessage() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Welcome to the Ecommerce Backend API!");
        response.put("version", "1.0.0");
        response.put("status", "running");
        return ResponseEntity.ok(response);
    }
}