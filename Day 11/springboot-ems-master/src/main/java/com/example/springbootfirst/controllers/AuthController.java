package com.example.springbootfirst.controllers;

import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.models.UserDetailsDto;
import com.example.springbootfirst.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public String addNewuser(@RequestBody UserDetailsDto register){

        return authService.addNewEmployee(register);
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody RegisterDetails login){

        return authService.authenticate(login);
    }

}
