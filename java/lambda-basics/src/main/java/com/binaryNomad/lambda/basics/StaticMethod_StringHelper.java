package com.binaryNomad.lambda.basics;

public class StaticMethod_StringHelper {
    public static void printAsCapital(String word) {
        if(word == null | word.isEmpty())
            System.out.println();
        else
            System.out.println(word.substring(0,1).toUpperCase() + word.substring(1));
    }
}
