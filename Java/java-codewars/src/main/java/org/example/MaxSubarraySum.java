package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxSubarraySum {

    /*
    The maximum sum subarray problem consists in finding the maximum sum of a contiguous subsequence in an array or list of integers:

    Max.sequence(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4});
    // should be 6: {4, -1, 2, 1}
     */

    public int sequence(int[] arr) {
        //List<Integer> newList =
        boolean posNumExists = false;
        if(arr.length < 1)
            return 0;

        int maxSum = Arrays.stream(arr).sum();
        int subArraySize = arr.length -1;
        for(int i = 0; i < arr.length; i++) {
            if(i + subArraySize < arr.length) {
                //maxSum =
            }
        }

        return 0;
    }
}
