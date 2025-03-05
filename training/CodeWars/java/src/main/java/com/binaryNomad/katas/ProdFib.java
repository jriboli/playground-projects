package com.binaryNomad.katas;

import java.util.ArrayList;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

// https://www.codewars.com/kata/5541f58a944b85ce6d00006a/train/java
public class ProdFib { // must be public for codewars

    public static long[] productFib(long prod) {
        long current = 0;
        long next = calculateFibonacci(0);
        int position = 0;
        while (current*next < prod) {
            position++;
            current = calculateFibonacci(position);
            next = calculateFibonacci(position+1);
        }

        return new long[]{ current, next, current*next == prod ? 1 : 0};
    }

    public static long calculateFibonacci(int n) {
        double root5 = sqrt(5);
        double gr = (1 + root5) / 2;
        double igr = 1 - gr;
        double value = (pow(gr, n) - pow(igr, n)) / root5;

        return Math.round(value);
    }
}
