package Week06.FinalProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Card {
	
	private int value;
	private String suite;
	
	public Card(int value, String suite) {
		this.value = value;
		this.suite = suite;
	}
	
	public void describe() {
		System.out.println(decodeFaceValue() + " of " + suite);
	}
	
	public String describeToString() {
		return decodeFaceValue() + " of " + suite;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return suite;
	}
	
	private String decodeFaceValue() {
		List<String> faceCards = new ArrayList<>(Arrays.asList("Jack", "Queen", "King", "Ace"));
		
		if(value > 10) {
			return faceCards.get(value - 10 - 1); 
		}
		
		return String.valueOf(value);
	}
}
