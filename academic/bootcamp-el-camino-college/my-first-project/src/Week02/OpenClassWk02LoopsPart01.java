package Week02;

import java.util.*;

public class OpenClassWk02LoopsPart01 {

	public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Base and Power");
        int base = in.nextInt();
        int power = in.nextInt();
        int result = 1;

/****** DO NOT CHANGE THE CODE ABOVE THIS LINE ******/
        for(int i = 1; i <= power; i++){
            result *= base;
            System.out.println(result);
        }
        
        System.out.println(result);
        System.out.println("----------------------------");

/****** DO NOT CHANGE THE CODE ABOVE THIS LINE ******/
        System.out.println("Count from number");
        int userNumber = in.nextInt();
        if(userNumber >= 1 && userNumber <= 100){
    	    while(userNumber <= 100) {
    	        System.out.println(userNumber);
    	        userNumber++;
    	    }
    	}
    	else
    	    System.out.println("Your number was not between 1 and 100.");
        
        System.out.println("----------------------------");
        
/****** DO NOT CHANGE THE CODE ABOVE THIS LINE ******/
        System.out.println("Do While Count from number");
        int userNumber2 = in.nextInt();
        if(userNumber2 > 10 && userNumber2 < 100){
            do{
                System.out.println(userNumber2);
                userNumber2--;
            }
            while(userNumber2 >= 10);
        }
        else
            System.out.println("Your number is not a value that is more than 10 and less than 100.");
    }

}
