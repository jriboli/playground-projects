package Week01;

public class Week01VariablesAndOperationsLab {

	public static void main(String[] args) {

		// 1. Create a variable to hold the quantity of 
		//		available plane seats left on a flight
		int availableSeats = 27;

		
		// 2. Create a variable to hold the cost of groceries at checkout
		double groceryTotal = 223.15;

		
		// 3. Create a variable to hold a person's middle initial
		char middleInitial = 'M';

	
		// 4. Create a variable to hold true if it's hot outside 
		//		and false if it's cold outside
		boolean isHotOutside = true;

		
		// 5. Create a variable to hold a customer's first name
		String customerFirstName = "Rocket";

		
		// 6. Create a variable to hold a street address
		String  streetAddress = "123 Main St."; 
		

		// 7. Print all variables to the console
		//System.out.println(String.format("There are %d available seats on flight A180", availableSeats));
		System.out.println(String.format("Your checkout total is %.2f", groceryTotal));
		System.out.println(String.format("Your customer's middle initial is %s", middleInitial));
		System.out.println(String.format("Is it hot outside? - %s", isHotOutside));
		System.out.println(String.format("We have %s on the phone", customerFirstName));
		System.out.println(String.format("They are looking for directions to %s", streetAddress));
	

		// 8. A customer booked 2 plane seats, 
		//		remove 2 seats from the available seats variable
		//availableSeats = availableSeats - 2;
	

		// 9. Impulse candy bar purchase, add 2.15 to the grocery total
		// 		costOfGroceries = costOfGroceries + 2.15;
		groceryTotal += 2.15;


		// 10.  The birth certificate was printed incorrectly, 
		//		change the middle initial to something else
		middleInitial = 'R';
		

		// 11.  The season has changed, update the hot outside 
		//			variable to be opposite of what it was
		isHotOutside = false;


		// 12.  Create a new variable called full name using the customer's first name, 
		//			the middle initial, and a last name of your choice
		String fullName = String.format("%S %s Raccoon", customerFirstName, middleInitial);
		// Interesting note - String.format %S with capital S uppercased the variable
	

		// 13.  Print a line to the console that introduces the customer and says they live
		// 			at the address variable
		System.out.println(String.format("Customer %s currently resides at address - %s", fullName, streetAddress));
		
		// Can use underscore characters in Numeric Literals - for readability
		long creditCardNumber = 4111_1111_1111_1111L;
		// Interesting note - Have to add L at the end
		System.out.println(creditCardNumber);

		
		
	}
}