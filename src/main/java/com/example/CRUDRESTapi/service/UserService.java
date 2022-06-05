package com.example.CRUDRESTapi.service;

import com.example.CRUDRESTapi.repository.domain.Role;
import com.example.CRUDRESTapi.repository.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);

    User getUserById(long id);
    List<User> getUsers();
}
