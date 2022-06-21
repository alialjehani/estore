package com.example.CRUDRESTapi.repository.model.domain;
import lombok.*;
import javax.persistence.*;
import java.util.*;

@Entity
@Data           //Warning:(7, 1) Using @Data for JPA entities is not recommended. It can cause severe performance and memory consumption issues.
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users") //if we did not use table name or not name it as users, the name (user) is reserved
                        // so the program will not run because user is reserved in H2
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nameOfTheUser")
    private String nameOfTheUser;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

}