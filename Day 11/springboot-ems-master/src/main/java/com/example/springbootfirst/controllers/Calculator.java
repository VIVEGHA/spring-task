package com.example.springbootfirst.controllers;

public class Calculator {


    public int addition(int n,int m){
        int a = n+m;
        return a;
    }
    public int sub(int n,int m){
        int a = n-m;
        return a;
    }
    public int mul(int n,int m){
        int a = n*m;
        return a;
    }
    public int div(int n,int m){
        if(m==0) return 0;
        int a = n/m;
        return a;
    }
}
