package com.example.deploy.services;

import com.example.deploy.models.*;
import com.example.deploy.repository.*;
import com.example.deploy.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthService {

    @Autowired
    private RegisterDetailsRepository userRepo;

    @Autowired
    private RolesRepository rolesRepo;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authManager;

    public String register(UserDetailsDto dto) {
        if (userRepo.findByUserName(dto.getUserName()).isPresent()) {
            return "Username already exists";
        }

        RegisterDetails user = new RegisterDetails();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setUserName(dto.getUserName());
        user.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));

        Set<Roles> roles = new HashSet<>();
        dto.getRoleNames().forEach(rn -> {
            rolesRepo.findByRoleName(rn)
                    .ifPresent(roles::add);
        });
        user.setRoles(roles);

        userRepo.save(user);
        return "User registered successfully";
    }

    public String login(LoginRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUserName(), req.getPassword()));

        return jwtTokenProvider.generateToken(auth);
    }
}
