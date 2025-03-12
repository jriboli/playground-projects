package Week05.Week05OOP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Deck {
	private final List<String> faceValues = new ArrayList<>(Arrays.asList("two", "three", "four", "five", "six", "seven",
			"eight", "nine", "ten", "jack", "queen", "king", "ace"));
	private final List<String> suiteValues = new ArrayList<>(Arrays.asList("hearts", "clubs", "diamonds", "spades"));
	private Random rand = new Random();
	
	List<Card> cards = new ArrayList<>();
	
	public Deck() {
		initializeDeck();
	}
	
	public void describe() {
		System.out.println("There are " + cards.size() + " cards in this deck. Here they are: ");
		for(Card c : cards) {
			c.describe();
		}
	}
	
	public void shuffle(int shuffleXTimes) {
		while(shuffleXTimes > 0) {
			theShuffle();
			shuffleXTimes--;
		}
	}
	
	public Card draw() {
		int randInt = rand.nextInt(cards.size());
		Card randomCard = cards.get(randInt);
		cards.remove(randInt);
		
		return randomCard;
	}
	
	private void theShuffle() {
		List<Card> shuffledCards = new ArrayList<>();
		
		while(shuffledCards.size() < cards.size()) {
			int rand_int = rand.nextInt(cards.size());
			Card randCard = cards.get(rand_int);
			if(!checkIfExists(randCard, shuffledCards)) {
				shuffledCards.add(randCard);
			}
		}
		
		cards = shuffledCards;
	}
	
	private boolean checkIfExists(Card c, List<Card> cards) {
		for(Card card : cards) {
			if(c.equals(card)) {
				return true;
			}
		}
		return false;
	}
	
	private void initializeDeck() {
		for(String suite : suiteValues) { 
			for(String face : faceValues) {
				cards.add(new Card(suite, face));
			}
		}
		
	}

}
