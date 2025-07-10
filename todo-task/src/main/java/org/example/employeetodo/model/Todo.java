package org.example.employeetodo.model;

import jakarta.persistence.*;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String task;
    private boolean completed;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;
    public Todo() {}

    public Todo(String task, boolean completed, Employee employee) {
        this.task = task;
        this.completed = completed;
        this.employee = employee;
    }
    public int getId() { return id; }

    public String getTask() { return task; }

    public void setTask(String task) { this.task = task; }

    public boolean isCompleted() { return completed; }

    public void setCompleted(boolean completed) { this.completed = completed; }

    public Employee getEmployee() { return employee; }

    public void setEmployee(Employee employee) { this.employee = employee; }
}
