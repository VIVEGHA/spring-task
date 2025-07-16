package org.example.Employeespringboot.controllers;

import org.example.Employeespringboot.models.Todo;
import org.example.Employeespringboot.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/assign/{empId}")
    public ResponseEntity<Todo> assignTodo(@PathVariable int empId, @RequestBody Todo todo) {
        Todo createdTodo = todoService.assignTaskToEmployee(empId, todo);
        return ResponseEntity.ok(createdTodo);
    }

    @GetMapping("/employee/{empId}")
    public ResponseEntity<List<Todo>> getTodosByEmployee(@PathVariable int empId) {
        return ResponseEntity.ok(todoService.getTodosByEmployee(empId));
    }
}




