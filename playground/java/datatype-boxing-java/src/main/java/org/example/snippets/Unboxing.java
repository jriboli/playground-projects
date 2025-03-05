package org.example.snippets;

import java.util.ArrayList;

public class Unboxing {

    public static void demo() {
        Character ch = 'a';

        // Unboxing, character object to primitive conversion
        char a = ch;

        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(24);

        // Unboxing because get method returns an Integer object
        int num = arrayList.get(0);

        // Printing the values from primitive datatype
        System.out.println(num);
    }
}
