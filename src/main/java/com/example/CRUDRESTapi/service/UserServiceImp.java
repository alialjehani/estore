package com.example.CRUDRESTapi.service;

import com.example.CRUDRESTapi.exception.ResourceNotFoundException;
import com.example.CRUDRESTapi.repository.RoleRepo;
import com.example.CRUDRESTapi.repository.UserRepo;
import com.example.CRUDRESTapi.repository.domain.Role;
import com.example.CRUDRESTapi.repository.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j

public class UserServiceImp implements UserService{ //need to do the dto layer for this

    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private final RoleRepo roleRepo;
    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the database", user.getNameOfTheUser());
         user = userRepo.save(user);
         user.setNameOfTheUser(user.getNameOfTheUser());
         user.setPassword(user.getPassword());
         user.setUsername(user.getUsername());
        return user;
    }
    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getName());
        role = roleRepo.save(role);
        role.setName(role.getName());
        return role;
    }
    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role{} to user {} ", roleName,username);
        User user = userRepo.findByUsername(username);
        Role role = roleRepo.findByName(roleName);
        user.getRoles().add(role);
    }
    @Override
    public User getUser(String username) {
        log.info("fetching user{} ", username);
        return userRepo.findByUsername(username);
    }
    @Override
    public User getUserById(long id) { //check from this one if it works
        Optional<User> userId = this.userRepo.findById(id);
        if(userId.isPresent()){
            return userId.get();
        }
        throw new ResourceNotFoundException("The Resource is not found");
    }
    @Override
    public List<User> getUsers() {
        log.info("fetching All Users");
        return userRepo.findAll();
    }
}
