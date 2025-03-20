package com.binaryNovice;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("My first Java Program!");
		
		//variable declaration - type, identifier, assignment operator, value, semicolon
		int age = 17; 
		double accountBalance = 34.67;
		char middleInitial = 'M'; // single quotes 
		boolean isHotOutside = true;
		String firstName = "Justin"; // double quotes
		System.out.println(age); //sysout - crtl + spacebar
		
		// operator performs actions on operands
		double savingsAccountBalance = 29.00;
		double depositAmount = 100.00;
		double withdrawalAmount = 50.00;
		double newSavingsAccountBalance = 0.00;
		
		System.out.println("The balance in your account is: $" + savingsAccountBalance);
		
		newSavingsAccountBalance = savingsAccountBalance + depositAmount;
		System.out.println("You have deposited $" + depositAmount); 
		System.out.println("Your new balance is $" + newSavingsAccountBalance);
		
		newSavingsAccountBalance = newSavingsAccountBalance - withdrawalAmount;
		System.out.println("Here is your withdrawal of $" + withdrawalAmount);
		System.out.println(String.format("Your new balance is %.2f", newSavingsAccountBalance));
	}

}
