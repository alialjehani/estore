package com.example.CRUDRESTapi.controller;

import com.example.CRUDRESTapi.repository.domain.User;
import com.example.CRUDRESTapi.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("api/users")
    public ResponseEntity<User> createUser(@RequestBody  User user){ //always make sure to include the @RequestBody
        return ResponseEntity.ok().body(this.userService.saveUser(user));
    }

    @PostMapping("api/users/addRoleToUser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
        this.userService.addRoleToUser(form.getUsername(), form.getRoleName());
        return ResponseEntity.ok().build();
    }
}
@Data
class RoleToUserForm{ //this class is acting like an entity
    private String username;
    private String roleName;
}
