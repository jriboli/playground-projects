package Week03;

import java.util.Scanner;

public class Week03Lab_MethodsPart2 {
	
	static int sumOfDigits(int digits) {
	    
	    // WRITE YOUR CODE HERE
	    String number = Integer.toString(digits);
	    int result = 0;
        for(int i=0;i<number.length();i++) {
            result += Integer.parseInt(number.charAt(i)+"");
        }
        return result;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int digits = in.nextInt();
		int sumAll = sumOfDigits(digits);
		System.out.println("The sum of all digits for the value " + digits + " is " + sumAll + ".");
	}

}
