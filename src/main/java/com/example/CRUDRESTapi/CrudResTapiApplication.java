package com.example.CRUDRESTapi;

import com.example.CRUDRESTapi.repository.model.domain.Role;
import com.example.CRUDRESTapi.repository.model.domain.User;
import com.example.CRUDRESTapi.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class CrudResTapiApplication {
	BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(CrudResTapiApplication.class, args);}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

		@Bean 											//without @Bean the CommandLiner will not run
		CommandLineRunner run(UserService userService){ //these ROLES, USERS will be created when the application starts
			return args -> {
				//this is to initialize the users when the application starts
				// we can create one super_admin and then send post requests to add Users to the JPA, which is sometimes a better approach
				userService.saveRole(new Role(null, "ADMIN"));
				userService.saveRole(new Role(null, "SUPER_ADMIN"));
				userService.saveRole(new Role(null,"MANAGER"));
				userService.saveRole(new Role(null,"EMPLOYEE"));

				userService.saveUser(new User(null,"Ali","CEO_Ali","1234", new ArrayList<>()));
				userService.saveUser(new User(null,"Badr","Badr_M","1234", new ArrayList<>()));
				userService.saveUser(new User(null,"Rahaf","Rahaf_M","1234",new ArrayList<>()));
				userService.saveUser(new User(null,"Abeer","Abeer_M","1234",new ArrayList<>()));
				userService.saveUser(new User(null,"Lolo","lolo_E","1234",new ArrayList<>()));

				userService.addRoleToUser("CEO_Ali","SUPER_ADMIN");
				userService.addRoleToUser("Badr_M","ADMIN");
				userService.addRoleToUser("Badr_M","MANAGER");
				userService.addRoleToUser("Rahaf_M","MANAGER");
				userService.addRoleToUser("Abeer_M","MANAGER");
				userService.addRoleToUser("lolo_E","EMPLOYEE");
				//userService.addRoleToUser("AliBinBadr","neverMind");
			};
	}
}
