package org.example;

import java.math.BigInteger;
import java.util.*;

//https://www.codewars.com/kata/525f4206b73515bffb000b21/train/java
public class AddingBigNumbers {

    // Cant use java.math
    public static String add(String a, String b) {
        //BigInteger newA =  new BigInteger(a);
        //BigInteger newB =  new BigInteger(b);
        //return newA.add(newB) + "";

        int carry = 0;
        var reversedA = convert(a);
        var reversedB = convert(b);

        var results = add(reversedA, reversedB);
        System.out.println(results);

        return "";
    }

    public static List<Integer> convert(String value) {
        List<Integer> results = new ArrayList<>();
        var charArray = value.toCharArray();

        for(int i = value.length(); i > 0; i--){
            results.add(Integer.parseInt(charArray[i-1]+""));
        }

        return results;
    }

    public static List<Integer> add(List<Integer> a, List<Integer> b){
        List<Integer> results = new ArrayList<>();
        int listLength = a.size() > b.size() ? a.size() : b.size();

        int carry = 0;
        for (int i =0; i < listLength; i++) {
            if(a.size() >= i && b.size() >= i) {
                var addition = a.get(i) + b.get(i);
                results.add(addition % 10 + carry);
                carry = addition > 10 ? 1 : 0;
            } else if (a.size() < i) {
                results.add(b.get(i));
            } else {
                results.add(a.get(i));
            }
        }

        for(int i =results.size(); )

        return results;
    }
}
