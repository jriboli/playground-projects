package com.binaryNomad.problems;

public class ArraysNStrings_SlidingWindow {
    /*
     * SubArray | Sliding Window Problems
     * - Constraint Metric:
     *
     * - Numeric Restriction:
     *
     * Formula for how many subarrays are there: (n * (n + 1)) / 2
     * So any algorithm that looks at every subarray will be at least O(n^2) - too slow
     * The sliding window guarantees a max of O(2n)
     *  - The right pointer can move n times and the left pointer can move n times
     */

    /*
     * Example 1: Given an array of positive integers 'nums' and an integer 'k', find the length
     * of the longest subarray whose sum is less than or equal to k.
     */
    public static int findMaxSubArray(int[] nums, int k) {
        int left = 0;
        int currSum = 0;
        int ans = 0;

        // Note about amortized O(1) time complexity - for all wok down inside the loop, it can only be done n times
        for(int right = 0; right < nums.length; right++) {
            currSum += nums[right];

            while(currSum > k) {
                currSum -= nums[left];
                left++;
            }

            ans = Math.max(ans, right-left+1);
        }

        return ans;
    }

    /*
     * Example 2: Given a binary string s. Choose up to one "0" to flip to "1". What is the length of longest
     * substring that contains only "1"
     */
    public static int findMaxSubArrayWith1(String s) {
        int left = 0;
        int currSum = 0;
        int ans = 0;

        for (int right = 0; right < s.length(); right++) {
            if(s.charAt(right) == '0'){
                currSum++;
            }

            while (currSum > 1) {
                if(s.charAt(left) == '0') {
                    currSum--;
                }

                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }

    /*
     * Example 3: SubArray Product less than K
     *
     * Given an array of positive integers nums and an integer k, return the number of sunarrays
     * where the prodc=ucts f all the elements
     */
    public int findSubArrayWithProductLessThan(int[] nums, int k) {
        if(k <= 1) {
            return 0;
        }

        int left = 0;
        int currSum = 1;
        int ans = 0;

        for(int right = 0; right < nums.length; right++) {
            currSum *= nums[right];
            while (currSum >= k) {
                currSum = currSum / nums[left];
                left++;
            }

            ans += right - left + 1;
        }

        return ans;
    }

    /*
     * Example 4: Given an integer array nums and an integer k, find the sum of the subarray with the largest
     * sum whose length is k.
     */
    public int findBestSubArray(int[] nums, int k) {
        int currSum = 0;
        for (int i = 0; i < k; i++) {
            currSum += nums[i];
        }

        int ans = currSum;
        for (int i = k; i < nums.length; i++) {
            currSum += nums[i] - nums[i-k];
            ans = Math.max(ans, currSum);
        }

        return ans;
    }

    /*
     * Problem 1: Given an integer array nums consisting o n elements, and a integer k.
     * Find a contiguous subarray whole length is equal to k that has the max avg value.
     */
    public double findMaxAverage(int[] nums, int k) {
        double currSum = 0;
        // First we find the value of the first K length subarray
        for(int i = 0; i < k; i++) {
            currSum += nums[i];
        }

        double ans = currSum / k;
        for(int i = k; i <nums.length; i++ ){
            // i can be renamed to right side of window
            // We move the window other 1 space. Dropping the lead [i-k] and adding a new one on the end [i].
            currSum += nums[i] - nums[i-k];
            ans = Math.max(ans, currSum / k);
        }

        return ans;
    }

    /*
     * Problem 2: Given a binary array nums and an integer k, return the max numb of consecutive 1
     * if you can flip at most k 0's.
     */
    public static int longestOnes(int[] nums, int k) {
        int left = 0;
        int currSum = 0;
        int ans = 0;

        for(int right = 0; right < nums.length; right++) {
            if(nums[right] == 0){
                currSum++;
            }

            while (currSum > k) {
                if(nums[left] == 0) {
                    currSum--;
                }

                left++;
            }

            ans = Math.max(ans, right - left + 1);
        }

        return ans;
    }
}
