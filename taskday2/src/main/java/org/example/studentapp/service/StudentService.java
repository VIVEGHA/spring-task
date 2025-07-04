package org.example.studentapp.service;

import org.example.studentapp.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private List<Student> studentList = new ArrayList<>();
    private long nextId = 1;

    public List<Student> getAllStudents() {
        return studentList;
    }

    public Student getStudentById(Long id) {
        return studentList.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Student saveStudent(Student student) {
        student.setId(nextId++);
        studentList.add(student);
        return student;
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        Optional<Student> optional = studentList.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst();

        if (optional.isPresent()) {
            Student student = optional.get();
            student.setName(updatedStudent.getName());
            student.setAge(updatedStudent.getAge());
            student.setEmail(updatedStudent.getEmail());
            return student;
        }
        return null;
    }

    public void deleteStudent(Long id) {
        studentList.removeIf(s -> s.getId().equals(id));
    }
}