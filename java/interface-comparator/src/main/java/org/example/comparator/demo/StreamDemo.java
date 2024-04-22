package org.example.comparator.demo;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamDemo {

    public static void runDemo() {
        List<Integer> nums = Arrays.asList(4,5,7,3,2,6);

        System.out.println("-- STREAMS -------------------------------------------");

        // Any changes to s1 will not affect the nums array
        Stream<Integer> s1 = nums.stream();

        // Once you use a stream you cant re-use it
        // Methods that return a Stream
        Stream<Integer> s2 = s1.filter(n -> n%2 == 0);
        Stream<Integer> s3 = s2.map(n -> n*2);
        // Methods that dont
        int result = s3.reduce(0, (c,e) -> c+e);
        System.out.println(result);
        //s3.forEach(System.out::println);

        //Everything on one line
        int newResult = nums.stream()
                .filter(n -> n%2 == 0)
                .map(n -> n*=2)
                .reduce(0, (c,e) -> c+e);
        System.out.println(newResult);

        // The above result is the same logic as below:
        int sum = 0;
        for(int n : nums) {
            if(n%2 == 0) {
                n = n*2;
                sum = sum + n;
            }
        }
        System.out.println(result);
    }

    public static void runStreamsBreakdown() {
        List<Integer> nums = Arrays.asList(4,5,7,3,2,6);

        System.out.println("-- STREAMS:: FILTERS -------------------------------------------");

        Predicate<Integer> p = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer % 2 == 0;
            }
        };

        //Using Lambda, since Predicate is a FunctionalInterface
        Predicate<Integer> p2 = i -> i%2 ==0;

        Function<Integer, Integer> fun = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer*2;
            }
        };

        // Simplify with Lambda
        Function<Integer, Integer> fun2 = i -> i*2;

        BinaryOperator<Integer> bo = new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        };

        // Simplified with Lambda
        BinaryOperator<Integer> bo2 = (c, e) -> (c + e);

        int result = nums.stream()
                // Filter takes a Predicate interface
                .filter(p2)
                .map(fun2)
                // C= carry, E =
                // 0 is initial value
                .reduce(0, bo2);

        System.out.println(result);
    }
}
