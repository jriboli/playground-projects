package Week03;

import java.util.Scanner;

public class Week03Lab_ArraysPart2 {
static void reverseArray(int a, int b, int c, int d, int e) {
		
		int[] arr = {a, b, c, d, e};

    /****** DO NOT CHANGE THE CODE ABOVE THIS LINE ******/
	
	// WRITE YOUR CODE HERE
	    int[] reverseArray = new int[arr.length];
	    for(int i = 0; i < arr.length; i++)
	        reverseArray[i] = arr[arr.length - i - 1];
	        
	    for(int number : arr)
	        System.out.println(number);
	        
	    for(int number : reverseArray)
	        System.out.println(number);
		
    /****** DO NOT CHANGE THE CODE BELOW THIS LINE ******/
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int a,b,c,d,e;
		a = in.nextInt();
		b = in.nextInt();
		c = in.nextInt();
		d = in.nextInt();
		e = in.nextInt();
		reverseArray(a, b, c, d, e);
	}

}
