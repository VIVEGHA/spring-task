package com.example.springbootfirst.services;



import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.repository.RegisterDetailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EmployeeServiceTest {

    @Mock
    RegisterDetailRepository registerDetailRepository;

    @InjectMocks
    EmployeeService employeeService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetMethod(){
        RegisterDetails emp1= new RegisterDetails();
        RegisterDetails emp2= new RegisterDetails();
        when(registerDetailRepository.findAll()).thenReturn(Arrays.asList(emp1,emp2));
        List<RegisterDetails> result = employeeService.getMethod();
        assertEquals(2,result.size());
    }

}