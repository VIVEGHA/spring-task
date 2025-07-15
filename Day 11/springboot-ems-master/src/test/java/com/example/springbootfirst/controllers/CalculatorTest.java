package com.example.springbootfirst.controllers;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    Calculator ca = new Calculator();

    @Test
    public void testAddition(){
        assertEquals(5,ca.addition(2,3));
    }

    @Test
    public void testSub(){
        assertEquals(1,ca.sub(3,2));
    }

    @Test
    public void testMul(){
        assertEquals(6,ca.mul(2,3));
    }

    @Test
    public void testDiv(){
        assertEquals(2,ca.div(6,3));
    }
}
