package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DescendingOrder_7kyu {

    //Your task is to make a function that can take any non-negative integer as an argument and return 
    //it with its digits in descending order. Essentially, rearrange the digits to create the highest possible number.

    public int sortDesc(final int num) {
        //Your code
        List<Integer> placeHolder = new ArrayList<>();
        int tempNum = num;
        while(tempNum > 0) {
            int currentNum = tempNum % 10;
            placeHolder.add(currentNum);
            tempNum /= 10;
        }
        // Sorting ArrayList in ascending Order
        Collections.sort(placeHolder);
        System.out.println(placeHolder);

        int result = 0;
        int index = placeHolder.size() - 1;
        for(Integer i : placeHolder) {
            double addNum = i * Math.pow(10, index);
            System.out.println(addNum);
            result += addNum;
            index -= 1;
        }

        return result;
    }
}