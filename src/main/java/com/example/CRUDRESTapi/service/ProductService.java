package com.example.CRUDRESTapi.service;

import com.example.CRUDRESTapi.service.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto productDto);
    ProductDto updateProduct(ProductDto productDto);
    List<ProductDto> getAllProduct(ProductDto productDto);
    ProductDto getProductById(long productId);
    void deleteProduct(long id);
}
