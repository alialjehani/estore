package com.example.CRUDRESTapi.repository.model;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Product")
public class JpaProduct {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private BigDecimal price;
    @Column
    private String description;
    @Column
    private Date createdAt;
    @Column
    private Date updatedAt;


}

