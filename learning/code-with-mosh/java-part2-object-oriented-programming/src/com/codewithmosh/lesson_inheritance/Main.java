package com.codewithmosh.lesson_inheritance;

import java.sql.SQLOutput;

public class Main {

    public static void main(String[] args){
        var tControl = new TextBox();
        tControl.disabled();
        tControl.setText("Hello World");
        System.out.println(tControl.isEnabled());
        System.out.println(tControl.toString());

        //var obj = new Object();

        var point1 = new Point(1,2);
        var point2 = new Point(1,2);

        // Since points of reference types so value points to different values
        System.out.println(point1 == point2);
        // same with equals - but we can override the method
        System.out.println(point1.equals(point2));
        // If you override equals, you should also override hashcode

        UIControl[] controls = { new TextBox(), new CheckBox()};
        for(var control : controls){
            control.render();
        }

    }
}
