package com.example.springbootfirst.services;

import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.models.Todo;
import com.example.springbootfirst.repository.RegisterDetailRepository;
import com.example.springbootfirst.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServices {

    @Autowired
    TodoRepository todoRepository;

    @Autowired
    private RegisterDetailRepository registerDetailRepository;

    public String assignTaskToEmployee(int empID, Todo todo) {
        RegisterDetails employee = registerDetailRepository.findById(empID)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        todo.setEmployee(employee);
        todoRepository.save(todo);

        return "Task assigned to employee with ID: " + empID;
    }

    public List<Todo> getTodoByEmployee(int empID) {
        return todoRepository.findByEmployeeEmpID(empID);
    }


}
