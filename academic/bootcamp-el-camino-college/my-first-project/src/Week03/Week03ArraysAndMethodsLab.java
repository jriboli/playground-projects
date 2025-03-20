package Week03;

public class Week03ArraysAndMethodsLab {
	
	public static void main(String[] args) {
		
		//
		// Arrays:
		//
		
		// 1. Create an array with the following values 1, 5, 2, 8, 13, 6
		int[] numbers = new int[]{1,5,2,8,13,6};
		//OR
		int[] numbersTheLongWay = new int[6];
		numbersTheLongWay[0] = 1;
		numbersTheLongWay[1] = 5;
		numbersTheLongWay[2] = 2;
		numbersTheLongWay[3] = 8;
		numbersTheLongWay[4] = 13;
		numbersTheLongWay[5] = 6;

		
		// 2. Print out the first element in the array
		System.out.println(numbers[0]);
	
		// 3. Print out the last element in the array without using the number 5
		System.out.println(numbers[numbers.length -1]);
		
		// 4. Print out the element in the array at position 6, what happens?
		//System.out.println(numbers[6]);
		// Returns out of bounds error
		
		// 5. Print out the element in the array at position -1, what happens?
		//System.out.println(numbers[-1]);
		// Returns out of bounds error
			
		// 6. Write a traditional for loop that prints out each element in the array
		for(int i = 0; i < numbers.length; i++) {
			System.out.println(numbers[i]);
		}	
			
		// 7. Write an enhanced for loop that prints out each element in the array
		for(int number : numbers) {
			System.out.println(number);
		}
		
		// 8. Create a new variable called sum, write a loop that adds 
		//			each element in the array to the sum
		int sum = 0;
		for(int number : numbers) {
			sum += number;
		}
		System.out.println(sum);		
			
		// 9. Create a new variable called average and assign the average value of the array to it
		int average = 0;
		for(int number : numbers) {
			average += number;
		}
		System.out.println(average / numbers.length);

		
		// 10. Write an enhanced for loop that prints out each number in the array 
		//			only if the number is odd
		for(int number : numbers) {
			if(!(number % 2 == 0)) {
				System.out.println(number);
			}
		}
		
		// 11. Create an array that contains the values "Sam", "Sally", "Thomas", and "Robert"
		String[] names = new String[] {"Sam", "Sally", "Thomas", "Robert"};
		
		// 12. Calculate the sum of all the letters in the new array
		int sumOfLetters = 0;
		for(String name : names) {
			sumOfLetters += name.length();
		}
		System.out.println(sumOfLetters);
		

		//
		// Methods:
		//
		
		// 13. Write and test a method that takes a String name and prints out a greeting. 
		//			This method returns nothing.
		sayGreeting("Rocket");
		
		
		// 14. Write and test a method that takes a String name and  
		//			returns a greeting.  Do not print in the method.
		String greeting = getGreeting("Rocket");

		
		// Compare method 13 and method 14:  
		//		a. Analyze the difference between these two methods.
		//		b. What do you find? 
		//		c. How are they different?
		// The return types are difference 
		
		
		// 15. Write and test a method that takes a String and an int and 
		//			returns true if the number of letters in the string is greater than the int
		System.out.println("Check string length: " + isStringLongerThanNumber("suspicious", 5));
		
		// 16. Write and test a method that takes an array of string and a string and 
		//			returns true if the string passed in exists in the array
		String[] words = new String[] {"Super", "Chaos", "Calamity"};
		System.out.println("Check if CHAOS exists: " + checkIfExists(words, "Chaos"));
		System.out.println("Check if RACCOON exists: " + checkIfExists(words, "Raccoon"));
		
		// 17. Write and test a method that takes an array of int and 
		//			returns the smallest number in the array
		int[] numbersForSmallest = new int[] {43,6,99,34,56,1};
		System.out.println("Find the smallest: " + findSmallestNumber(numbersForSmallest));
	
		
		// 18. Write and test a method that takes an array of double and 
		//			returns the average
		double[] doubleNumbers = new double[] {45.0, 100, 89.7, 23.4};
		System.out.println("Double Average: " + calculateAverage(doubleNumbers));

		// 19. Write and test a method that takes an array of Strings and 
		//			returns an array of int where each element
		//			matches the length of the string at that position
		int[] wordLengths = calculateStringLengths(words);
		for(int size : wordLengths) {
			System.out.println("Word length: " + size);
		}
				
		// 20. Write and test a method that takes an array of strings and 
		//			returns true if the sum of letters for all strings with an even amount of letters
		//			is greater than the sum of those with an odd amount of letters.
		System.out.println("Even or Odds: " + hasMoreEvenWordCharacters(words));
	
		// 21. Write and test a method that takes a string and 
		//			returns true if the string is a palindrome
		System.out.println("Is Hannah a Palidrome: " + isPalidrome("Hannah"));
		System.out.println("Is racecar a Palidrome: " + isPalidrome("racecar"));
		System.out.println("Is Rocket Raccoon a Palidrome: " + isPalidrome("Rocket Raccoon"));	
		
	}
	

	
	// Method 13:
	public static void sayGreeting(String name) {
		System.out.println("Hello " + name + ", good morning.");
	}

	// Method 14:
	public static String getGreeting(String name) {
		return "Hello " + name + ", good morning.";
	}

	
	// Method 15:
	public static boolean isStringLongerThanNumber(String word, int compareLength) {
		return (word.length() > compareLength);
	}

	
	// Method 16:
	public static boolean checkIfExists(String[] words, String specificWord) {
		for(String word : words) {
			if(word.equals(specificWord))
				return true;
		}
		return false;
	}

	
	// Method 17:
	public static int findSmallestNumber(int[] numbers) {
		int smallest = numbers[0];
		for(int number : numbers) {
			if(number < smallest)
				smallest = number;
		}
		
		return smallest;
	}

	
	// Method 18:
	public static double calculateAverage(double[] numbers) {
		double average = 0;
		for(double number : numbers) {
			average += number;
		}
		
		return average / numbers.length;
	}

	
	// Method 19:
	public static int[] calculateStringLengths(String[] words) {
		int[] lengths = new int[words.length];
		for(int i = 0; i < words.length; i++) {
			lengths[i] = words[i].length();
		}
		
		return lengths;
	}

	
	// Method 20:
	public static boolean hasMoreEvenWordCharacters(String[] words) {
		int evenLengthWords = 0;
		int oddLengthWords = 0;
		for(String word : words) {
			if(word.length() % 2 == 0)
				evenLengthWords += 1;
			else
				oddLengthWords += 1;
		}
		
		if(evenLengthWords > oddLengthWords)
			return true;
		
		return false;
	}
	
	
	// Method 21:
	public static boolean isPalidrome(String word) {
		for(int i = 0; i < word.length(); i++) {
			if(word.charAt(i) != word.charAt(word.length() - (1 + i))) {
				return false;
			}
		}
		
		return true;
	}

}
