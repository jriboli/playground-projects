package com.binaryNomad.lambda.basics;

public class GreetImplementor implements Greeter{
    @Override
    public void greet(String s) {
        System.out.println("Hello, " + s);
    }
}
