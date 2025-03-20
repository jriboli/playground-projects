package com.binaryNomad.katas;

public class MeanSquareError {

    public static double solution(int[] arr1, int[] arr2) {
        double results = 0;

        for(int i = 0; i < arr1.length; i++) {
            results += Math.pow((arr1[i] - arr2[i]), 2);
        }
        System.out.println(results);

        return results/arr1.length;
    }

    // Clever Answer:
    public static double solution_clever(int[] arr1, int[] arr2) {
        return java.util.stream.IntStream.range(0, arr1.length).map(i -> arr1[i] - arr2[i]).map(diff -> diff * diff).average().getAsDouble();
    }
}
