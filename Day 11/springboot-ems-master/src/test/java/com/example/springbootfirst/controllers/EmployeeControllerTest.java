package com.example.springbootfirst.controllers;

import com.example.springbootfirst.models.RegisterDetails;
import com.example.springbootfirst.models.UserDetailsDto;
import com.example.springbootfirst.services.EmployeeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class EmployeeControllerTest {

   @Mock
   EmployeeService employeeService;

   @InjectMocks
   EmployeeController employeeController;

   @BeforeEach
   void setUp(){
       MockitoAnnotations.openMocks(this);
   }

   @Test
   void testRoute(){
       String result = employeeController.route();
       assertEquals("Welcome to SpringBoot Security",result);
   }


   @Test
   void testgetMethod(){
       RegisterDetails emp1 = new RegisterDetails();
       RegisterDetails emp2 = new RegisterDetails();
       when(employeeService.getMethod()).thenReturn(Arrays.asList(emp1,emp2));
       List<RegisterDetails> result = employeeController.getMethod();
       assertEquals(2,result.size());
   }

   @Test
   void testgetEmployeeById(){
       int empid = 1;
       RegisterDetails emp1 = new RegisterDetails();
       emp1.setEmpID(empid);
       when(employeeService.getEmployeeById(empid)).thenReturn(emp1);
       RegisterDetails result = employeeController.getEmployeeById(empid);
       assertEquals(empid,result.getEmpID());
   }

   @Test
   void testaddnewEmployee(){
       UserDetailsDto user = new UserDetailsDto();
       user.setName("vivi");
       user.setEmail("vivi@gmail.com");
       user.setPassword("vivi@1234");
       String expectedMessage = "Employee added successfully";
       when(employeeService.addNewEmployee(user)).thenReturn(expectedMessage);
       String result = employeeController.addnewEmployee(user);
       assertEquals(expectedMessage, result);
   }

   @Test
   void testupdateEmployee(){
       int empId = 1;
       UserDetailsDto user = new UserDetailsDto();
       user.setName("vivi");
       user.setEmail("vivi@gmail.com");
       String expectedMessage = "Employee updated successfully";
       when(employeeService.updateEmployee(empId, user)).thenReturn(expectedMessage);
       String result = employeeController.updateEmployee(empId, user);
       assertEquals(expectedMessage, result);
   }

}