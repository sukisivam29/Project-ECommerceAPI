package com.practice.springdemo.service;

import com.practice.springdemo.dto.ProductDTO;
import com.practice.springdemo.entity.Product;
import com.practice.springdemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    // Get all active products
    public List<ProductDTO> getAllProducts() {
        return productRepository.findByActiveTrue()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    // Get products with pagination
    public Page<ProductDTO> getAllProducts(int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ? 
                   Sort.by(sortBy).descending() : 
                   Sort.by(sortBy).ascending();
        
        Pageable pageable = PageRequest.of(page, size, sort);
        return productRepository.findByActiveTrue(pageable)
                .map(this::convertToDTO);
    }
    
    // Get product by ID
    public Optional<ProductDTO> getProductById(Long id) {
        return productRepository.findById(id)
                .filter(Product::getActive)
                .map(this::convertToDTO);
    }
    
    // Create new product
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return convertToDTO(savedProduct);
    }
    
    // Update existing product
    public Optional<ProductDTO> updateProduct(Long id, ProductDTO productDTO) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(productDTO.getName());
                    existingProduct.setDescription(productDTO.getDescription());
                    existingProduct.setPrice(productDTO.getPrice());
                    existingProduct.setStockQuantity(productDTO.getStockQuantity());
                    existingProduct.setCategory(productDTO.getCategory());
                    existingProduct.setImageUrl(productDTO.getImageUrl());
                    existingProduct.setActive(productDTO.getActive());
                    
                    Product updatedProduct = productRepository.save(existingProduct);
                    return convertToDTO(updatedProduct);
                });
    }
    
    // Soft delete product (set active to false)
    public boolean deleteProduct(Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setActive(false);
                    productRepository.save(product);
                    return true;
                })
                .orElse(false);
    }
    
    // Get products by category
    public List<ProductDTO> getProductsByCategory(String category) {
        return productRepository.findByCategoryAndActiveTrue(category)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    // Search products by name
    public List<ProductDTO> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCaseAndActiveTrue(name)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    // Search products with pagination
    public Page<ProductDTO> searchProducts(String searchTerm, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.searchProducts(searchTerm, pageable)
                .map(this::convertToDTO);
    }
    
    // Get products by price range
    public List<ProductDTO> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findByPriceBetweenAndActiveTrue(minPrice, maxPrice)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    // Get products in stock
    public List<ProductDTO> getProductsInStock() {
        return productRepository.findByStockQuantityGreaterThanAndActiveTrue(0)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    // Get all categories
    public List<String> getAllCategories() {
        return productRepository.findDistinctCategories();
    }
    
    // Update stock quantity
    public boolean updateStock(Long productId, Integer newQuantity) {
        return productRepository.findById(productId)
                .map(product -> {
                    product.setStockQuantity(newQuantity);
                    productRepository.save(product);
                    return true;
                })
                .orElse(false);
    }
    
    // Convert Entity to DTO
    private ProductDTO convertToDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setStockQuantity(product.getStockQuantity());
        dto.setCategory(product.getCategory());
        dto.setImageUrl(product.getImageUrl());
        dto.setActive(product.getActive());
        return dto;
    }
    
    // Convert DTO to Entity
    private Product convertToEntity(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStockQuantity(dto.getStockQuantity());
        product.setCategory(dto.getCategory());
        product.setImageUrl(dto.getImageUrl());
        product.setActive(dto.getActive() != null ? dto.getActive() : true);
        return product;
    }
}