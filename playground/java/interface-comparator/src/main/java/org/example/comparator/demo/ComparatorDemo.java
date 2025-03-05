package org.example.comparator.demo;

import java.util.*;

public class ComparatorDemo {

    public static void runDemo() {
        List<Integer> nums = new ArrayList<>();
        nums.add(43);
        nums.add(31);
        nums.add(72);
        nums.add(29);

        // Using default comparison
        Collections.sort(nums);
        System.out.println(nums);

        // Works with anonymous class
        Comparator<Integer> sortByLastDigit = new Comparator<Integer>()
        {
            // Check to swap items
            // Return 1 = swap
            // Return -1 = no swap
            public int compare(Integer i, Integer j) {
                if (i%10 > j%10) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };
    }

    public static void sortLexiconigoly() {
        List<String> words = Arrays.asList("banana", "apple", "babble", "abracadabra", "absent", "bumble", "allure",
                "adobe", "braid", "agile", "azure", "balloon", "abrupt", "baboon", "abate", "biscuit", "apricot", "beetle",
                "abuzz", "bamboo");

        var sortedWords = words.stream().sorted();

        sortedWords.forEach(System.out::println);
    }

}
