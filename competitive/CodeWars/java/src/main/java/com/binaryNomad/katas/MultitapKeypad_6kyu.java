package com.binaryNomad.katas;

import java.util.ArrayList;
import java.util.List;

public class MultitapKeypad_6kyu {

    public static int presses(String phrase) {
        int numberOfPresses = 0;
        char[] letters = phrase.toCharArray();
        List<String> keyPads = new ArrayList<String>();

        keyPads.add("1");
        keyPads.add("ABC2");
        keyPads.add("DEF3");
        keyPads.add("GHI4");
        keyPads.add("JKL5");
        keyPads.add("MNO6");
        keyPads.add("PQRS7");
        keyPads.add("TUV8");
        keyPads.add("WXYZ9");
        keyPads.add("*");
        keyPads.add(" 0");
        keyPads.add("#");

        for(char letter : letters) {
            String upperCaseLetter = (letter + "").toUpperCase();
            for(String key : keyPads) {
                if(key.contains(upperCaseLetter)) {
                    numberOfPresses += key.indexOf(upperCaseLetter) + 1;
                    System.out.println(numberOfPresses + " : " + (key.indexOf(upperCaseLetter)+1));
                }
            }
        }

        return numberOfPresses;
    }
}
