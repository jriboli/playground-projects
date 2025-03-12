package Week06.FinalProject;

public class warGameSimplified extends warGameBase implements CardGame  {
	
	@Override
	public void play() {
		for(int i = 0; i < 26; i++) {
			System.out.println("-------------------------------------");
			System.out.println("Round " + i);
			System.out.println("-------------------------------------");
			round();
			displayScore();
		}
		
		System.out.println("Game has concluded...");
	}

	@Override
	public void round() {
		Card player1Flipped = player1.flip();
		Card player2Flipped = player2.flip();
		
		System.out.println(String.format("[%s]", player1.getName()));
		player1Flipped.describe();
		System.out.println(String.format("[%s]", player2.getName()));
		player2Flipped.describe();
		
		if(player1Flipped.getValue() == player2Flipped.getValue()) {
			System.out.println("Draw - no point given");
		} else if(player1Flipped.getValue() > player2Flipped.getValue()) {
			player1.incrementScore();
			System.out.println("Point awarded to " + player1.getName());
		} else {
			player2.incrementScore();
			System.out.println("Point awarded to " + player2.getName());
		}
	}

	@Override
	public void displayWinner() {
		displayScore();
		
		if(player1.getScore() == player2.getScore()) {
			System.out.println("Game was tied");
		} else if(player1.getScore() > player2.getScore()) {
			System.out.println(player1.getName() + " won!");
		} else {
			System.out.println(player2.getName() + " won!");
		}
	}

	@Override
	public void displayScore() {
		System.out.println(String.format("%s [%s] vs [%s] %s", player1.getName(), player1.getScore(), player2.getScore(), player2.getName()));
	}

}
