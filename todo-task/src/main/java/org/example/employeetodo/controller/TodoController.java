package org.example.employeetodo.controller;

import org.example.employeetodo.model.Employee;
import org.example.employeetodo.model.Todo;
import org.example.employeetodo.repository.EmployeeRepository;
import org.example.employeetodo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("/add/{empId}")
    public ResponseEntity<Todo> addTodo(@PathVariable int empId, @RequestBody Todo todo) {
        Optional<Employee> employeeOpt = employeeRepository.findById(empId);
        if (!employeeOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        todo.setEmployee(employeeOpt.get());
        return ResponseEntity.ok(todoRepository.save(todo));
    }

    @GetMapping("/employee/{empId}")
    public List<Todo> getTodosByEmployee(@PathVariable int empId) {
        return todoRepository.findByEmployeeId(empId);
    }
}
