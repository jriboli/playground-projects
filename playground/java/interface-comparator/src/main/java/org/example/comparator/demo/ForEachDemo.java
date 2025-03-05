package org.example.comparator.demo;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ForEachDemo {

    public static void runDemo() {
        List<Integer> nums = Arrays.asList(4,5,7,3,2,6);

        System.out.println("-- DEFINING CONSUMER -------------------------------------------");
        // The Consumer interface has one method - accept
        Consumer<Integer> con = new Consumer<Integer>() {
            // Accept will take each item one at a time from the List
            // and you get to decide the action
            public void accept(Integer integer) {
                // Action...
                System.out.println(integer);
            }
        };

        // Above can be reduced to
        // Since only one action we can remove { and }
        // Since only on parameter (i) we can remove the ( and )
        Consumer<Integer> con2 = i -> System.out.println(i);
        // ForEach takes a Consumer interface
        nums.forEach(con2);

        System.out.println("-- SIMPLIFIED CONSUMER -------------------------------------------");
        // Taken even further
        // Notice we just took the assignment of Consumer and moved it into an argument
        // passed to the .forEach method.
        nums.forEach(i -> System.out.println(i));

        System.out.println("-- TRYING ANDTHEN() IN CONSUMER -------------------------------------------");
        //Try using the andThen for Consumer
        Consumer<Integer> con3 = i -> i*=2;
        con3 = con3.andThen(System.out::println);
        // Sout Lambda can be replaced with Method interface -- Look into this ???
        nums.forEach(con3);
    }

}
