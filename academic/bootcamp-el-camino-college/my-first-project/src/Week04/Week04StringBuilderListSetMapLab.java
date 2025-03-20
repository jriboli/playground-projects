package Week04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Week04StringBuilderListSetMapLab {
	
	// 3. Write and test a method that takes a list of strings 
	//			and returns the shortest string
	public static void findShortestString(List<String> words) {
		String shortest = words.get(0);
		for(String word : words) {
			if(word.length() < shortest.length())
				shortest = word;
		}
		
		System.out.println(shortest);
	}
	
	// 4. Write and test a method that takes a list of strings 
	//			and returns the list with the first and last element switched
	public static void switchFirstAndLast(List<String> words) {
		String tempHolder = words.get(0);
		words.set(0, words.get(words.size() -1));
		words.set(words.size() -1, tempHolder);
		
		for(String word : words) {
			System.out.println(word);
		}
	}
	
	// 5. Write and test a method that takes a list of strings 
	//			and returns a string with all the list elements concatenated to each other,
	// 			separated by a comma
	public static void combineWords(List<String> words) {
		String concatenatedWord = "";
		for(String word : words) {
			concatenatedWord += word;
		}
		
		System.out.println(concatenatedWord);
	}
	
	// 6. Write and test a method that takes a list of strings and a string 
	//			and returns a new list with all strings from the original list
	// 			containing the second string parameter (i.e. like a search method)
	public static List<String> findWordsThatContain(List<String> words, String searchWord) {
		List<String> newWords = new ArrayList<String>();
		for(String word : words) {
			if (word.contains(searchWord))
				newWords.add(word);
		}
		
		return newWords;		
	}
	
	// 7. Write and test a method that takes a list of integers 
	//			and returns a List<List<Integer>> with the following conditions specified
	//			for the return value:
	//		a. The first List in the returned value contains any number from the input list 
	//			that is divisible by 2
	//		b. The second List contains values from the input list that are divisible by 3
	//		c. The third containing values divisible by 5, and 
	//		d. The fourth all numbers from the input List not divisible by 2, 3, or 5
	public static List<List<Integer>> sortingNumbers(List<Integer> numbers) {
		List<List<Integer>> sortedNumbers = new ArrayList<>();
		
		List<Integer> divisibleByTwo = new ArrayList<Integer>();
		List<Integer> divisibleByThree = new ArrayList<Integer>();
		List<Integer> divisibleByFive = new ArrayList<Integer>();
		List<Integer> notDivisible = new ArrayList<Integer>();
		for(Integer num : numbers) {
			if(num % 2 == 0)
				divisibleByTwo.add(num);
			if(num % 3 == 0)
				divisibleByThree.add(num);
			if(num % 5 == 0)
				divisibleByFive.add(num);
			if(num % 2 != 0 && num % 3 != 0 && num % 5 != 0)
				notDivisible.add(num);
		}
		
		sortedNumbers.add(divisibleByTwo);
		sortedNumbers.add(divisibleByThree);
		sortedNumbers.add(divisibleByFive);
		sortedNumbers.add(notDivisible);
		
		return sortedNumbers;
	}
	
	// 8. Write and test a method that takes a list of strings 
	//			and returns a list of integers that contains the length of each string
	public static List<Integer> calculateStringLengths(List<String> words) {
		List<Integer> stringSize = new ArrayList<Integer>();
		for(String word : words) {
			stringSize.add(word.length());
		}
		
		return stringSize;
	}
	
	// 10. Write and test a method that takes a set of strings and a character 
	//			and returns a set of strings consisting of all the strings in the
	// 			input set that start with the character parameter.
	public static Set<String> findAllStringsContaining(Set<String> words, char searchChar) {
		Set<String> containsChar = new HashSet<String>();
		
		for(String word : words) {
			if(word.contains(searchChar+""))
				containsChar.add(word);
		}
		return containsChar;
	}
	
	// 11. Write and test a method that takes a set of strings 
	//			and returns a list of the same strings
	public static List<String> toList(Set<String> words) {
		List<String> newList = new ArrayList<String>();		
		newList = Arrays.asList(words.toArray(new String[words.size()]));
		return newList;
	}
	
	// 12. Write and test a method that takes a set of integers 
	//			and returns a new set of integers containing only even numbers 
	//			from the original set
	public static Set<Integer> returnEvenNumbers(Set<Integer> numbers) {
		Set<Integer> evenNumbers = new HashSet<Integer>();
		
		for(Integer num : numbers) {
			if(num % 2 ==0)
				evenNumbers.add(num);
		}
		
		return evenNumbers;		
	}
	
	// 14. Write and test a method that takes a Map<String, String> and a string 
	// 			and returns the value for a key in the map that matches the
	// 			string parameter (i.e. like a language dictionary lookup)
	public static String translateBabyLingo(Map<String, String> lingo, String searchWord) {
		return lingo.getOrDefault(searchWord, "No translation found.");
	}
	
	// 15. Write and test a method that takes a List<String> 
	//			and returns a Map<Character, Integer> containing a count of 
	//			all the strings that start with a given character
	public static Map<Character, Integer> wordBeginsWith(List<String> words) {
		Map<Character, Integer> beginsWith = new HashMap<Character, Integer>();
		for(String word : words) {
			if(beginsWith.containsKey(word.charAt(0)))
				beginsWith.put(word.charAt(0), beginsWith.get(word.charAt(0)) + 1);
			else
				beginsWith.put(word.charAt(0), 1);
		}
		
		return beginsWith;
	}
	
	public static void main(String[] args) {

		// 1. Why would we use a StringBuilder instead of a String?
		// 		a. Instantiate a new StringBuilder
		//		b. Append the characters 0 through 9 to it separated by dashes
		// 				Note:  make sure no dash appears at the end of the StringBuilder
		StringBuilder sBuilder = new StringBuilder();
		for(int i = 0; i < 10; i++) {
			sBuilder.append(i);
			if(i < 9)
				sBuilder.append("-");
		}
		
		System.out.println(sBuilder);

		
		// 2. List of String:
		//		a. Create a list of Strings 
		//		b. Add 5 Strings to it, each with a different length
		List<String> words = new ArrayList<String>();
		words.add("one");
		words.add("three");
		words.add("five");
		words.add("eleven");
		words.add("twenty seven");
		
		// 3. ABOVE ^^
		findShortestString(words);
		System.out.println("-------------------------------------------------");
		
		// 4. ABOVE ^^
		switchFirstAndLast(words);
		System.out.println("-------------------------------------------------");
		
		// 5. ABOVE ^^
		combineWords(words);
		System.out.println("-------------------------------------------------");
		
		// 6. ABOVE ^^
		List<String> newWords = findWordsThatContain(words, "seven");
		for(String word : newWords) {
			System.out.println(word);
		}
		System.out.println("-------------------------------------------------");
		
		// 7. ABOVE ^^
		// https://stackoverflow.com/questions/21696784/how-to-declare-an-arraylist-with-values
		List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)); 
		List<List<Integer>> sorted = sortingNumbers(numbers);
		for(List<Integer> group : sorted) {
			System.out.println("// GROUP //");
			for(Integer num : group) {
				System.out.println(num);
			}
		}
		System.out.println("-------------------------------------------------");
		
		// 8. ABOVE ^^
		List<Integer> stringSizes = calculateStringLengths(words);
		for(Integer num : stringSizes) { 
			System.out.println(num);
		}
		System.out.println("-------------------------------------------------");
		
		// 9. Create a set of strings and add 5 values
		Set<String> dictionary = new HashSet<String>();
		dictionary.add("calamity");
		dictionary.add("chaos");
		dictionary.add("link");
		dictionary.add("zelda");
		System.out.println("-------------------------------------------------");

		// 10. ABOVE ^^
		Set<String> containsChar = findAllStringsContaining(dictionary, 'c');
		for(String word : containsChar) {
			System.out.println(word);
		}
		System.out.println("-------------------------------------------------");

		// 11. ABOVE ^^
		List<String> newDictionary = toList(dictionary);
		for(String word : newDictionary) {
			System.out.println(word);
		}
		System.out.println("-------------------------------------------------");

		// 12. ABOVE ^^
		Set<Integer> numSet = new HashSet<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)); 
		Set<Integer> evenNums = returnEvenNumbers(numSet);
		for(Integer num : evenNums) {
			System.out.println(num);
		}
		System.out.println("-------------------------------------------------");
		
		// 13. Create a map of string and string and add 3 items to it where the key of each
		// 			is a word and the value is the definition of the word
		Map<String, String> babyLingo = new HashMap<String, String>();
		babyLingo.put("googoo", "I am fascinated with what is in front of me.");
		babyLingo.put("gagaa", "Bring me another play toy!");
		babyLingo.put("waaaa", "I am annoyed for some reason.");
		System.out.println("-------------------------------------------------");
		
		// 14. ABOVE ^^
		System.out.println("Googoo => " + translateBabyLingo(babyLingo, "googoo"));
		System.out.println("Nanna => " + translateBabyLingo(babyLingo, "nanna"));
		System.out.println("-------------------------------------------------");
		
		// 15. ABOVE ^^
		Map<Character, Integer> wordsBeginWith =  wordBeginsWith(words);
		for(Map.Entry<Character, Integer> characters : wordsBeginWith.entrySet()) {
			System.out.println(characters.getKey() + " : " + characters.getValue());
		}
		System.out.println("-------------------------------------------------");
	}
}
