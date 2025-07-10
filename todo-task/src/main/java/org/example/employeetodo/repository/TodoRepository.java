package org.example.employeetodo.repository;

import org.example.employeetodo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findByEmployeeId(int employeeId);
}
