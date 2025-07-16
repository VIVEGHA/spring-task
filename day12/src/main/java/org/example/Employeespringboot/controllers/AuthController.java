package org.example.Employeespringboot.controllers;


import org.example.Employeespringboot.models.AuthResponse;
import org.example.Employeespringboot.models.RegisterDetails;
import org.example.Employeespringboot.models.UserDetailsDto;
import org.example.Employeespringboot.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

//    @PostMapping("/register")
//    public String addNewUser(@RequestBody UserDetailsDto register){
//        return authService.addNewEmployee(register);
//    }
//
//    @PostMapping("/login")
//    public String Login(@RequestBody RegisterDetails login){
//        return authService.authenticate(login);
//    }

    @PostMapping("/register")
    public String addNewUser(@RequestBody UserDetailsDto register){
        return authService.addNewEmployee(register);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> Login(@RequestBody RegisterDetails login){
        AuthResponse response = authService.authenticate(login);  // or employeeService if it’s there
        return ResponseEntity.ok(response);
    }
}