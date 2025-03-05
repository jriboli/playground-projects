package com.binaryNomad.katas;

import java.util.Arrays;

public class MexicanWave_6kyu {
    /*
    In this simple Kata your task is to create a function that turns a string into a Mexican Wave.
    You will be passed a string and you must return that string in an array where an uppercase letter is a person standing up.
     */

    public String[] wave(String str) {
        // Your code here
        String[] results = new String[str.length()];
        int strIndex = 0;
        int arrayIndex = 0;
        for(char c : str.toCharArray()) {
            StringBuilder sb = new StringBuilder(str);
            if(c != ' ') {
                sb.setCharAt(strIndex, (Character.toUpperCase(c)));
                results[arrayIndex] = sb.toString();

                arrayIndex += 1;
            } else {
                results = Arrays.copyOf(results, results.length - 1);
            }

            strIndex += 1;
        }

        for(String s : results) {
            System.out.println(s);
        }

        return results;
    }

//    public static String[] wave(String str) {
//        List<String> list = new ArrayList<>();
//        for (int i = 0; i < str.length(); i++) {
//            char ch = str.charAt(i);
//            if (ch == ' ') continue;
//            list.add(str.substring(0,i) + Character.toUpperCase(ch) + str.substring(i+1));
//        }
//        return list.toArray(new String[0]);
//    }
}
