package org.example;

import java.util.HashMap;
import java.util.Map;

public class CountCharacters_6kyu {

    /*
    The main idea is to count all the occurring characters in a string. If you have a string like aba, then the result should be {'a': 2, 'b': 1}.

    What if the string is empty? Then the result should be empty object literal, {}.
     */
    public Map<Character, Integer> count(String str) {
        // Happy coding!
        Map<Character, Integer> results = new HashMap<>();
        for(char c : str.toCharArray()) {
            if(results.containsKey(c)) {
                results.replace(c, results.get(c) + 1);
            } else {
                results.put(c, 1);
            }
        }
        return results;
    }
}
