package com.example.springbootfirst.controllers;

import com.example.springbootfirst.models.Todo;
import com.example.springbootfirst.repository.RegisterDetailRepository;
import com.example.springbootfirst.services.TodoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class ToDoEmployeeController {

    @Autowired
    TodoServices todoServices;


    @GetMapping("/get/{empID}")
    public List<Todo> getTodoByEmployee(@PathVariable int empID){
        return todoServices.getTodoByEmployee(empID);
    }

    @PostMapping("/assign/{empID}")
    public String assignTask(@PathVariable int empID, @RequestBody Todo todo) {
        return todoServices.assignTaskToEmployee(empID, todo);
    }


}
