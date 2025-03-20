package Week02;

import java.util.Scanner;

public class Week02Lab {

	public static void main(String[] args) {
		
		//
		// BOOLEANS and CONDITIONALS:
		//
		
		// 1. Variable Declaration:
		//		a. Create a variable named age and assign it a value of 14
		byte age = 14;		
		
		// 2. Print a Boolean Expression:
		//		a. Print the boolean expression age >= 16 to the console and note the results.
		//		a. Change the value of age to 18 and print again.
		System.out.println(age >= 16);
		
		// 3. Can you drive?
		//		a. Using a conditional, print one of the following:
		//				i. "You can drive" if age is greater than or equal to 16
		//				ii. "You cannot drive" otherwise
		//
		//		a. Change the value of age and rerun to see the result
		if(age >= 16)
			System.out.println("You can drive");
		else
			System.out.println("You cannot drive");
		
		// 4. Update Solution to Question 3 as follows:
		//		a. Add a new variable called hasLicense before the conditional.
		//		b. Change the boolean expression in the conditional to additionally 
		//				include the need for hasLicense to be true.
		//		c. Try changing the values of age and hasLicense and note the different results.
		boolean hasLicense = true;
		if(hasLicense && age >= 16)
			System.out.println("You can drive");
		else
			System.out.println("You cannot drive");
		
		// 5. Milk?
		//		a. Create two new variables - costOfMilk and thirstLevel
		//		b. Create a new conditional that prints "Milk Please" if costOfMilk is less than 2.50 
		//				or if thirstLevel is greater than 6 and prints "No Thanks" otherwise.
		//		c. Change the values and note the different results.
		double costOfMilk = 4;
		byte thirstLevel = 5;
		if(costOfMilk < 2.50 || thirstLevel > 6)
			System.out.println("Milk Please");
		else
			System.out.println("No Thanks");	
		
		// 6. Cookie Distribution: 
		//			Note:  You will evenly distribute all of the cookies to the children 
		//					and as the adult you get to keep the remaining cookies for yourself.
		//
		//		a. Create two variables called numberOfCookies and numberOfChildren.
		//		b. Initialize the two variables to integer values.
		//		b. Use a conditional to print the following based on the following conditions:
		//				i. If there are 0 cookies remaining, print "Sad Face"
		//				ii. If there are less than 2 cookies, print "Yes!"
		//				iii. If there are less than 5 cookies, print "Whoohoooo!"
		//				iv. If there are 5 or more cookies, print "Jackpot!"
		int numberOfCookies = 20;
		int numberOfChildren = 3;
		
		int cookieRemainder = numberOfCookies % numberOfChildren;
		if(cookieRemainder == 0)
			System.out.println("Sad Face");
		else if(cookieRemainder < 2)
			System.out.println("Yes!");
		else if(cookieRemainder < 5)
			System.out.println("Whoohooo!");
		else
			System.out.println("Jackpot!");		
				
		// 7.  Loyalty Member Program:
		//		a. Create a variable called loyaltyMemberStatus and assign the value "SILVER"
		//		b. Create a variable called loyaltyMemberDiscount and assign the value 0.0
		//		c. Using a switch, set the value of loyaltyMemberDiscount based on 
		//				the following loyaltyMemberStatus scale: 
		//				i. "SILVER" is 0.10
		//				ii. "GOLD" is 0.15
		//				iii. "PLATINUM" is 0.25
		String loyaltyMemberStatus = "SILVER";
		double loyaltyMemberDiscount = 0.0;
		
		switch(loyaltyMemberStatus) {
		case "SILVER":
			loyaltyMemberDiscount = 0.10;
			break;
		case "GOLD":
			loyaltyMemberDiscount = 0.15;
			break;
		case "PLATINUM":
			loyaltyMemberDiscount = 0.25;
			break;
		}
		
		System.out.println(loyaltyMemberDiscount);
		
		// 8. Using the Loyalty Member Program variables from Question 7, do the following:
		//		a. Create a variable called billTotal and assign a value
		//		b. Create a variable called adjustedTotal and assign it the billTotal minus 
		//				the loyaltyMemberDiscount percent of the billTotal
		//		c. If the adjustedBillTotal is greater than $500 upgrade the 
		//				loyaltyMemberStatus from SILVER to GOLD or from GOLD to PLATINUM
		double billTotal = 100.00;
		double adjustedTotal = billTotal - (billTotal * loyaltyMemberDiscount);
		
		if(adjustedTotal > 500) {
			if(loyaltyMemberStatus == "SILVER")
				loyaltyMemberStatus = "GOLD";
			else
				loyaltyMemberStatus = "PLATINUM";
		}
		
		System.out.println(loyaltyMemberStatus);
		
		// 9. Login -- username & password:
		//		a. Create two variables, username and password
		//		b. Create a conditional that prints one of the following:
		//				i. "login successful" if the username is "Tommy123" and the password is "12345"
		//				ii. "access denied" otherwise
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter username: ");
		String username = sc.nextLine().trim();
		System.out.println("Enter password: ");
		String password = sc.nextLine().trim();
		
		if(username.equals("Tommy123") && password.equals("12345"))
			System.out.println("Login successful");
		else
			System.out.println("Access denied");
		
		//
		// LOOPS:
		//
		
		// 10. Write a for loop that prints each number from 0 to 9
		for(int i = 0; i > 10; i++)
			System.out.println(i);
		
		// 11. Write a for loop that prints each number from 10 to 0 backwards
		for(int i = 10; i >= 0; i--)
			System.out.println(i);		
		
		// 12. Write a for loop that prints every other number from 0 to 100
		for(int i = 0; i <= 100; i += 2)
			System.out.println(i);		
		
		// 13. Write a for loop that iterates from 0 to 100 and prints 
		//			"EVEN" if the number is even and "ODD" if it's odd
		for(int i = 0; i <= 100; i++) {
			if(i % 2 == 0)
				System.out.println("EVEN");
			else
				System.out.println("ODD");
		}
		
		// 14. Write a while loop that starts at 100 and iterates backwards by 1 until it reaches 0
		//			within the loop, divide each number by 3 and print the remainder to the console.
		int i = 100;
		while(i >= 0) {
			System.out.println(i % 3);
			i--;
		}		
	}

}
