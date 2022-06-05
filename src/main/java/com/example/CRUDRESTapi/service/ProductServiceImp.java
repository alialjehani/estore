package com.example.CRUDRESTapi.service;

import com.example.CRUDRESTapi.exception.ResourceNotFoundException;
import com.example.CRUDRESTapi.mapper.ProductMapper;
import com.example.CRUDRESTapi.repository.ProductRepository;
import com.example.CRUDRESTapi.repository.model.JpaProduct;
import com.example.CRUDRESTapi.service.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class ProductServiceImp implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public ProductDto createProduct(ProductDto employeeDto) {
        JpaProduct jpaProduct = productRepository.save(
                ProductMapper.INSTANCE.productDtoToEntity(employeeDto));
        return ProductMapper.INSTANCE.EntityToDto(jpaProduct);

    }
    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        Optional<JpaProduct> productId = this.productRepository.findById(productDto.getId());
        if (productId.isPresent()) {
            JpaProduct jpaProduct = productRepository.save(
                    ProductMapper.INSTANCE.productDtoToEntity(productDto));
            return ProductMapper.INSTANCE.EntityToDto(jpaProduct);
        } else {
            throw new ResourceNotFoundException("The Resource is not found");
        }
    }
    @Override
    public List<ProductDto> getAllProduct(ProductDto productDto) {
        List<JpaProduct> jpaProductList = new ArrayList<>();
        jpaProductList = productRepository.findAll();
        return   ProductMapper.INSTANCE.EntityListToDto(jpaProductList);
    }
    @Override
    public ProductDto getProductById(long productId) {
        return null;
    }
    @Override
    public void deleteProduct(long productId) {
        Optional<JpaProduct> productDB = this.productRepository.findById(productId);
        if(productDB.isPresent()){
            this.productRepository.delete(productDB.get());
        }else{
            throw new ResourceNotFoundException("Resource not found with id:"+productId);
        }
    }
}