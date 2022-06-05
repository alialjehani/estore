package com.example.CRUDRESTapi.controller;

import com.example.CRUDRESTapi.service.ProductService;
import com.example.CRUDRESTapi.service.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("api/products")
    public ResponseEntity<List<ProductDto>> getAllProduct(ProductDto productDto){
        return ResponseEntity.ok().body(productService.getAllProduct(productDto));
    }
    @GetMapping("api/products/{id}")
    public ResponseEntity<ProductDto> getProductByID(@PathVariable long id){
        return ResponseEntity.ok().body(productService.getProductById(id));
    }
    @PostMapping("api/products")
    public ResponseEntity<ProductDto>createProduct(@RequestBody ProductDto productDto){
        return ResponseEntity.ok(this.productService.createProduct(productDto));
    }
   @PutMapping("api/products/{id}")
   public ResponseEntity<ProductDto>updateProduct(@PathVariable long id, @RequestBody ProductDto productDto){
        productDto.setId(id);
        return ResponseEntity.ok().body(this.productService.updateProduct(productDto));
   }
    @DeleteMapping("api/products/{id}")
    public HttpStatus deleteProduct(@PathVariable long id){
        this.productService.deleteProduct(id);
        return HttpStatus.OK;
    }
}
