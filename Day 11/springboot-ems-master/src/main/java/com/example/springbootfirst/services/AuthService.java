package com.example.springbootfirst.services;

import com.example.springbootfirst.jwt.JwtTokenProvider;
import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.models.Roles;
import com.example.springbootfirst.models.UserDetailsDto;
import com.example.springbootfirst.repository.RegisterDetailRepository;
import com.example.springbootfirst.repository.RegisterRepository;
import com.example.springbootfirst.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthService {

    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    RegisterDetailRepository registerDetailRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    public String addNewEmployee(UserDetailsDto register) {
        RegisterDetails registerDetails = new RegisterDetails();
        registerDetails.setEmpID(register.getEmpID());
        registerDetails.setName(register.getName());
        registerDetails.setEmail(register.getEmail());
        registerDetails.setPassword(passwordEncoder.encode(register.getPassword()));
        registerDetails.setUserName(register.getUserName());
        Set<Roles> roles =new HashSet<>();
        for(String roleName: register.getRoleName()){
            Roles role = rolesRepository.findByRoleName(roleName)
                    .orElseThrow(()->new RuntimeException("User not Found"));
            roles.add(role);
        }
        registerDetails.setRoles(roles);
        registerDetailRepository.save(registerDetails);

        return "Employee Added Successfully";
    }

//    public String authenticate(RegisterDetails login) {
//        RegisterDetails user = registerDetailRepository.findByEmail(login.getEmail());
//        if(user != null){
//            if(passwordEncoder.matches(login.getPassword(), user.getPassword())){
//                return "Login Successfully";
//            }
//            else{
//                return "Password Incorrect";
//            }
//        }
//        return "Login Not Successfully";
//    }

    public Map<String, Object> authenticate(RegisterDetails login){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getUserName(),login.getPassword()));

        String token = jwtTokenProvider.generateToken(authentication);

        String role = " ";                                                   
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            role = authority.getAuthority();
            break;
        }
        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("username", login.getUserName());
        result.put("role", role);
        result.put("password", login.getPassword());

        return result;
    }

    public Optional<RegisterDetails>  getUserByUserName(String username){
        return registerRepository.findByUserName(username);
    }


}
