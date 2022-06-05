package com.example.CRUDRESTapi.mapper;

import com.example.CRUDRESTapi.repository.model.JpaProduct;
import com.example.CRUDRESTapi.service.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper  {
        ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
        JpaProduct productDtoToEntity(ProductDto productDto);
        ProductDto EntityToDto(JpaProduct jpaProduct);

        List<ProductDto> EntityListToDto(List<JpaProduct> jpaProduct);
    }
