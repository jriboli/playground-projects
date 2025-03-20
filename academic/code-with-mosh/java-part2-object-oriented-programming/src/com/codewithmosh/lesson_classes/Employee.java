package com.codewithmosh.lesson_classes;

public class Employee {
    private int baseSalary;
    private int hourlyRate;

    public static int numberOfEmployees;

    public Employee(int baseSalary, int hourlyRate){
        this.setBaseSalary(baseSalary);
        this.setHourlyRate(hourlyRate);
        numberOfEmployees++;
    }
    // Overloaded Constructor
    public Employee(int baseSalary){
        this(baseSalary, 0);
    }

    public static void printNumberOfEmployees(){
        System.out.println(numberOfEmployees);
    }
    public int calculateWage(int extraHours){
        return baseSalary + (hourlyRate * extraHours);
    }
    // Overloaded Method
    public int calculateWage(){
        return calculateWage(0);
    }

    private void setBaseSalary(int baseSalary){
        if(baseSalary <= 0)
            throw new IllegalArgumentException("Base Salary needs to be greater than zero.");
        this.baseSalary = baseSalary;
    }
    private int getBaseSalary(){
        return baseSalary;
    }

    private void setHourlyRate(int hourlyRate){
        if(hourlyRate < 0)
            throw new IllegalArgumentException("Hourly Rate needs to be greater than zero.");
        this.hourlyRate = hourlyRate;
    }
    private int getHourlyRate(){
        return hourlyRate;
    }
}