package Week06.FinalProject_test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Week06.FinalProject.Card;
import Week06.FinalProject.Deck;

public class DeckTest {
	private Deck deck;
	
	@BeforeAll
	public void createDeck() {
		deck = new Deck();
	}

	@DisplayName("Deck Initialization")
	@Test
	void testDeckInitialization() {
	    Assertions.assertEquals(52, deck.getDeckSize());
	}
	
	@DisplayName("Deck Draw")
	@Test
	void testDeckDraw() {
		int currentDeckSize = deck.getDeckSize();
		deck.draw();
		int newDeckSize = deck.getDeckSize();
	    Assertions.assertEquals(currentDeckSize - 1, newDeckSize);
	}
}
