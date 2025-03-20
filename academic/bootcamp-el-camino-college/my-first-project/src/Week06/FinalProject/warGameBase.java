package Week06.FinalProject;

import java.util.ArrayList;
import java.util.List;

public class warGameBase {
	public Deck deck = new Deck();
	public Player player1;
	public Player player2;
	
	public void deal(String playerName) {
		List<Card> player1Hand = new ArrayList<>();
		List<Card> player2Hand = new ArrayList<>();
		
		for(int i = 0; i < 52; i++) {
			if(i % 2 == 0)
				player1Hand.add(deck.draw());
			else
				player2Hand.add(deck.draw());
		}
		
		player1 = new Player("Joshua", player1Hand);
		player2 = new Player(playerName, player2Hand);
		
		// looking at the hands - used to troubleshoot
		// player1.describe();
		// player2.describe();
	}
}
