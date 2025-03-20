package com.codewithmosh.collections;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {

    public static void show() {
        var c1 = new Customer("a", "e1");
        var c2 = new Customer("b", "e2");
        var c3 = new Customer("c", "e3");
        Map<String, Customer> customers = new HashMap<>();
        customers.put(c1.getEmail(), c1);
        customers.put(c2.getEmail(), c2);
        customers.put(c3.getEmail(), c3);
    }
}
