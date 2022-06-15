package com.example.CRUDRESTapi.service;

import com.example.CRUDRESTapi.exception.ResourceNotFoundException;
import com.example.CRUDRESTapi.repository.RoleRepo;
import com.example.CRUDRESTapi.repository.UserRepo;
import com.example.CRUDRESTapi.repository.domain.Role;
import com.example.CRUDRESTapi.repository.domain.User;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
@Data


public class UserServiceImp implements UserService, UserDetailsService { //#Technical debt : do the dto layer for this later
                                                                        //#UserDetailsService is an interface that let us find and set authorities to the users we have in JPA

    private final UserRepo userRepo;

    private final RoleRepo roleRepo;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if(user == null) {
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("User found in the database: {}", username);
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.getRoles().forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
            });
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
    }
    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the database", user.getNameOfTheUser());
        user.setPassword(passwordEncoder.encode(user.getPassword())); //because we are using the password encoder, we want to encode the password before save it to the jpa
        user = userRepo.save(user);
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
