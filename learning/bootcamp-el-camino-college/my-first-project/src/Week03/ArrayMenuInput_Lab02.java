package Week03;

import java.util.Scanner;

public class ArrayMenuInput_Lab02 {
	static String[] iceCreamList = new String[3];
	static int iceCreamCounter = 0;
	static Scanner input = new Scanner(System.in);
	private static final int maxScoops = 3;
	private static String[] options = {"Vanilla", "Chocolate", "Mint Chocolate Chip", "Strawberry", "Black Raspberry", "Chocolate Peanut Butter Cup", "Bubble Gum", "Banana Fudge","End of Ice Cream Selection"};
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// **********************************************************
		// Future enhancement -- Create a List of IceCreamOrders,
		// and then add the order to the list, and prompt the User
		// and ask whether their order is complete or not.
		// **********************************************************

		ArrayIceCreamOrder_Lab02 userOrder = new ArrayIceCreamOrder_Lab02();
		boolean finished = false;
		System.out.println("\n\n-------------------------\nThis Example Uses Arrays!\n-------------------------\n\n\n");
		System.out.println( "------------------------------\nWelcome to The Ice Cream Stand\n------------------------------\n");

		while (!finished) {
			System.out.print("Enter a name for your order: ");
			userOrder.setUser(input.nextLine());
			int counter = 0;
			boolean willOrderIceCream = true;
			int userInput = 0;
			System.out.println("\n\n" + userOrder.getUser() + ", you may choose up to 3 flavors of ice cream!\n\n");
			do {
				if (counter == 0) {
					printFlavorOptions();
				}
				if (counter >= 2) {
					System.out.print("Enter your selection: ");
					userInput = input.nextInt();
					switch (userInput) {
						case (1):
						case (2):
						case (3):
						case (4):
						case (5):
						case (6):
						case (7):
						case (8):
							printChoice(options[userInput - 1], counter);
							break;
						case (9):
							if (counter == 0) {
								System.out.println("No selection has been made! Do you want to order ice cream?");
								if (willOrderIceCream = checkOrderCancellation()) {
									userInput = 0;
									counter--;
									break;
								}
							}
							System.out.println("End of Ice Cream Selection!\n");
							break;
						default:
							System.out.println("Invalid Selection Choice");
							counter--;
					}
				}
				counter++;
			} while (userInput != 9 && counter <= 4); // ExitCriteria == 9
	
			/* Set field in userOrder */
			userOrder.setFlavors(iceCreamList);
			if (willOrderIceCream) {
				// Choose the Size of the Ice Cream: Small, Medium or Large
				int userInputSize = 0;
				int size = iceCreamCounter;
				if (size == maxScoops) {
					System.out.println("With 3 Ice Cream flavors, the size you get is: LARGE");
					userOrder.setSize("Large");
					userInputSize = size;
				} else {
					printSizeOptions();
					userInputSize = input.nextInt();
					while ((userInputSize != 1) && (userInputSize != 2) && (userInputSize != 3)) {
						System.out.print("Please enter a valid ice cream size selection: ");
						userInputSize = input.nextInt();
					}
					System.out.print("The size you have chosen is: ");
					switch (userInputSize) {
						case (1):
							System.out.println("SMALL");
							userOrder.setSize("Small");
							break;
						case (2):
							System.out.println("MEDIUM");
							userOrder.setSize("Medium");
							break;
						case (3):
							System.out.println("LARGE");
							userOrder.setSize("Large");
							break;
					}
				}
		
				System.out.println("\n");
				// Choose cup or cone
				int order = 0;
				printCupOrConeOptions();
				order = input.nextInt();
				while ((order != 1) && (order != 2)) {
					System.out.print("Please enter a valid entry: ");
					order = input.nextInt();
				}
				System.out.print("You have chosen a: ");
				switch (order) {
					case (1):
						System.out.println("CUP");
						userOrder.setCupOrCone("Cup");
						break;
					case (2):
						System.out.println("CONE");
						userOrder.setCupOrCone("Cone");
						break;
				}
				System.out.println("\n");
				userOrder.describe(iceCreamCounter);
				System.out.println("\n");
			} else {
				System.out.println("Order Cancelled!");
			}
			// **********************************************************
			// Future enhancement -- Add a menu here to ask the
			// user if they have another request to add to this order.
			// **********************************************************
			finished = true;
		} // end of while
		System.out.println("Goodbye -- End of Order!");

	}
	
	public static void printCupOrConeOptions() {
		System.out.println("Choose either a CUP or a CONE for your order: ");
		System.out.println("---------------------------------------------");
		System.out.println("\t1) CUP");
		System.out.println("\t2) CONE");
		System.out.println("---------------------------------------------");
		System.out.print("Enter your selection: ");
	}

	public static void printChoice(String flavor, int counter) {
		System.out.println("Ice Cream Choice #" + (counter + 1) + ": " + flavor);
		System.out.println("------------------------------------------------");
		iceCreamList[iceCreamCounter++] = flavor;
	}

	public static void printFlavorOptions() {
		System.out.println();
		System.out.println("Choose ice cream flavors:");
		System.out.println("-------------------------");
		for (int i = 0; i < options.length; i++) {
		System.out.println(" " + (i + 1) + ") " + options[i]);
		}
		System.out.println("------------------------------------------------");
	}

	public static boolean checkOrderCancellation() {
		int check = 0;
		System.out.println();
		System.out.println("Would you like Ice cream?");
		System.out.println("-------------------------");
		System.out.println("\t1) Yes, continue the order!");
		System.out.println("\t2) No, CANCEL my order");
		System.out.print("Enter your selection: ");
		check = input.nextInt();
		while ((check != 1) && (check != 2)) {
			System.out.print("Please enter a valid entry: ");
			check = input.nextInt();
		}
		System.out.print("You have chosen: ");
		if (check == 1) {
			System.out.println("CONTINUE Your Order");
			System.out.println();
			return true;
		} else {
			System.out.println("CANCEL Your Order");
			System.out.println();
			return false;
		}
	}

	public static void printSizeOptions() {
		System.out.println("Choose size for your ice cream: ");
		System.out.println("-------------------------------");
		System.out.println("\t1) SMALL -- 1 scoop");
		System.out.println("\t2) MEDIUM -- 2 scoops");
		System.out.println("\t3) LARGE -- 3 scoops");
		System.out.print("Enter your selection: ");
	}

}
