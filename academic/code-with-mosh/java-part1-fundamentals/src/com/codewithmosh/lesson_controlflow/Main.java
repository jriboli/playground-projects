package com.codewithmosh.lesson_controlflow;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        // COMPARISON
        int x = 1;
        int y = 1;
        System.out.println(x == y);

        // LOGICAL OPERATORS
        int temperature = 22;
        boolean isWarm = temperature > 20 && temperature < 30;

        boolean hasHighIncome = true;
        boolean hasGoodCredit = true;
        boolean hasCriminalRecord = false;
        boolean isEligible = (hasHighIncome || hasGoodCredit) && !hasCriminalRecord; // NOT OPERATOR

        // IF STATEMENTS
        int temperature2 = 32;
        if(temperature2 > 30){
            System.out.println("It's a hot day");
            System.out.println("Drink plenty of water");
        }
        else if(temperature2 > 20) // & temperature2 <=30) UNNCESSARY DUE TO IF
            System.out.println("It's a beautiful day");
        else
            System.out.println("It's a cold day");

        // SIMPLIFYING IF STATEMENTS
        int income = 120_000;
        boolean hasHighIncome2 = (income > 100_000); //BOOLEAN EXPRESSION

        // TENARY OPERATOR
        int income2 = 120_000;
        String className = (income > 100_000) ? "First" : "Economy"; // Condition ? if true : if false

        // SWITCH STATEMENTS
        String role = "admin";
        switch(role){
            case "admin":
                System.out.println("You're an admin");
                break;
            case "moderator":
                System.out.println("You're a moderator");
                break;
            default:
                System.out.println("You're a guest");
        }
        // CAN USE WITH NUMBERS - BYTE, SHORT, INT

        // FOR LOOP
        // WHILE LOOP
        // Interesting note - can not use comparison operator for reference types (ie Strings)
        // it will compare the location of the reference and not the value
        Scanner scanner2 = new Scanner(System.in);
        String userInput = "";
        while(true) {
            System.out.print("Enter Input:");
            userInput = scanner2.next().toLowerCase();
            if(userInput.equals("pass"))
                continue; // skips rest of code block start loop over again
            if(userInput.equals("quit"))
                break;
            System.out.println(userInput);
        }

        // FOR EACH LOOP - to loop over iteratibles - ie Arrays
        String[] fruits = {"Apple", "Orange", "Banana"};
        for (String fruit : fruits){
            System.out.println(fruit);
        }
    }
}