package Week06.FinalProject_test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Week06.FinalProject.Card;

public class CardTest {

	@DisplayName("Happy Path")
	@Test
	void testCardHappyPath() {
	    Card happy2Card = new Card(2, "hearts");
	    Assertions.assertEquals("2 of hearts", happy2Card.describeToString());
	    
	    Card happyAceCard = new Card(14, "spades");
	    Assertions.assertEquals("Ace of spades", happyAceCard.describeToString());
	}
	
	@DisplayName("Negative Path")
	@Test
	void testCardNegativePath() {
	    Card happy2Card = new Card(15, "hearts");
	    Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
	    	happy2Card.describeToString();
	    });
	    
	    System.out.println(exception.getMessage());
	    
	    assertTrue(exception.getMessage().contains("out of bounds"));
	}

	@Test
	@Disabled("Not implemented yet")
	void testShowSomething() {
	}
}


