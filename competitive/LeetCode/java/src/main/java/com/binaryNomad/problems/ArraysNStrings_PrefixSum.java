package com.binaryNomad.problems;

public class ArraysNStrings_PrefixSum {

    /***
     * Prefix Sum ---
     * Formula:
     *      prefix[j] - prefix[i] + nums[i]
     *      prefix[j] - prefix[i -1]
     *
     * nums[] {3, 6, 2, 8, 1, 4, 1, 5}
     * pref[] {3, 9, 11, 19, 20, 24, 25, 30}
     *
     * How to build the prefix array
     * prefix = [nums[0]]
     * for (int i = 1; i < nums.length; i++)
     *      prefix.append(nums[i] + prefix[prefix.length-1])
     */

    /*
     * Example 1: Given an integer array nums, an array queries where queries[i] = [x,y]
     * and an integer limit, return a boolean aray that represents the answer to each query.
     *
     * nums = [1, 6, 3, 2, 7, 2]
     * queries = [[0,3], [2,5], [2,4]]
     */

    public static boolean[] answerQueries(int[] nums, int[][] queries, int limit) {
        int[] prefix = new int[nums.length];
        prefix[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // We have already populated position 0 and we are starting at 1
            // The prefix[i-1] is the current sum of all numbers before it
            prefix[i] = prefix[i -1] + nums[i];
        }

        boolean[] ans = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int x = queries[i][0], y = queries[i][1];
            int curr = prefix[y] - prefix[x] + nums[x];
            ans[i] = curr < limit;
            System.out.println("ANSWER: " + ans[i]);
        }

        return ans;
    }

    /*
     * Example 2: 2270. Number of Ways to Split Array
     *
     * Given an integer array nums, find the number of ways to split the array into two parts so that the
     * first section has a sum greater than or equal to the sum of the second section.
     */
    public static int waysToSplitArray(int[] nums) {
        int[] prefix = new int[nums.length];
        prefix[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i -1] + nums[i];
        }

        int ans = 0;
        for (int i = 0; i < prefix.length - 1; i++) {
            int left_section = prefix[i];
            int right_section = prefix[prefix.length -1] - prefix[i];
            if(left_section >= right_section) {
                ans += 1;
            }
        }

        System.out.println("Ways 2 Split: " + ans);
        return ans;
    }

    /*
     * Problem 1: Given an array nums, We define a running sum of an array as runningSum[i] = sum(nums[0]...nums[i])
     * Return the running sum of nums
     */
    public static int[] runningSum(int[] nums) {
        int[] prefix = new int[nums.length];
        prefix[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i -1] + nums[i];
        }

        return prefix;
    }

    /*
     * Problem 2: Given an array of integers nums, you start with an initial positive value 'startValue'.
     * In each iteration, you calculate the step by step sum of startValue plus elements in nums (left to right)
     * Return the minimum positive value of startValue such that the step by step sum is never less than 1.
     */
    public static int minStartValue(int[] nums) {
        int minStartValue = 1;
        int currSum = 0;
        for(int i = 0; i < nums.length; i++) {
            currSum += nums[i];
            while(minStartValue + currSum < 1) {
                minStartValue += 1;
            }
        }

        return minStartValue;
    }

    /***
     * Problem 3: Given a 0-indexed array nums of n integers, and an integer k.
     * The k-radius average for a subarray of nums centered at some index i with the radius k is the average of all
     * elements in nums between the indices i -k and i + k (inclusive)
     */
    public static int[] getAverages(int[] nums, int k) {
        int[] prefix = new int[nums.length];
        prefix[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i -1] + nums[i];
        }

        int[] avgs = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            int span = i + (k * 2);
            if(i < k || (nums.length - i) <= k) {
                System.out.println("Catch Answer: " + -1);
                avgs[i] = -1;
            } else if (i == k ) {
                int ans = prefix[i+k] / span;
                System.out.println("Else Answer: " + ans);
                avgs[i] = ans;
            } else {
                int left = prefix[i-k] - nums[i];
                int right = prefix[i+k];
                System.out.println("Left: " + left + " || Right: " + right);
                int ans = (right - left) / span;
                System.out.println("Answer: " + ans);
                avgs[i] = ans;
            }
        }

        return avgs;
    }
}
