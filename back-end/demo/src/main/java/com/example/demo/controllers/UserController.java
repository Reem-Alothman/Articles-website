package com.example.demo.controllers;

import com.example.demo.mapstruct.dtos.UserDto;
import com.example.demo.models.User;
import com.example.demo.security.DomainUserDetailsService;
import com.example.demo.security.JWTRequest;
import com.example.demo.security.JWTUtility;
import com.example.demo.services.UserService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTUtility jwtUtility;
    @Autowired
    DomainUserDetailsService domainUserDetailsService;

    @PostMapping(value = "/user")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDto newUser) {
        userService.createUser(newUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping(value = "/login")
    public ResponseEntity<Object> loginUser(@Valid @RequestBody JWTRequest jwtRequest) throws Exception {
        try {
            final UserDetails userDetails = domainUserDetailsService.loadUserByUsername(jwtRequest.getUsername());

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    jwtRequest.getUsername(),
                    jwtRequest.getPassword()
            );

            Authentication authentication = authenticationManager.authenticate(authenticationToken);
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            final String token = jwtUtility.generateToken(userDetails);
//            return ResponseEntity.status((HttpStatus.OK)).body(token);

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            "Bearer "+jwtUtility.generateToken(userDetails)
                    ).body("logged in successfully");

        } catch (BadCredentialsException e){
            throw new Exception("Invalid Credentials", e);
        }

    }

    @GetMapping(value ="/logout")
    public ResponseEntity<String> logUserOut() {
        userService.logoutUser();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}