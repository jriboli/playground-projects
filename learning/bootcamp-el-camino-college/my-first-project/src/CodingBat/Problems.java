package CodingBat;

public class Problems {
	//Given a non-negative number "num", return true if num is within 2 of a multiple of 10. Note: (a % b) 
	//is the remainder of dividing a by b, so (7 % 5) is 2. See also: Introduction to Mod
	public boolean nearTen(int num) {
		if(num % 10 <= 2 || num % 10 >= 8) {
			return true;
		}
		
		return false;
	}
	
	//Given 2 int values greater than 0, return whichever value is nearest to 21 without going over. Return 0 if they both go over.
	public int blackjack(int a, int b) {
		  if(a > 21 && b > 21) {
			  return 0;
		  }
		  else if (b > 21) {
			  return a;
		  }
		  else if (a > 21) {
			  return b;
		  }
		  else if (a > b) {
			  return a;
		  }
		  else {
			  return b;
		  }
	}
	
	//Given a string, return the sum of the digits 0-9 that appear in the string, ignoring all other characters. Return 0 if
	//there are no digits in the string. (Note: Character.isDigit(char) tests if a char is one of the chars '0', '1', .. '9'.
	//Integer.parseInt(string) converts a string to an int.)
	public int sumDigits(String str) {
		  int sum = 0;
		  for(char c : str.toCharArray()) {
			  if(c >= '0' && c <= '9') {
				 sum += Integer.valueOf(c + ""); 
			  }
		  }
		  
		  return sum;
	}
}
