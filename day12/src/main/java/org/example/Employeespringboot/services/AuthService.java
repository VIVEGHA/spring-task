package org.example.Employeespringboot.services;


import org.example.Employeespringboot.jwt.JwtTokenProvider;
import org.example.Employeespringboot.models.AuthResponse;
import org.example.Employeespringboot.models.RegisterDetails;
import org.example.Employeespringboot.models.Roles;
import org.example.Employeespringboot.models.UserDetailsDto;
import org.example.Employeespringboot.repository.RegisterDetailsRepository;
import org.example.Employeespringboot.repository.RegisterRepository;
import org.example.Employeespringboot.repository.RolesRepository;
import  org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    RegisterDetailsRepository registerDetailsRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

//    public String addNewEmployee(UserDetailsDto register) {
//        RegisterDetails registerDetails = new RegisterDetails();
//        registerDetails.setEmpId(register.getEmpId());
//        registerDetails.setName(register.getName());
//        registerDetails.setEmail(register.getEmail());
//        registerDetails.setPassword(passwordEncoder.encode(register.getPassword()));
//        registerDetails.setUserName(register.getUserName());
//        Set<Roles> roles = new HashSet<>();
//        for(String roleName: register.getRoleName()){
//            Roles role = rolesRepository.findByRoleName(roleName)
//                    .orElseThrow(()->new RuntimeException("User not found" + roleName));
//            roles.add(role);
//        }
//        registerDetails.setRoles(roles);
//        System.out.println("Registration"+ registerDetails);
//        registerDetailsRepository.save(registerDetails);
//        return "Employee Added Successfully";
//    }

//    public String authenticate(RegisterDetails login) {
//        Authentication authentication =
//                authenticationManager.authenticate(
//                        new UsernamePasswordAuthenticationToken(
//                                login.getUserName(),login.getPassword()));
//        return jwtTokenProvider.generateToken(authentication);
//    }




    public String addNewEmployee(UserDetailsDto register) {
        RegisterDetails registerDetails = new RegisterDetails();
        registerDetails.setEmpId(register.getEmpId());
        registerDetails.setName(register.getName());
        registerDetails.setEmail(register.getEmail());
        registerDetails.setPassword(passwordEncoder.encode(register.getPassword()));
        registerDetails.setUserName(register.getUserName());

        Set<Roles> roles = new HashSet<>();

        if (register.getRoleName() != null && !register.getRoleName().isEmpty()) {
            for (String roleName : register.getRoleName()) {
                Roles role = rolesRepository.findByRoleName(roleName)
                        .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
                roles.add(role);
            }
        } else {
            // Fallback: set default role
            Roles defaultRole = rolesRepository.findByRoleName("ROLE_EMPLOYEE")
                    .orElseThrow(() -> new RuntimeException("Default role not found"));
            roles.add(defaultRole);
        }

        registerDetails.setRoles(roles);
        System.out.println("Registration: " + registerDetails);
        registerDetailsRepository.save(registerDetails);
        return "Employee Added Successfully";
    }


    public AuthResponse authenticate(RegisterDetails login) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getUserName(), login.getPassword()));

        String token = jwtTokenProvider.generateToken(authentication);
        RegisterDetails user = registerRepository.findByUserName(login.getUserName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Set<String> roleNames = user.getRoles()
                .stream()
                .map(role -> role.getRoleName())
                .collect(Collectors.toSet());

        return new AuthResponse(token, user.getUserName(), roleNames);
    }

    public Optional<RegisterDetails> getUserByUsername(String username){
        return registerRepository.findByUserName(username);
    }


}