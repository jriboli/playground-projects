package org.example.snippets;

import java.util.ArrayList;

public class Autoboxing {

    public static void demo() {
        char ch = 'a';

        // Autoboxing - primitive to Character object conversion
        Character a = ch;

        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        // Here, autoboxing of the in 25 takes place because arrayList only stores Integer objects.
        arrayList.add(25);

        // Printing the values from object
        System.out.println(arrayList.get(0));
    }
}
