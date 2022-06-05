package com.example.CRUDRESTapi.service.dto;

import com.example.CRUDRESTapi.service.dto.validate.UpdateEmployeeValidation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    @Min(groups = {UpdateEmployeeValidation.class}, value = 1, message = "Please Enter Id")
    @NotNull(groups = {UpdateEmployeeValidation.class}) // whenever using not null you must add it to the Body request
    private Long id;
    @NotNull
    @NotBlank
    private String firstName;
    @NotNull
    @NotBlank
    private String lastName;
    @NotNull
    private LocalDate DOB;
    @NotNull
    @NotBlank
    private Integer age;

}
