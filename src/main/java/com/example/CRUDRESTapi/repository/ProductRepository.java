package com.example.CRUDRESTapi.repository;

import com.example.CRUDRESTapi.repository.model.JpaProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<JpaProduct,Long> {
}
