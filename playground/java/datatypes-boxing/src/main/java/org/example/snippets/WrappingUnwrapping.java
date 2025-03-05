package org.example.snippets;

import java.util.ArrayList;

public class WrappingUnwrapping {

    // NOTE: Wrapping class constructors appear to be deprecated since version 9
    // and it is suggested to use .valueOf() instead.
    public static void demo()
    {
        // byte data type
        byte a = 1;

        // wrapping around Byte object
        Byte byteobj = Byte.valueOf(a);

        // int data type
        int b = 10;

        //wrapping around Integer object
        Integer intobj = Integer.valueOf(b);

        // float data type
        float c = 18.6f;

        // wrapping around Float object
        Float floatobj = Float.valueOf(c);

        // double data type
        double d = 250.5;

        // Wrapping around Double object
        Double doubleobj = Double.valueOf(d);

        // char data type
        char e='a';

        // wrapping around Character object
        Character charobj=e;

        // printing the values from objects
        System.out.println("Values of Wrapper objects (printing as objects)");
        System.out.println("Byte object byteobj: " + byteobj);
        System.out.println("Integer object intobj: " + intobj);
        System.out.println("Float object floatobj: " + floatobj);
        System.out.println("Double object doubleobj: " + doubleobj);
        System.out.println("Character object charobj: " + charobj);

        // objects to data types (retrieving data types from objects)
        // unwrapping objects to primitive data types
        byte bv = byteobj;
        int iv = intobj;
        float fv = floatobj;
        double dv = doubleobj;
        char cv = charobj;

        // printing the values from data types
        System.out.println("Unwrapped values (printing as data types)");
        System.out.println("byte value, bv: " + bv);
        System.out.println("int value, iv: " + iv);
        System.out.println("float value, fv: " + fv);
        System.out.println("double value, dv: " + dv);
        System.out.println("char value, cv: " + cv);

        // More about using Wrapper classes
        Integer i3 = 5000; // autoboxing
        Integer i4 = 5000; // autoboxing

        // i3 and i4 are both reference variables
        // In other words, their values are the memory addresses of the objects they are referring to
        // They are pointing to 2 different objects, and therefore, i3 == i4 will be false
        System.out.println("i3 == i4: " + (i3 == i4));

        /*
         * If you have 2 reference variables, and you use == operator, then if they are pointing to
         * 2 different objects, it will evaluate to false
         */

        // .equals() is a method that can be used for comparing the internal values of 2 objects
        System.out.println("i3.equals(i4): " + i3.equals(i4)); // both objects have the same internal value

        /*
         * The wrapper classes contain a lot of useful static methods that you can utilize
         * They also contain various constants defined statically
         */
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);

        // There are also methods to convert a String into its corresponding primitive value
        int age = Integer.parseInt("75"); // take the string "75" and give an int 75

        // Collections such as ArrayList can only deal with non-primitive types
        // for example, cannot store int primitives, only Integer objects
        ArrayList<Integer> myInts = new ArrayList<>();
        myInts.add(10);
        myInts.add(1000);
    }
}
