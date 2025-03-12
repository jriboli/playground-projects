package com.codewithmosh.lambdas;

public interface Printer {
    void print(String message);

    default void printTwice(String message) {
        System.out.println(message);
        System.out.println(message);
    }
}
