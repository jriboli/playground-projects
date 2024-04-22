package org.example.snippets;

public class JavaWrappers {
    // For every primative type there is a wrapper class

    public void intWrapper(){
        int num = 7;

        // boxing - taking a primative value and storing it in a class obj
        //Integer num1 = new Integer(num);
        // Autoboxing - happening automatically
        Integer num1 = num;

        // Unboxing
        int num2 = num1.intValue();

        System.out.println(num2);
    }
}
