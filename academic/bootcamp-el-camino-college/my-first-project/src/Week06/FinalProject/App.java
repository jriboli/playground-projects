package Week06.FinalProject;

import java.util.Scanner;

public class App {
	public static void main(String[] args) {
		System.out.println("Hello, I am Joshua");
		System.out.println("Shall we play a game?");
		
		int choice = 0; 
		while(choice != 3) {
			try {
				// Had to put scanner inside while do to funkiness when exception thrown on user input
				Scanner scanner = new Scanner(System.in);
				System.out.println("-------------------------------------");
				System.out.println("-------------------------------------");
				System.out.println("1) Card game War - Simplified");
				System.out.println("2) Card game War - Advanced");
				System.out.println("3) Exit");
				System.out.println("Make your choice:");
				choice = scanner.nextInt();
				
				CardGame warTheGame; 
				if (choice == 1) {
					startGame(new warGameSimplified());
				} else if (choice == 2) {
					startGame(new warGameAdvanced());
				} else if (choice == 3) {
					System.out.println("Good Bye");
				} else {
					System.out.println("Select a valid options - 1 thru 3");
				} 		
			} catch (Exception ex) {
				System.out.println("You entered in something funky, stop it... valid options - 1 thru 3");
				choice = 0;
			}
		}
	}
	
	public static void startGame(CardGame game) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your name:");
		String playerName = scanner.next();
		game.deal(playerName);
		game.play();
		game.displayWinner();
	}
}
