package Week05.Week05OOP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Card implements Comparable{
	private final List<String> faceValues = new ArrayList<>(Arrays.asList("two", "three", "four", "five", "six", "seven",
			"eight", "nine", "ten", "jack", "queen", "king", "ace"));
	private int value;
	private String suite;
	private String name;
	
	public Card (String suite, String name) {
		this.suite = suite;
		this.name = name;
		setValue(name);
	}
	
	public void describe() {
		System.out.println(String.format("%s of %s", name, suite));
	}
	
	public int getValue() {
		return value;
	}
	
	private void setValue(String face) {
		int index = faceValues.indexOf(face);	
		value = index + 2; // Handle Offset
	}

	@Override
	public int compareTo(Object o) {
	
		Card otherCard = (Card)o;
		if(value < otherCard.getValue()) {
			return -1;
		} else if (value == otherCard.getValue()) {
			return 0;
		} else {
			return 1;
		}
	}
	
	
	
	
}
