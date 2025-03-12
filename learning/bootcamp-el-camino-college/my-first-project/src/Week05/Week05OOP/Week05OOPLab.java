package Week05.Week05OOP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Week05OOPLab {

	// This Lab will give you a basic look at creating an Object Oriented Card Game.  
	//		The idea here is to prepare you for your Week 6 Unit Final Project.
	//		There are many ways to implement this Lab, please use the tools that you know,
	//		and go from there.
	// These exercises are intended to be coded in order 1 through 5.
		
	
	public static void main(String[] args) {
	
		
//		// A standard deck of playing cards has 52 cards as specified below 
//		//		i. There are 4 suits:  Clubs, Diamonds, Hearts, & Spades
//		//				
//		//	   ii. Each suit has 13 cards:  Two, Three, Four, Five, Six, Seven, 
//		//									 Eight, Nine, Ten, Jack, Queen, King & Ace
//		//
//		//	  iii. Comparing Cards:  When comparing two cards, Ace is high and Two is low.
//		//							 to make this easy, a Two will have a value of 2, a
//		//							 Three will have a value of 3, ... and an Ace will have
//		//							 a value of 14.
//		//
//	
//		
//		// 1. Card Class:
//		//		Create a class called Card to represent a standard playing card. 
//		//		Fields:   The Card class should have the following fields:
//		// 			a. name field
//		//			b. suit field
//		//			c. value field for comparing against other cards.
//		//
//		//		Methods:  This class can have any useful method.
//		//			a. describe() to display the card information to the Console.
//		//			b. Getters & Setters 
//		//
//		System.out.println("\nQuestion 1: Card Class");
//		// Add your code here to instantiate a Card
//		Card card = new Card("spades", "ace");
//		
//		
//		// Call the describe method on the newly instantiated card.
//		card.describe();
//		
//		
//		
//		
//
//		// 2. Deck Class:
//		//		Create a class called Deck.
//		//		Fields:  This class should have a list of Card field called cards 
//		//				 that will hold all the cards in the deck. 
//		//			List<Card> cards = new ArrayList<Card>(); 
//		//
//		//		Constructor: The constructor for the Deck Class should
//		// 			instantiate all 52 standard playing cards and add them to the cards list.
//		//
//		//		Methods:  
//		//			a.  describe() to describe the deck to the Console -- 
//		//					print out all of the cards in the deck.
//		//
//		System.out.println("\nQuestion 2: Deck Class");
//	    // Add your code here to instantiate a Deck	
//	    Deck deck = new Deck();
//	    
//	    // Call the describe method on the newly instantiated deck.
//	    deck.describe();
//	    
//	    
//	    
//		
//		
//		// 3. Deck shuffle() Method:
//		//		Add a shuffle method within the Deck Class
//		System.out.println("\nQuestion 3: Deck shuffle() method");
//		// Test your method here
//		deck.shuffle(7);
//		
//		
//		// Call the describe method on the newly shuffled deck.
//		deck.describe();
//		
//		
//		
//		// 4. Deck draw() Method:
//		//		Add a draw method within the Deck Class
//		System.out.println("\nQuestion 4: Deck draw() method");
//		// Test your method here
//		Card drawnCard = deck.draw();
//		
//		drawnCard.describe();
//		System.out.println("-----------------------------------------------------");
//		deck.describe();
//		
		
		
		
		
		
		
		// 5. Create Game Board:
		//		Create and test a method that takes an int as a parameter (representing the
		// 			number of players for a game) and returns a Map<String, List<Card>>
		// 			that represents each player (i.e. "Player 1", "Player 2", etc..) 
		//			and their cards.
		//
		// 			The method should create a new instance of Deck, shuffle it,
		// 			and deal the cards out to the "players" in the Map.
		System.out.println("\nQuestion 5: Create Game");
		// Call your method here
		int numberOfPlayers = 5;
		Map<String, List<Card>> theGame = deal(numberOfPlayers);
		
		for(int i = 1; i <= numberOfPlayers; i++) {
			List<Card> playerHand = theGame.get("Player " + i);
			
			System.out.println("-----------------------------------------------------");
			System.out.println("Player " + i + " Hand: ");
			System.out.println("Card count = " + playerHand.size());
			for(Card c : playerHand) {
				c.describe();
			}
		}		
		
	} 
	
	// Method 5:
	public static Map<String, List<Card>> deal(int numberOfPlayers) {
		Map<String, List<Card>> gameBoard = new HashMap<String, List<Card>>();
		//Map<String, Integer> testMap = new HashMap<String, String>();
		
		// Create a fresh Deck
		Deck deck = new Deck();
		deck.shuffle(7);
		
		// Find number of cards to deal
		int cardsRemainingAfterDeal = 52 % numberOfPlayers;
		int numberOfCardsPerPlayer = (52 - cardsRemainingAfterDeal)/numberOfPlayers;
		System.out.println("Number of Cards per Player - " + numberOfCardsPerPlayer);
		
		// Loop through creating the player hands
//		for(int i = 1; i <= numberOfPlayers; i++) {
//			// Create player hand
//			List<Card> playerHand = new ArrayList<>();
//			
//			// Loop through populating the player hand with X amount of cards
//			for(int j = 0; j < numberOfCardsPerPlayer; j++) {
//				playerHand.add(deck.draw());
//			}		
//			
//			// Add the hand to gameBoard
//			gameBoard.put("Player " + i, playerHand);
//		}
		
		// Loop through more realistically - because Pamela
		for(int i = 1; i <= numberOfPlayers; i++) {
			// Create player hand
			List<Card> playerHand = new ArrayList<>();		
			
			// Add the hand to gameBoard
			gameBoard.put("Player " + i, playerHand);
			//gameBoard.put("player " + 1, null);
		}
		
		for(int j = 52; j > 0; j--) {
			// 
			if(j > cardsRemainingAfterDeal) {
				int whoToDealTo = j % numberOfPlayers + 1;
				// playerName = "Player " + whoToDealTo;
				//System.out.println("Player Name : " + playerName);
				
				//List<Card> playerHand = gameBoard.get(playerName);
				//playerHand.add(deck.draw());
				
				//System.out.println(playerHand);
				gameBoard.get("Player " + whoToDealTo).add(deck.draw());
				
			} else {
				// Do something
				System.out.println("Skipped not enough cards -- " + j);
			}
		}
		
		double numberOfHandOutLoops = Math.floor(52/5);
		System.out.println("Here we go --- " + numberOfHandOutLoops);
		
//		for (int k = 52; k > 0; k -= numberOfPlayers) {
//			
//			if(k - numberOfPlayers)
//		}
		
		
		return gameBoard;
	}

}
