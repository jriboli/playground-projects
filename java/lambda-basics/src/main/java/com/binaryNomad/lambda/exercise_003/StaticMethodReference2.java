package com.binaryNomad.lambda.exercise_003;

import java.util.function.BiFunction;

public class StaticMethodReference2 {
    public static void main(String[] args) {

        BiFunction<Integer, Integer, Integer> customFI = StaticMethodReference2::addition;
        int sum = customFI.apply(10, 20);
        System.out.println("Addition : " + sum);

    }

    public static int addition(int a, int b) {
        return a + b;
    }
}
