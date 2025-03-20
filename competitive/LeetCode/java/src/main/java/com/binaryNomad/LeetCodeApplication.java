package com.binaryNomad;

import com.binaryNomad.problems.ArraysNStrings_PrefixSum;
import com.binaryNomad.problems.ArraysNStrings_SlidingWindow;

public class LeetCodeApplication {
    public static void main(String[] args) {

        // ******************** Arrays N Strings - Two Pointers ********************
        //System.out.println(ArraysNStrings_TwoPointers.checkForTargetOofSquared(new int[]{1, 2, 4, 6, 8, 9, 14, 15}, 14));
        //System.out.println(ArraysNStrings_TwoPointers.checkForTargetOofN(new int[]{1, 2, 4, 6, 8, 9, 14, 15}, 14));

        // Problem 1
        //System.out.println(ArraysNStrings_TwoPointers.reverseString(new char[]{'h','e','l','l','o'}));
        //System.out.println(ArraysNStrings_TwoPointers.reverseString(new char[]{'H','a','n','n','a','h'}));

        // Problem 2
        //Arrays.stream(ArraysNStrings_TwoPointers.sortedSquares(new int[]{-7, -3, 2, 3, 11})).forEach(i -> System.out.println(i));
        //System.out.println("--------");
        //Arrays.stream(ArraysNStrings_TwoPointers.sortedSquares(new int[]{-4, -1, 0, 3, 10})).forEach(i -> System.out.println(i));
        //System.out.println("--------");
        //Arrays.stream(ArraysNStrings_TwoPointers.sortedSquares(new int[]{-5, -3, -2, -1})).forEach(i -> System.out.println(i));
        //System.out.println("--------");
        //Arrays.stream(ArraysNStrings_TwoPointers.sortedSquares(new int[]{-1, 2, 2})).forEach(i -> System.out.println(i));
        //System.out.println("--------");
        //Arrays.stream(ArraysNStrings_TwoPointers.sortedSquares(new int[]{-10000, -9999, -7, -5, 0, 0, 10000})).forEach(i -> System.out.println(i));

        // ******************** Arrays N Strings - Sliding Window ********************
        //System.out.println(ArraysNStrings_SlidingWindow.findMaxSubArray(new int[]{3,1,2,7,4,2,1,1,5}, 8));
        //System.out.println(ArraysNStrings_SlidingWindow.findMaxSubArrayWith1("11001011"));

        // Problem 1

        // Problem 2
        //System.out.println(ArraysNStrings_SlidingWindow.longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0}, 2));
        //System.out.println(ArraysNStrings_SlidingWindow.longestOnes(new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}, 3));

        // ******************** Arrays N Strings - Prefix Sum ********************
        //ArraysNStrings_PrefixSum.answerQueries(new int[]{1,6,3,2,7,2}, new int[][]{{0,3}, {2,5}, {2,4}}, 13);
        //ArraysNStrings_PrefixSum.waysToSplitArray(new int[] {10,4,-8,7});

        // Problem 2
        //System.out.println("Problem 2: " + ArraysNStrings_PrefixSum.minStartValue(new int[] {-3, 2, -3, 4, 2}));

        // Problem 3
        ArraysNStrings_PrefixSum.getAverages(new int[] {7,4,3,9,1,8,5,2,6}, 3);
    }
}