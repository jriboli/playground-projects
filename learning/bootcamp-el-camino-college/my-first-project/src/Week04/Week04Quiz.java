package Week04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Week04Quiz {
	public static void main(String[] args) {
		System.out.println(power(3,4));
		quizQuestion3();
		
		
		// Testing
		System.out.println(repeatWord("Java", 5));
		
		//Question 5: 
		List<String> listOfNames = new ArrayList<>();
		listOfNames.add("Rocket Raccoon");
		listOfNames.add("Drax");
		listOfNames.add("Star Lord");
		listOfNames.add("Groot");
		listOfNames.add("Gamora");
		List<String> newListOfNames = halfTheList(listOfNames);
		System.out.println(newListOfNames);
		
		//Question 6: 
		Map<String, String> dictionary = new HashMap<String, String>();
		dictionary.put("alpha", "A");
		dictionary.put("anteater", "B");
		dictionary.put("omega", "C");
		dictionary.put("charlie", "D");
		dictionary.put("apple", "E");
		System.out.println(dictionary.size());
		System.out.println(dictionary);
		List<String> wordsWithA = returnValuesForKeysStartingWithA(dictionary);
		System.out.println(wordsWithA);
		
	}
	
	public static String repeatWord(String userWord, int numTimes) {
		return userWord.repeat(numTimes);
	}
	
	public static int power(int base, int exponent) {
		int result = 1;
		for(int index = 1; index <= exponent; index++) {
			result = result * base;
		}
		
		return result;
	}
	
	public static void quizQuestion3() {
		List<String> listOfNames = new ArrayList<>();
		listOfNames.add("Richard");
		listOfNames.add("Garyn");
		listOfNames.add("Helene");
		
		System.out.println(listOfNames);
		System.out.println(listOfNames.get(1));
	}
	
	public static List<String> halfTheList(List<String> listOfNames) {
		List<String> results = new ArrayList<String>();
		
		for(int index = 0; index < listOfNames.size(); index++) {
			if(!(index % 2 == 0)) {
				results.add(listOfNames.get(index));
			}
		}
		
		return results;
	}

	public static List<String> returnValuesForKeysStartingWithA(Map<String, String> dictionary) {
		List<String> results = new ArrayList<>();
		
		Set<String> keySet = dictionary.keySet();

		for(String key : keySet) { 
			if(key.toLowerCase().charAt(0) == 'a') {
				results.add(dictionary.get(key));
			}
		}
		
		return results;
	}
}
