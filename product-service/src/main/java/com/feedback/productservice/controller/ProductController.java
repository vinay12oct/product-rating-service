package com.feedback.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.feedback.productservice.dtos.ProductDto;
import com.feedback.productservice.payload.ApiResponse;
import com.feedback.productservice.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse> createProduct(@Valid @RequestBody ProductDto productDto) {
        try {
            ProductDto created = productService.createProduct(productDto);
            ApiResponse response = new ApiResponse("Product created successfully", true, HttpStatus.CREATED.value(), created);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse("Failed to create product: " + e.getMessage(), false, HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET BY ID
    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable int productId) {
        try {
            ProductDto product = productService.getProductById(productId);
            ApiResponse response = new ApiResponse("Product fetched successfully", true, HttpStatus.OK.value(), product);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse("Product not found with ID: " + productId, false, HttpStatus.NOT_FOUND.value(), null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<ApiResponse> getAllProducts() {
        try {
            List<ProductDto> products = productService.getAllProducts();
            ApiResponse response = new ApiResponse("Products fetched successfully", true, HttpStatus.OK.value(), products);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse("Failed to fetch products: " + e.getMessage(), false, HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // DELETE
    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable int productId) {
        try {
            productService.deleteProduct(productId);
            ApiResponse response = new ApiResponse("Product deleted successfully", true, HttpStatus.OK.value(), null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse("Failed to delete product: " + e.getMessage(), false, HttpStatus.NOT_FOUND.value(), null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
