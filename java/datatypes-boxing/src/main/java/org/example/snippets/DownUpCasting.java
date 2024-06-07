package org.example.snippets;

import java.sql.SQLOutput;

public class DownUpCasting {

    public void downCasting() {
        double d = 4.5;
        //int i = d; // Incompatible types. Found: 'double', required: 'int'
        // We can not down cast double to int
        // But we can typecasting
        int i = (int) d;

        System.out.println(d);
    }

    public void upCastClass() {
        // Up casting moving to parent reference
        A obj = (A) new B();
        // Works as this too:
        // A obj = new B();
        // Automatically upcasting happens
        obj.show1();

    }

    public void downCastClass() {
        // Down casting move to child reference
        A obj = new B();
        obj.show1();

        B obj1 = (B) obj;
        obj1.show2();
    }
}

