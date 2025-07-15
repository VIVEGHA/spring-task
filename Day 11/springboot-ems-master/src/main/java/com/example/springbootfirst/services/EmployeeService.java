package com.example.springbootfirst.services;


import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.models.Roles;
import com.example.springbootfirst.models.UserDetailsDto;
import com.example.springbootfirst.repository.RegisterDetailRepository;
import com.example.springbootfirst.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RegisterDetailRepository registerDetailRepository;

    @Autowired
    RolesRepository rolesRepository;

    public List<RegisterDetails> getMethod() {

        return registerDetailRepository.findAll();
    }

    public RegisterDetails getEmployeeById(int empID) {
        return registerDetailRepository.findById(empID).orElse(new RegisterDetails());
    }

    public List<RegisterDetails> getEmployeeByRoles(String roleName){
        Roles role = rolesRepository.findByRoleName(roleName)
                .orElseThrow(()->new RuntimeException("Role Not Found" + roleName));
        return registerDetailRepository.findByRoles(role);
    }

    public String addNewEmployee(UserDetailsDto register) {
        RegisterDetails registerDetails = new RegisterDetails();
        registerDetails.setEmpID(register.getEmpID());
        registerDetails.setName(register.getName());
        registerDetails.setEmail(register.getEmail());
        registerDetails.setPassword(passwordEncoder.encode(register.getPassword()));
        registerDetails.setUserName(register.getUserName());
        Set<Roles> roles = new HashSet<>();
        for(String roleName: register.getRoleName()){
            Roles role = rolesRepository.findByRoleName(roleName)
                    .orElseThrow(()->new RuntimeException("User not found" + roleName));
            roles.add(role);
        }
        registerDetails.setRoles(roles);
        System.out.println("Registration"+ registerDetails);
        registerDetailRepository.save(registerDetails);
        return "Employee Added Successfully";
    }

    public String updateEmployee(int empID,UserDetailsDto registerDetails) {
        RegisterDetails user = registerDetailRepository.findById(empID)
                        .orElseThrow(()-> new RuntimeException("No such user found"));
        user.setName(registerDetails.getName());
        user.setEmail(registerDetails.getEmail());
        user.setPassword(passwordEncoder.encode(registerDetails.getPassword()));
        user.setUserName(registerDetails.getUserName());
        Set<Roles> roles = new HashSet<>();
        for(String roleName: registerDetails.getRoleName()){
            Roles role = rolesRepository.findByRoleName(roleName)
                    .orElseThrow(()->new RuntimeException("User not found" + roleName));
            roles.add(role);
        }
        user.setRoles(roles);

        registerDetailRepository.save(user);
        return "Employee Updated Successfully";
    }

    public String deleteEmployeeById(int empID){
        registerDetailRepository.deleteById(empID);
        return "Employee Deleted Successfully";
    }
}
