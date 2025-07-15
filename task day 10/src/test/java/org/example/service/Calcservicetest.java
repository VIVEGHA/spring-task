package org.example.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Calcservicetest {
    private Calcservice calculator;
    @BeforeEach
    public void setUp(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");
        calculator = (Calcservice) context.getBean("calculatorservice");
    }
    @Test
    void testAddition() {
        assertEquals(9, calculator.add(4, 5));
    }

    @Test
    void testSubtraction() {
        assertEquals(2, calculator.subtract(7, 5));
    }
    @Test
    void testMultiplication() {
        assertEquals(35, calculator.multiply(7, 5));
    }
    @Test
    void testDivision() {
        assertEquals(3, calculator.divide(6, 2));
    }
    @Test
    void testDivideByZero() {
        Exception ex = assertThrows(ArithmeticException.class, () -> {
            calculator.divide(5, 0);
        });
        assertEquals("Divide by zero", ex.getMessage());
    }
}
