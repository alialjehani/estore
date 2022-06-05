package com.example.CRUDRESTapi.service;

import com.example.CRUDRESTapi.exception.ResourceNotFoundException;
import com.example.CRUDRESTapi.mapper.EmployeeMapper;
import com.example.CRUDRESTapi.repository.EmployeeRepository;
import com.example.CRUDRESTapi.repository.model.JpaEmployee;
import com.example.CRUDRESTapi.repository.model.JpaProduct;
import com.example.CRUDRESTapi.service.dto.EmployeeDto;
import com.example.CRUDRESTapi.service.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class EmployeeServiceImp implements EmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    public EmployeeDto createEmployee(EmployeeDto employeeDto) throws Exception {
      JpaEmployee jpaEmployee= employeeRepository.save
              (EmployeeMapper.INSTANCE.employeeDtoToEntity(employeeDto));
        return EmployeeMapper.INSTANCE.EntityToDto(jpaEmployee);
    }
    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        Optional<JpaEmployee> employee = this.employeeRepository.findById(employeeDto.getId());
        if (employee.isPresent()) {
            JpaEmployee jpaEmployee = employeeRepository.save(
                    EmployeeMapper.INSTANCE.employeeDtoToEntity(employeeDto));
                    return EmployeeMapper.INSTANCE.EntityToDto(jpaEmployee);
        } else {
            throw new ResourceNotFoundException("The Resource is not found");
        }
    }
    @Override
    public List<EmployeeDto> getAllEmployee() { //EntityToDto didn't work because we are returning one object a time
                                                //to get the whole list one time you will need to create another mapper of typeList
        List<JpaEmployee> jpaEmployeeList = new ArrayList<>();
        jpaEmployeeList = employeeRepository.findAll();
        return   EmployeeMapper.INSTANCE.EntityListToDtoList(jpaEmployeeList);
    }
    @Override
    public EmployeeDto getEmployeeById(long id) {
        Optional<JpaEmployee> jpaEmployee = this.employeeRepository.findById(id);
        if (jpaEmployee.isPresent()) {
          return   EmployeeMapper.INSTANCE.EntityToDto(jpaEmployee.get());
        } else {
            throw new ResourceNotFoundException("The Resource is not found");
        }
    }
    @Override
    public void deleteEmployee(long id) {
        Optional<JpaEmployee> jpaEmployee = this.employeeRepository.findById(id);
        if(jpaEmployee.isPresent()){
             this.employeeRepository.delete(jpaEmployee.get());}
        else{
            throw new ResourceNotFoundException("The Resource is not found");}
    }
}