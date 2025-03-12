package com.codewithmosh.lambdas;

public class LambdaDemo {

    public static void show() {
        greet(new ConsolePrinter());
    }

    public static void greet(Printer printer) {
        printer.print("Hello World!");
    }
}
