package org.example.iterable.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class iterableDemo {

    public static void runDemo() {
        // The Interface Collection extends Iterable
        // List extends Collection
        List<Integer> values = new ArrayList();
        values.add(4);
        values.add(6);
        values.add(9);
        // With List
        values.add(2, 2);

        System.out.println(values);

        // Iterator
        Iterator it = values.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }

        // For loop

    }


}
