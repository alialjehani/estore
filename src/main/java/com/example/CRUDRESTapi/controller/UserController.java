package com.example.CRUDRESTapi.controller;

import com.example.CRUDRESTapi.repository.domain.Role;
import com.example.CRUDRESTapi.repository.domain.User;
import com.example.CRUDRESTapi.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("api/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }
    @GetMapping("api/users{id}")
    public ResponseEntity<User> getUserById(@RequestBody @PathVariable long id){
        return ResponseEntity.ok().body(this.userService.getUserById(id));
    }

    @PostMapping("api/users/create")
    public ResponseEntity<User> createUser(@RequestBody  User user){ //always make sure to include the @RequestBody
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toString());
        return ResponseEntity.created(uri).body(this.userService.saveUser(user));
    }

    @PostMapping("api/user/role/create")
    public ResponseEntity<Role> createRole(@RequestBody  Role role){ //always make sure to include the @RequestBody
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toString());
        return ResponseEntity.created(uri).body(this.userService.saveRole(role));
    }

    @PostMapping("api/users/addRoleToUser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
        this.userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@PathVariable long id){
        return null; // Technical Debt, Continue deleteUser()
    }
}
