package org.example.Employeespringboot.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "todo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private String status; // PENDING, COMPLETED, etc.

    @ManyToOne
    @JoinColumn(name = "emp_id")
    private RegisterDetails employee;
}
