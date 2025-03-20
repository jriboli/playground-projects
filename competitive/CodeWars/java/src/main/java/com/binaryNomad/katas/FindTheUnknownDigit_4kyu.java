package com.binaryNomad.katas;

import java.util.ArrayList;
import java.util.List;

//https://www.codewars.com/kata/546d15cebed2e10334000ed9/java
public class FindTheUnknownDigit_4kyu {
    private static final String SPECIAL_OPERATOR_CHAR = "(?<=[0-9?])-(?=.*[0-9?])|[+*=]";
    private static final List<Integer> KNOWN_DIGITS = List.of(0,1,2,3,4,5,6,7,8,9);

    public static int solveExpression( final String expression ) {
        int missingDigit = -1;

        //Write code to determine the missing digit or unknown rune
        //Expression will always be in the form
        //(number)[operator](number)=(number)
        //Unknown digit will not be the same as any other digits used in expression

        String[] expressionValues = expression.split(SPECIAL_OPERATOR_CHAR);
        String operator = expression.substring(expressionValues[0].length(), expressionValues[0].length()+1);
        List<Integer> validDigits = findValidNumbersToTry(expression);
        missingDigit = runThePossibilities(validDigits, operator, expressionValues[0], expressionValues[1], expressionValues[2]);

        System.out.println("Result: " + missingDigit);
        return missingDigit;
    }

    private static int runThePossibilities(List<Integer> validDigits, String operator, String value1, String value2, String value3) {

        if((value1.length() > 1 && (value1.indexOf("?") == 0 || value1.indexOf("-?") == 0)) ||
            (value2.length() > 1 && (value2.indexOf("?") == 0 || value2.indexOf("-?") == 0)) ||
            (value3.length() > 1 && (value3.indexOf("?") == 0 || value3.indexOf("-?") == 0)))
            validDigits.remove((Integer)0);

        for(Integer i : validDigits) {
            int num1 = replaceQuestionMarks(value1, i);
            int num2 = replaceQuestionMarks(value2, i);
            int num3 = replaceQuestionMarks(value3, i);

            if(checkTheEquation(operator, num1, num2, num3)){
                return i;
            }
        }
        return -1;
    }

    private static boolean checkTheEquation(String operator, int value1, int value2, int result){
        return switch (operator) {
            case "+" -> value1 + value2 == result;
            case "-" -> value1 - value2 == result;
            case "*" -> value1 * value2 == result;
            default -> false;
        };
    }

    private static int replaceQuestionMarks(String value, int num) {
        return Integer.parseInt(value.replace("?", num+""));
    }

    private static List<Integer> findValidNumbersToTry(String expression){

        List<Integer> validDigits = new ArrayList<>();
        for(Integer i : KNOWN_DIGITS) {
            if(!expression.contains(i + ""))
                validDigits.add(i);
        }

        return validDigits;
    }

    // CLEVER ANSWER

    /*
      private static Set<String> DIGITS = new HashSet<String>(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));

      private static String NUMS = "[\\d?]+";
      private static Pattern TOKENIZER = Pattern.compile(String.format("(-?%1$s)([*+-])(-?%1$s)=(-?%1$s)", NUMS));
      private static String INVALID_0 = ".*?(?<!"+NUMS+")[?]"+NUMS+"+.*";

      public static int solveExpression(String runes) {

        Set<String> knownDigits = new HashSet<String>();
        for (int i = 0 ; i < runes.length() ; i++) knownDigits.add(runes.substring(i,i+1));
        knownDigits.retainAll(DIGITS);

        for (String d: DIGITS) {
            if (knownDigits.contains(d) || d.equals("0") && runes.matches(INVALID_0)) continue;

            Matcher m = TOKENIZER.matcher(runes);
            m.find();
            String a  = m.group(1).replace("?",d),
                   op = m.group(2),
                   b  = m.group(3).replace("?",d),
                   c  = m.group(4).replace("?",d);
            int x = Integer.parseInt(a),
                y = Integer.parseInt(b),
                z = Integer.parseInt(c),
                digit = Integer.parseInt(d);
            switch (op) {
                case "+": if (x+y==z) return digit; break;
                case "-": if (x-y==z) return digit; break;
                case "*": if (x*y==z) return digit; break;
            }
        }
        return -1;
      }

    */

}
