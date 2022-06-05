package com.example.CRUDRESTapi.repository;

import com.example.CRUDRESTapi.repository.model.JpaEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<JpaEmployee,Long> {
}
