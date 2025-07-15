package com.example.deploy.controllers;

import com.example.deploy.models.*;
import com.example.deploy.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService svc;

    @PostMapping("/register")
    public String register(@RequestBody UserDetailsDto dto) {
        return svc.register(dto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest req) {
        return svc.login(req);
    }
    @GetMapping("/test")
    public String test() {
        return "Auth service is up!";
    }
}
