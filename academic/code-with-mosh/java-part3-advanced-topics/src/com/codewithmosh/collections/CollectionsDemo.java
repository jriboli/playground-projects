package com.codewithmosh.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class CollectionsDemo {
    public static void show() {
        Collection<String> collection = new ArrayList<String>();
        // Adding multiple values in one command
        Collections.addAll(collection, "a", "b", "c");

        Collection<String> other = new ArrayList<String>();
        other.addAll(collection);

        System.out.println("Does collection == other: " + (collection == other));
        System.out.println("Now let's compare correctly, .equals(): " + (collection.equals(other)));
    }
}
