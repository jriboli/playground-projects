package com.codewithmosh.collections;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SetDemo {

    public static void show() {
        Set<String> firstSet = new HashSet<>(Arrays.asList("a", "b", "c"));
        Set<String> secondSet = new HashSet<>(Arrays.asList("b", "c", "d"));

        // Note these modify the sets, and does not produce a new set
        firstSet.addAll(secondSet);
        System.out.println("The combined sets : " + firstSet);
        firstSet.retainAll(secondSet);
        System.out.println("The interection of sets : " + firstSet);
        firstSet.removeAll(secondSet);
        System.out.println("The difference only of sets : " + firstSet);

    }
}
