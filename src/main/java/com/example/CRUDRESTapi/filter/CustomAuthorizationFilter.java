package com.example.CRUDRESTapi.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Slf4j

public class CustomAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getServletPath().equals("/api/login")) { //if this is the path requested, im not going to do anything and just let the user pass to the login page
            filterChain.doFilter(request, response);
        }else{
            String authorizationHeader = request.getHeader(AUTHORIZATION); // authorization header is where the token stored
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) { //the tokens usually starts with Bearer
                try {
                        String token = authorizationHeader.substring("Bearer ".length()); //to get the token - the Bearer
                        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes()); //has to be the same algorithm of the Authentication filter with the same key "secret"
                        JWTVerifier verifier = JWT.require(algorithm).build(); //this is how we create the verifier
                        DecodedJWT decodedJWT = verifier.verify(token); //pass the token to the verifier
                        String username = decodedJWT.getSubject(); //this is going to give the username that comes in the token
                        String[] roles = decodedJWT.getClaim("roles").asArray(String.class); //.getClaim("what's the key word needs to be copied in the token "roles"") how to copy it .asArray(String.class)
                        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
                        stream(roles).forEach(role -> {
                            authorities.add(new SimpleGrantedAuthority(role));
                        });
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                        filterChain.doFilter(request, response);

                }catch (Exception exception) {
                    log.error("Error logging in: {}", exception.getMessage());
                    response.setHeader("error", exception.getMessage());
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    //  response.sendError(HttpServletResponse.SC_FORBIDDEN);
                    Map<String, String> error = new HashMap<>();
                    error.put("error_message", exception.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error); // this going to return the tokens in a JSON format in the body of the response

                }
                }else {
                filterChain.doFilter(request,response);
            }
            }
        }
    }



