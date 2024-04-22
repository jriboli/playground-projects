package org.example.iterable.demo;

// Problem:
// You have a list of integers, and you need to find and print the sum of all even numbers in the list.

import java.util.List;
import java.util.function.Consumer;

public class EasyProblem {

    public void runProblem(){
        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9,10);

        int sum = 0;
        for (Integer number : numbers) {
            if (number % 2 == 0) {
                sum += number;
            }
        }

        System.out.println(sum);
    }
}
