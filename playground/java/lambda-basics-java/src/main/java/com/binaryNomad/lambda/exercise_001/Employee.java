package com.binaryNomad.lambda.exercise_001;

public class Employee implements Measurable {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public double getMeasure() {
        return this.salary;
    }

    public String getName() {
        return this.name;
    }
}
