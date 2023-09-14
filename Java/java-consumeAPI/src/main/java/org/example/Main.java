package org.example;

import org.example.classes.GetDotNetValues;
import org.example.classes.StripeExample;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        GetDotNetValues dotNetValues = new GetDotNetValues();
        var valueOne = dotNetValues.getValue("MY_ENV_VAR1");
        System.out.println(valueOne);

        //StripeExample stripe = new StripeExample();
    }
}