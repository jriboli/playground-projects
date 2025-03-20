package Week06.FinalProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Deck {
	private final List<String> suites = new ArrayList<>(Arrays.asList("hearts", "clubs", "diamonds", "spades"));
	
	private Random rand = new Random();	
	private List<Card> cards = new ArrayList<>();
	
	public Deck() {
		// create deck - 52 cards
		initialize();
		shuffle(7);
	}
	
	public void shuffle(int shuffleXTimes) {
		while(shuffleXTimes > 0) {
			shuffle();
			shuffleXTimes--;
		}
	}
	
	private void shuffle() {
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
	
	public Card draw() {
		Card drawn = cards.get(0);
		cards.remove(0);
		
		return drawn;
	}
	
	public void describe() {
		System.out.println("There are " + cards.size() + " cards in this deck. Here they are: ");
		for(Card c : cards) {
			c.describe();
		}
	}
	
	public int getDeckSize() {
		return cards.size();
	}
	
	private boolean checkIfExists(Card c, List<Card> cards) {
		for(Card card : cards) {
			if(c.equals(card)) {
				return true;
			}
		}
		return false;
	}
	
	private void initialize() {
		for(String suite : suites) { 
			for(int i = 2; i <= 14; i++) {
				cards.add(new Card(i, suite));
			}
		}
		
	}
}
