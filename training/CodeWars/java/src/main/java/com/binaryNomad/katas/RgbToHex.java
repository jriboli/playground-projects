package com.binaryNomad.katas;

// https://www.codewars.com/kata/513e08acc600c94f01000001/train/java
public class RgbToHex {

    public static String rgb(int r, int g, int b) {
        // Note: hex color is base 16
        System.out.println(convertRGBColor(r)+convertRGBColor(g)+convertRGBColor(b));
        return convertRGBColor(r)+convertRGBColor(g)+convertRGBColor(b);
    }

    public static String convertRGBColor(int color) {
        if(color < 0) {
            return "00";
        } else if (color > 255) {
            return "FF";
        }
        int secondChar = color%16;
        int firstChar = color/16;
        System.out.printf("Color: %s, 1st: %s, 2nd: %s", color, firstChar, secondChar);

        return convertNumberToLetter(firstChar) + convertNumberToLetter(secondChar);
    }

    public static String convertNumberToLetter(int colorNum) {
        switch(colorNum){
            case 15:
                return "F";
            case 14:
                return "E";
            case 13:
                return "D";
            case 12:
                return "C";
            case 11:
                return "B";
            case 10:
                return "A";
            default:
                return String.valueOf(colorNum);
        }
    }
}

