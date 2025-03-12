package com.codewithmosh.lab_fizzbuzz;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to FizzBuzz");
        System.out.print("Enter a number:" );
        int number = scanner.nextInt();

        String result = "";
        if(number % 3 == 0)
            result += "Fizz";
        if(number % 5 == 0)
            result += "Buzz";
        if(number % 3 != 0 && number % 5 != 0)
            result = Integer.toString(number);

        System.out.println(result);
    }
}
