package com.feedback.productservice.service;

import com.feedback.productservice.dtos.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    ProductDto getProductById(int productId);
    List<ProductDto> getAllProducts();
    void deleteProduct(int productId);
}
