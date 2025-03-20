package Week04;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Week04Research {
	
	public static void main(String[] args) {
		/*
		 * STRINGS (https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html)
		 * 
		 * 1. Select five (5) methods from the String Java Documentation and describe the following for each:
		 * 	a. What is the method signature for each method? 
		 * 	b. What does each method do?
		 * 	c. Why would this method be useful (how could you use it)? Give an example
		 * 
		 *Answer: 
		 *	charAt(int i) - Link: (https://www.geeksforgeeks.org/java-string-charat-method-example/)
		 *	For the .charAt() method. It take in an integer as argument, and return the char of a String at the position. This can be used to aid in looping
		 *	through a String by grabbing char at position, same i in a for loop. Example:
		 */
		String phrase = "The quick brown fox jumped over the lazy dog";
		for(int i = 0; i < phrase.length(); i++) {
			if(phrase.charAt(i) == ' ')
				System.out.println("Space found at position " + i);
		}
		System.out.println("-------------------------------------------------");
		/*
		 *Answer Continued:
		 *	indexOf(String s) | indexOf(String s, int i) - Link: (https://www.geeksforgeeks.org/java-string-indexof/):
		 *	For the .indexOf() method there are actually multiple signatures, so it looks like the method is overloaded. One option takes just a String as
		 *	an argument. While another takes both a String and integer. The purpose of this method is to find first time a string exists in another 
		 *	string. When adding the integer, you are tell it which position to start searching at. Example:
		 */
		System.out.println("The word 'brown' exist at position "+ phrase.indexOf("brown"));
		System.out.println("Starting after 'brown' where does the first 'o' exist - position "+ phrase.indexOf("o",phrase.indexOf("brown") + 5));
		System.out.println("-------------------------------------------------");
		/*
		 *Answer Continued: 
		 *	toLowerCase() - Link: (https://www.geeksforgeeks.org/java-string-tolowercase-with-examples/):
		 *	This method .toLowerCase(), while it does not take an arguments while return a new String value. It takes the currect String value and convert
		 *	all the individual characters to lowercase. One purpose for this method is to use validating user input to match. Since we can not guarantee
		 *	the users with upper/lower case. Example: 
		 */
		Scanner sc = new Scanner(System.in);
		System.out.println("What is your favorite icecream flavor (Chocolate, Vanilla, Strawberry):");
		String iceCreamFlavor = sc.next();
		switch(iceCreamFlavor.toLowerCase()) {
		case "chocolate":
			System.out.println("We can still be friends.");
			break;
		case "vanilla":
			System.out.println("You are a pysho.");
			break;
		case "strawberry":
			System.out.println("You made the right choice!");
			break;
		default:
			System.out.println("You had trouble coloring in the lines when you were younger, huh?");
		}
		System.out.println("-------------------------------------------------");
		/*
		 * Answer Continued:
		 * 	isEmpty() - Link: (https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html#isEmpty()):
		 * 	The method .isEmpty() again takes no arguments but will return a boolean true/false depending on if the Strings length is 0 or not. If 0 
		 * 	the method will return true, else false. A use for this is to check if a String is empty before performing an operation on it. 
		 * 	Example: 
		 */
		String emptyString = "";
		if(!emptyString.isEmpty())
			System.out.println("String is not empty.");
		System.out.println("-------------------------------------------------");
		/*
		 *Answer Continued:
		 *	format(String format, Object... args) - Link: (https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/String.html#format(java.lang.String,java.lang.Object...)):
		 *	With the .format() method, this is different from the others that were instance methods. This is a static method. It takes in x number of arguments
		 *	it first takes in a String, then n number of Objects that will format in the string. The purpose of this is to help generate clear String values. 
		 *	Either for reporting or logging or etc. Example:		 *
		 */
		int errorCode = 8004005;
		String userName = "Rocket";
		System.out.println(String.format("[Error - %s] %s - Username does not exist in our system.", errorCode, userName));
		
		
		
		/*
		 * ARRAYS (https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/reflect/Array.html)
		 * 
		 * 2. Select five (5) methods from the Array Java Documentation and describe the following for each: 
		 * 	a. What is the method signature for each method? 
		 * 	b. What does each method do?
		 * 	c. Why would this method be useful (how could you use it)? Give an example 
		 * 
		 *Answer: 
		 * 	getLength(Object array) - Link: (https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/reflect/Array.html#getLength(java.lang.Object)):
		 * 	With the getLength() method, it takes an array object as an argument and will return an integer value for the count of items in the array. A use for
		 * 	this would be to know the length of an array before operating on it. Without it you could run into "out of bounds" error. Example: 
		 */
		//List<Integer> numbers = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
		int[] numbers = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
		int numLength = Array.getLength(numbers);
		for(int i = 0; i < numLength; i++) {
			if(numbers[i] % 2 == 0)
				System.out.println(numbers[i]);
		}
		System.out.println("-------------------------------------------------");
		/*
		 *Answer Continued:
		 *	get(Object array, int index) - Link: (https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/reflect/Array.html#get(java.lang.Object,int)):
		 *	The .get() method takes two arguments, an array object and an integer for index. The method will return a object. The purpose of the method is to return 
		 *	the value of an element in an array at a specific position. Example:
		 */
		int numAtPos3 = Array.getInt(numbers, 2);
		System.out.println(numAtPos3);
		System.out.println("-------------------------------------------------");
		/*
		 *Answer Continued:
		 *	set(Object array, int index, Object value) - Link: (https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/reflect/Array.html#set(java.lang.Object,int,java.lang.Object)):
		 *	With the .set() method it takes in three arguments. The array we want to operate on, the int for index/position, and the new value. A reason
		 *	we may want to use this is to set the value in an array at a known position. Example:
		 */
		Array.set(numbers, 3, 22);
		for(int i = 0; i < numLength; i++) {
			System.out.println(numbers[i]);
		}
		System.out.println("-------------------------------------------------");
		
		
		
		
		
		
		/* 
		 * Collections
		 * Resource : Java tutorials on Interfaces (https://docs.oracle.com/javase/tutorial/collections/interfaces/index.html)
		 * 
		 * 3. What are the differences between Lists, Sets, and Maps in Java?
		 *Answer:
		 *	Lists is ordered group of items, while Sets can not guarantee order. Sets do not allow duplicates, while Lists can have duplicates, and Maps need
		 *	unique values for the key. Unlike Lists and Sets, Maps are a key/value pairing structure.   
		 * 
		 * 4. List at least two different implementations for each collection(List, Set, and Map). When would you use one of the implementations over the other?
		 */
		List<String> words = new LinkedList<String>();
		List<String> words2 = new ArrayList<String>(Arrays.asList("Rocket Raccoon", "Star Lard", "Drax", "Groot", "Gamora"));
		
		Set<Integer> numerics = new HashSet<Integer>();
		Set<Integer> numerics2 = new TreeSet<Integer>();
		
		Map<Integer, String> raceResults = new HashMap<Integer, String>();
		Map<String, String> dictionary = new TreeMap<String, String>();
		/*
		 * 	We would use a List if we need to keep insert order. We would use Set if we don't want duplicates. And we can use Map when we want to store 
		 * 	with a key/value pair. 
		 * 
		 * 5. Write Java code that does the following:
		 * 	a. Write a line of code that shows how you would instantiate an ArrayList of String
		 * 	b. Write a line of code that shows how you would instantiate a HashSet of StringBuilder.
		 * 	c. Write a line of code that shows how you would instantiate a HashMap of String, String.
		 */
		List<String> arrList = new ArrayList<String>();
		Set<StringBuilder> builders = new HashSet<StringBuilder>();
		Map<String, String> mapOfStrings = new HashMap<String, String>();
	}

}
