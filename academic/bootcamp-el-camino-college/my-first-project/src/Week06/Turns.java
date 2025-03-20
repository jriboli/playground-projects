package Week06;

import java.util.ArrayList;
import java.util.List;

public class Turns {
	private int turn = 0;
	private List<String> players = new ArrayList<>();
	
	public Turns(String playerOne, String playerTwo) {
		players.add(playerOne);
		players.add(playerTwo);
		turn = 0;
	}
	
	public int next() {
		String currentPlayer = players.get(turn % 2);
		System.out.println(currentPlayer + " it is your turn.");
		
		turn ++;
		return players.indexOf(currentPlayer);	
	}
	
	public String getPlayer(int indexOfPlayer) {
		return players.get(indexOfPlayer);
	}
	
	public int getTurn() {
		return turn;
	}

}
