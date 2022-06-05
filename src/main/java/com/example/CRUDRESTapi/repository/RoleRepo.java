package com.example.CRUDRESTapi.repository;

import com.example.CRUDRESTapi.repository.domain.Role;
import com.example.CRUDRESTapi.repository.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
