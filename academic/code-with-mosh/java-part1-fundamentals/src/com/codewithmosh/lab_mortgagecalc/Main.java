package com.codewithmosh.lab_mortgagecalc;

import java.text.NumberFormat;
import java.util.Scanner;

public class Main {
    // avoid magic numbers in code - use constants
    public static byte MONTHS_IN_YEAR = 12;
    public static byte PERCENT = 100;
    public static void main(String[] args){
        System.out.println("Welcome to Mortage Calculator - fill in the following:");

        int principal = (int) validateInput("Principal ($1K thru $1M: ", 1_000, 1_000_000);
        double annualInterestRate = validateInput("Annual Interest Rate: ", 0.1, 30);
        int numberOfYears = (int) validateInput("Period (Years): ", 1, 30);

        printMortgage(principal, annualInterestRate, numberOfYears);
        printPaymentSchedule(numberOfYears, principal, annualInterestRate);
    }

    private static void printMortgage(int principal, double annualInterestRate, int numberOfYears) {
        double mortgage = calculateMortgage(principal, annualInterestRate, numberOfYears);
        System.out.println();
        System.out.println("MORTAGE");
        System.out.println("-------");
        System.out.println("Monthly Payments: " + NumberFormat.getCurrencyInstance().format(mortgage));
    }

    private static void printPaymentSchedule(int numberOfYears, int principal, double annualInterestRate) {
        System.out.println();
        System.out.println("PAYMENT SCHEDULE");
        System.out.println("----------------");
        for(int numberOfPayments = 1; numberOfPayments < numberOfYears * MONTHS_IN_YEAR; numberOfPayments++)
        {
            double balance = calculateBalance(principal, annualInterestRate, numberOfYears, numberOfPayments);
            System.out.println(NumberFormat.getCurrencyInstance().format(balance));
            numberOfPayments++;
        }
    }

    public static double validateInput(String prompt, double min, double max){
        Scanner scanner = new Scanner(System.in);
        double result = 0;
        while(true) {
            System.out.print(prompt);
            result = scanner.nextDouble();
            if(result >= min && result <= max)
                break;
            System.out.println(String.format("Enter value between %d and %d", min, max));
        }
        return result;
    }

    public static double calculateMortgage(int principal, double annualInterestRate, int numberOfYears){
        int numberOfPayments = numberOfYears * MONTHS_IN_YEAR;
        double monthlyInterestRate = annualInterestRate / PERCENT / MONTHS_IN_YEAR;
        double mortgage = principal * ((monthlyInterestRate * Math.pow((1 + monthlyInterestRate), numberOfPayments)) / (Math.pow((1 + monthlyInterestRate), numberOfPayments) - 1));

        return mortgage;
    }

    public static double calculateBalance(
            int principal, double annualInterestRate,
            int numberOfYears, int paymentsMade){
        int numberOfPayments = numberOfYears * MONTHS_IN_YEAR;
        double monthlyInterestRate = annualInterestRate / PERCENT / MONTHS_IN_YEAR;

        double balance = (principal * (Math.pow((1+monthlyInterestRate), numberOfPayments) - Math.pow((1+monthlyInterestRate), paymentsMade)))/(Math.pow((1+monthlyInterestRate), numberOfPayments) - 1);
        return balance;
    }
}