package com.example.CRUDRESTapi.repository;

import com.example.CRUDRESTapi.repository.model.JpaBranch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<JpaBranch,Long> {
}
