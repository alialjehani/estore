package com.example.CRUDRESTapi.mapper;

import com.example.CRUDRESTapi.repository.model.JpaEmployee;
import com.example.CRUDRESTapi.service.dto.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EmployeeMapper {
        EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
        JpaEmployee employeeDtoToEntity(EmployeeDto employeeDto);
        EmployeeDto EntityToDto(JpaEmployee jpaEmployee);
        List<EmployeeDto> EntityListToDtoList(List<JpaEmployee> jpaEmployee); //needed to retrive a list from the Jpa to DTO
    }


