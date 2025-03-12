package Week02;

import java.util.Scanner;

public class Week02_InClassChallenge {

	public static void main(String[] args) {
		boolean loggedIn = false;
		Scanner sc = new Scanner(System.in); 
		
		int attempts = 0;
		while (!loggedIn) {
			System.out.print("Enter username: ");   
			String username = sc.nextLine();   
			System.out.print("Enter password: ");  
			String password = sc.nextLine();   
			
			if (username.equals("samy123") && password.equals("12345")) {
				System.out.println("Welcome back " + username);
				loggedIn = true;
		    } else { 
		    	System.out.println("Inaccurate credentials!"); 
		    	attempts++;
		    	if(attempts > 2) {
		    		System.out.println("You are locked out!");
		    		break;
		    	}
			} // end of else 
		} // end of while

	}

}
