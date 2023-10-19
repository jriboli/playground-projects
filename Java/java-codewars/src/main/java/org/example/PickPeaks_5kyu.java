package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class PickPeaks_5kyu {

    /*
    In this kata, you will write a function that returns the positions and the values of the "peaks" (or local maxima) of a numeric array.

    For example, the array arr = [0, 1, 2, 5, 1, 0] has a peak at position 3 with a value of 5 (since arr[3] equals 5).

    The output will be returned as a ``Map<String,List>with two key-value pairs:"pos"and"peaks".
    If there is no peak in the given array, simply return {"pos" => [], "peaks" => []}`.
     */
    public Map<String,List<Integer>> getPeaks(int[] arr) {
        // Your code here!
        Map<String, List<Integer>> results = new HashMap<>();
        List<Integer> positions = new ArrayList<>();
        List<Integer> peaks = new ArrayList<>();
        if(arr.length < 1) {
            results.put("pos", positions);
            results.put("peaks", peaks);
            return results;
        }

        int currPeak = arr[0];
        int currPos = 0;
        boolean foundPeak = false;
        for(int i = 1; i < arr.length; i++) {
            if(arr[i] > currPeak) {
                foundPeak = false;
            } else if (arr[i] < currPeak) {
                if(currPos == 0) {
                    foundPeak = true;
                }
                if(!foundPeak) {
                    positions.add(currPos);
                    peaks.add(currPeak);
                    foundPeak = true;
                }
            }

            if(arr[i] != currPeak) {
                currPeak = arr[i];
                currPos = i;
            }
        }

        results.put("pos", positions);
        results.put("peaks", peaks);
        return results;
    }
}
