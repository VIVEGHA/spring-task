package com.example.deploy.controllers;

import com.example.deploy.models.RegisterDetails;
import com.example.deploy.repository.RegisterDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private RegisterDetailsRepository repo;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public List<RegisterDetails> getAll() {
        return repo.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        repo.deleteById(id);
        return "Employee deleted";
    }
}
