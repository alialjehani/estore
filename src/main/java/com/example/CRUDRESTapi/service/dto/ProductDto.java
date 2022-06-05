package com.example.CRUDRESTapi.service.dto;

import com.example.CRUDRESTapi.service.dto.validate.UpdateEmployeeValidation;
import com.example.CRUDRESTapi.service.dto.validate.UpdateProductValidation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @Min(groups = {UpdateProductValidation.class}, value = 1, message = "please enter your ID")
    @NotNull (groups = {UpdateProductValidation.class})
    private long id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private BigDecimal price;
    private String description;
    }


