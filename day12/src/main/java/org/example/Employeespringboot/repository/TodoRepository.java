package org.example.Employeespringboot.repository;


import org.example.Employeespringboot.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findByEmployeeEmpId(int empId);
}
