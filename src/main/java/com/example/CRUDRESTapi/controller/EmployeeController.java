package com.example.CRUDRESTapi.controller;

import com.example.CRUDRESTapi.service.EmployeeService;
import com.example.CRUDRESTapi.service.dto.EmployeeDto;
import com.example.CRUDRESTapi.service.dto.validate.CreateEmployeeValidation;
import com.example.CRUDRESTapi.service.dto.validate.UpdateEmployeeValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class EmployeeController {

    @Autowired //Inject the Employee service in the controller to use the employeeService Object
    private EmployeeService employeeService;

    @GetMapping("api/employees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployee(){
        return ResponseEntity.ok().body(employeeService.getAllEmployee());
    }
    @GetMapping("api/employees/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable long id){
        return ResponseEntity.ok().body(employeeService.getEmployeeById(id));
    }
    @PostMapping("api/employees")
    public ResponseEntity<EmployeeDto>createEmployee(@RequestBody @Validated(value = CreateEmployeeValidation.class) EmployeeDto employeeDto) throws Exception {
        return ResponseEntity.ok(this.employeeService.createEmployee(employeeDto));
    }
    @PutMapping("api/employees")
    public ResponseEntity<EmployeeDto>updateEmployee(@RequestBody @Validated(value = UpdateEmployeeValidation.class) EmployeeDto employeeDto) throws Exception{
        return ResponseEntity.ok().body(this.employeeService.updateEmployee(employeeDto));
   }

    @DeleteMapping("api/employees/{id}")
    public HttpStatus deleteEmployee(@PathVariable long id){
        this.employeeService.deleteEmployee(id);
        return HttpStatus.OK;
  }
}