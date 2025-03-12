package com.codewithmosh.lesson_types;

import java.awt.*;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        int myAge = 30;
        int herAge = myAge;
        System.out.println(myAge);

        // PRIMITIVE TYPES ---------------------------------------
        // byte - 1 bytes - range -128, 127
        // short - 2 bytes - range -32K, 32K
        // int - 4 bytes - range -2B, 2B
        // long - 8 bytes
        // float - 4 bytes
        // double - 8 bytes
        // char - 2 bytes
        // boolean - 1 bytes

        byte age = 30;
        int viewsCount = 123_456_789;
        long viewCountHuge = 3_123_456_789L; // Compiler sees value as integer - add L
        float price = 10.99F; //by default decimal point seen as double - add F
        char letter = 'A'; // single char with single quotes
        boolean isEligible = true; // notice reserved key words

        // JRE knows the size needed for Primatives
        // Primatives dont have members

        // COMPLEX TYPES / REFERENCE TYPES -------------------------
        Date now = new Date();
        //now.getTime(); // Reference Types have members
        System.out.println(now);

        // MEMORY MANAGEMENT VERSES 
        byte x = 1;
        byte y = 2;
        System.out.println(x);

        Point point1 = new Point(1, 1); // variable holds address in memory - not actual value
        Point point2 = point1;
        point1.x = 2;

        System.out.println(point2);

        // STRINGS
        String tempString = "Hello \"World"; // Escaping in a string

        // ARRAYS
        int[] numbers = new int[5];
        numbers[0] = 1;
        System.out.println(numbers); // Java will calculate the address of this array and return that value
        System.out.println(Arrays.toString(numbers));

        int[] numbersNewWay = {2, 3, 4, 5, 1}; // Java arrays have a fixed length
        Arrays.sort(numbersNewWay);

        int[][] multiDemiNumbers = new int[2][3];
        multiDemiNumbers[0][1] = 1;
        System.out.println(Arrays.deepToString(multiDemiNumbers)); // new method due to multidimensional array
        System.out.println("---------------");

        // CONSTANTS
        final float PI = 3.14F; // constants should be all CAPS

        // ARITHMETIC EXPRESSIONS
        int firstX = 1;
        int firstY = firstX++;
        int secondY = ++firstX;
        System.out.println(firstX);
        System.out.println(firstY);
        System.out.println(secondY);
        System.out.println("---------------");

        // ORDER OF OPERATIONS
        // P E MD AS

        // IMPLICIT CASTING
        short implyX = 1;
        int implyY = implyX + 1; // Java says short (2 bytes) can fit into int (4 bytes)
        System.out.println(implyY);
        System.out.println("---------------");

        // EXPLICIT CASTING
        // byte > short > int > long > float > double
        double explX = 1.1;
        int explY = (int)explX + 2;
        System.out.println("---------------");

        // MATH CLASS
        int xResult = Math.round(1.1F);
        double yResult = Math.ceil(1.1);
        double zResult = Math.floor(1.9);
        System.out.println(xResult);
        System.out.println(yResult);
        System.out.println(zResult);
        System.out.println("---------------");

        // NUMBER FORMATTING
        NumberFormat currency = NumberFormat.getCurrencyInstance(); // Asbstract class - use factory to return instance
        String result = currency.format(1234567.891);
        System.out.println(result);
        System.out.println("---------------");

        // READING USER INPUT
        Scanner scanner = new Scanner(System.in);
        byte yourAge = scanner.nextByte();
        System.out.println("You are " + yourAge);
    }
}
