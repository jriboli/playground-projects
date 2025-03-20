package com.binaryNomad.problems;

import java.lang.reflect.Array;
import java.util.Arrays;

public class bigONotation_intro {
    /*
    * Algorithms should be 'deterministic':
    * Given the same input, the algorithm should always produce the same output.
    *
    * The algorithm should be correct for any arbitrary valid input.
    *
    * Time complexity: As the input size grows, how much longer does the algorithm take?
    * Space complexity: As the input size grows, how much more memory doe the algorithm use?
    *
    * Complexity is described by a function (math formula). Most common vairalbe you'll
    * see is n, this should represent the value that changes between inputs, and it directly
    * affects the algorithm.
    */

    public void largestNumInArray(Integer[] numbers){
        /*
         * This algorithm is of O(n), n representing the length of numbers
         *
         * If we pass an array with length of 10, we perform approximately 10 steps
         * if we pass an array with length of 10,000,000, we perform 10,000,000 steps
         */

        int maxNum = 0;
        for(Integer i : numbers) {
            if(i > maxNum)
                maxNum = i;
        }

        // The point of time complexity is to describe how the number of operations changes at the input changes.
    }

    /*
     * Rules:
     * - We ignore constants
     * Example: O(9999999n) = O(8n) = O(n) = 0(n/500)
     * When n is 100 verse when n is 500. As the input size increases, the number of operations increase linearly.
     *
     * - Tend to infinity
     * Example: O(2^n + n^2 - 500n) = O(2^n)
     * We ignore all terms except the most powerful one. As we go to infinity, 2^n becomes so large that the
     * other terms have zero effect.
     */

    public void LoopingArray(Integer[] numbers) {
        /*
         * The algorithm is of O(n^2)
         * - Each inner look iterations, we perform a multiplication and print = O(1)
         * - The inner for runs n times = O(n)
         * - The outer look run n times = O(n)
         * Which give a time complexity of O(n * n) = O(n^2)
         */
        for(Integer i : numbers){
            for (Integer j : numbers){
                System.out.println(i * j);
            }
        }
    }

    public void DiffLoopingArray(Integer[] numbers) {
        /*
         * The algorithmn is of O(n^2)
         * The inner loop is dependent on what iterations the outer
         */
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i; j < numbers.length; j++) {
                System.out.println(numbers[i] + numbers[j]);
            }
        }
    }
}
