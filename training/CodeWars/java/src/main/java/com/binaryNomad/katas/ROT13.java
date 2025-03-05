//https://www.codewars.com/kata/52223df9e8f98c7aa7000062/train/java
package com.binaryNomad.katas;

import static java.util.stream.Collectors.joining;

// I found this joke on USENET, but the punchline is scrambled. Maybe you can decipher it?
// According to Wikipedia, ROT13 is frequently used to obfuscate jokes on USENET.
public class ROT13 {

    public static String rot13(String message) {

        // If a-z or A-Z increase by 13 - wrap around alphabet
        StringBuilder result = new StringBuilder();

        // Loop through each char of string
        for (var letter : message.toCharArray()) {
            String newLetter = Character.isLetter(letter) ? decode(letter) + "" : letter + "";
            result.append(newLetter);
        }

        //System.out.println((int)'a');
        //System.out.println((int)'z');
        //System.out.println((int)'A');
        //System.out.println((int)'Z');

        System.out.println(result.toString());
        // return result
        return "";
    }

    public static char decode(char character) {
        int rotateNum = 13;
        int characterNum = (int) character;
        System.out.println("Before:" + String.valueOf(characterNum-rotateNum));
        if(characterNum >= 97 && characterNum <=122){
            characterNum = characterNum-rotateNum < 97 ? characterNum+rotateNum : characterNum-rotateNum;
        } else if (characterNum >= 65 && characterNum <=90) {
            characterNum = characterNum-rotateNum < 65 ? characterNum+rotateNum : characterNum-rotateNum;
        }
        System.out.println("After:" + String.valueOf(characterNum));
        char newCharacter = (char) ((char) characterNum);
        return newCharacter;
    }


    // Solution from another submission
    // Pretty clever using the map and ternary statement
    static String rot13_clever(String message) {
        return message.chars().mapToObj(c -> "" + (char) c)
                .map(c -> "" + (char) (c.charAt(0) + (c.matches("(?i)[A-M]") ? 13 : c.matches("(?i)[N-Z]") ? -13 : 0)))
                .collect(joining());
    }
}
