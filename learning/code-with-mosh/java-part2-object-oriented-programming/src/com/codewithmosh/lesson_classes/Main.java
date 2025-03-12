package com.codewithmosh.lesson_classes;

public class Main {
    public static void main(String[] args){
        var textBox1 = new TextBox(); // using var Java with detect the data type based on assignment

        // MEMORY ALLOCATION
        // HEAP
        // where Objects are stored

        // STACK
        // where Primitives and short lived variables
        // Reference types store the address of their object on the HEAP

        var textBox001 = new TextBox(); // Object in HEAP at location 100
        var textBox002 = new TextBox(); // Object in HEAP at location 101
        var textBox003 = textBox001; // Since assign reference type we assign the location on HEAP not the values - so 100

        // At this point changes to 001 and 003 affect each other
        // We are assigning the reference / HEAP location

        // MEMORY DEALLOCATION
        // When we exist a method JAVA will remove the items stored on the Stack
        // ie. textBox001 - 003 from the Stack

        // Items will then be left in the HEAP without reference
        // after a period of time a process with clean these up - This is called
        // Garbage Collection

        var employee = new Employee(50_000, 20);
        // Encapsulate logic into the Employee Class
        // Not to be visible outside the Class
            //employee.setBaseSalary(50_000);
            //employee.setHourlyRate(20);
        int wage = employee.calculateWage(10);
        System.out.println(wage);
    }

}
