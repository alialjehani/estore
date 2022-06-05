package com.example.CRUDRESTapi.security;

import com.example.CRUDRESTapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;


@EnableWebSecurity
@Configuration
@RequiredArgsConstructor

public class SecurtiyConfig extends WebSecurityConfigurerAdapter {

    /*    @Autowired
    private final UserDetailsService userDetailsService;
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }*/

  /*  @Override
    protected UserDetailsService userDetailsService() {
        List<UserDetails> userDetailsList=new ArrayList<>();
        userDetailsList.add(User.withUsername("ranjith").password("1234").roles("admin").build());
       // return super.userDetailsService();
        return new InMemoryUserDetailsManager(userDetailsList);
    }
*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
//        http.formLogin()
//                .loginPage("/login")
//                .permitAll();
//        http.formLogin().defaultSuccessUrl("/api/users");
//    }

        http.httpBasic().and()// add this for postman to work properly
                .cors().and()
                .csrf().disable().authorizeRequests()
                .antMatchers("/users").hasRole("admin")
                .anyRequest().authenticated()
                .and().formLogin();
    }

}




        //Another Security Config
        //                http
//                        .cors().and()
//                        .csrf().disable().authorizeRequests()
//                        .antMatchers("/users").hasRole("admin")
//                        .anyRequest().authenticated()
//                        .and()
//                        .formLogin();
//            }
//        }





        //One of the security config
//        httpSecurity
//        .authorizeRequests()
//        .anyRequest()
//                .permitAll()
//                .and().httpBasic();
//        httpSecurity
//                .csrf().disable();