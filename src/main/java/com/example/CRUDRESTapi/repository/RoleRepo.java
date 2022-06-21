package com.example.CRUDRESTapi.repository;

import com.example.CRUDRESTapi.repository.model.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
