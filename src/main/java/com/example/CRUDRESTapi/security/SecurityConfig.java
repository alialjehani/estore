package com.example.CRUDRESTapi.security;

import com.example.CRUDRESTapi.filter.CustomAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@EnableWebSecurity
@Configuration
@RequiredArgsConstructor

public class SecurityConfig extends WebSecurityConfigurerAdapter {
   private final UserDetailsService userDetailsService; //we can include the annotation @Autowired but not necessary
   private final BCryptPasswordEncoder bCryptPasswordEncoder; //we can include the annotation @Autowired but not necessary

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception { //further config can be implemented to authorize the login page check 1:25:00
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS); //|| SESSIONS //understand the difference 1:03:00
//        http.authorizeRequests().anyRequest().permitAll(); //allow anyone to access this application, this is not practical because we need to make some end-points accessed by a certain user_Roles ex: Super_Admin
        http.authorizeRequests().antMatchers(GET,"api/users/**").hasAuthority("SUPER_ADMIN");
        http.authorizeRequests().antMatchers(GET,"api/users/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(GET,"api/employees").hasAuthority("MANAGER");
        http.authorizeRequests().antMatchers(GET,"api/employees/**").hasAuthority("MANAGER");
        http.authorizeRequests().antMatchers(GET,"api/users/**").hasAuthority("MANAGER");
        http.authorizeRequests().antMatchers(POST,"api/users/**").hasAuthority("SUPER_ADMIN");
        http.authorizeRequests().antMatchers(POST,"api/users/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(POST,"api/Branches/**").hasAuthority("SUPER_ADMIN");
        http.authorizeRequests().antMatchers(POST,"api/Branches/**").hasAuthority("SUPER_ADMIN");
        http.authorizeRequests().antMatchers(POST,"api/employees/**").hasAuthority("MANAGER");
        http.authorizeRequests().antMatchers(PUT,"api/users/**").hasAuthority("SUPER_ADMIN");
        http.authorizeRequests().antMatchers(PUT,"api/users/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(PUT,"api/employees/**").hasAuthority("SUPER_ADMIN");
        http.authorizeRequests().antMatchers(PUT,"api/employees/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(DELETE,"api/users/**").hasAuthority("SUPER_ADMIN");
        http.authorizeRequests().antMatchers(DELETE,"api/users/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(DELETE,"api/employees/**").hasAuthority("SUPER_ADMIN");
        http.authorizeRequests().antMatchers(DELETE,"api/employees/**").hasAuthority("ADMIN");
        http.authorizeRequests().anyRequest().authenticated().antMatchers(GET,"api/products/**");
        http.authorizeRequests().antMatchers(GET,"api/products/**");
        http.authorizeRequests().antMatchers(POST,"api/products/**").hasAuthority("SUPER_ADMIN");
        http.authorizeRequests().antMatchers(POST,"api/products/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(POST,"api/products/**").hasAuthority("MANAGER");
        http.authorizeRequests().antMatchers(POST,"api/products/**").hasAuthority("EMPLOYEE");
        http.authorizeRequests().antMatchers(DELETE,"api/products/**").hasAuthority("SUPER_ADMIN");
        http.authorizeRequests().antMatchers(DELETE,"api/products/**").hasAuthority("ADMIN");
        http.authorizeRequests().antMatchers(DELETE,"api/products/**").hasAuthority("MANAGER");
        http.authorizeRequests().antMatchers(DELETE,"api/products/**").hasAuthority("EMPLOYEE");





        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}