package com.example.CRUDRESTapi.service;

import com.example.CRUDRESTapi.service.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto) throws Exception;
    EmployeeDto updateEmployee(EmployeeDto employeeDto);
    List<EmployeeDto> getAllEmployee();
    EmployeeDto getEmployeeById(long id);
    void deleteEmployee(long id);
}
