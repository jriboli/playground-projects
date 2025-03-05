package com.binaryNomad.katas;

import static java.util.stream.Stream.of;

//https://www.codewars.com/kata/59c633e7dcc4053512000073/train/java
public class ConsonantValue {

    public static int solve(final String s) {
        int result = 0;
        int interim = 0;
        for (var c : s.toCharArray()) {
            System.out.println(c);
            System.out.println("Start:" + interim);
            if(c != 'a' && c != 'e' && c != 'i' && c != 'o' && c != 'u')
                interim += ((int) c) -96;
            else
                interim = 0;

            System.out.println("End:" + interim);
            if(interim > result)
                result = interim;
        }
        System.out.println(result);
        return result;
    }

    // Solution from another submission
    // Pretty clever using the map and ternary statement
    static int solve_clever(String s) {
        return of(s.split("[aeiou]")).mapToInt(con -> con.chars().map(c -> c - 96).sum()).max().orElse(0);
    }
}
