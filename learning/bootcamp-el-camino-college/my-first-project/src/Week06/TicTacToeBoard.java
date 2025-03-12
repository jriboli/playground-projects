package Week06;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicTacToeBoard {
	private final int ROWS = 3;
	private final int COLUMNS = 3;
	
	private String displaySplitter = "+";
	private Map<Integer, String> board = new HashMap<>();
	
	public void initiatize() {
		// setup board
		for(int i = 0; i < 9; i++) {
			board.put(i, " ");
		}
		
		// setup display splitter
		for(int i = 0; i < COLUMNS; i++) {
			displaySplitter += "---+";
		}
	}
	
	public void display() {
		List<String> lines2display = new ArrayList<>();
		lines2display.add(displaySplitter); // top
		for(int i = 0; i < board.size(); i += 3) {
			lines2display.add(String.format("| %s | %s | %s |", board.get(i), board.get(i + 1), board.get(i + 2)));
			lines2display.add(displaySplitter); // breaker
		}
		
		for(String line : lines2display) {
			System.out.println(line);
		}
	}
	
	public boolean checkWinStatus() {
		if(isDiagonalWin() || isVerticalWin() || isHorizontalWin()) {
			return true;
		}
		
		return false;
	}
	
	public boolean placePiece(int location, int player) {
		int boardLocation = location - 1;
		if (location < 1 || location > 9) {
			throw new InvalidParameterException("Location must be between 1 - 9. Try again: ");
		} else if(!isLocationFree(boardLocation)) {
			throw new InvalidParameterException("Please choose a position that is not already taken. Try again: ");
		}
		
		String currentPiece = player % 2 == 0 ? "X" : "O";
		board.put(boardLocation, currentPiece);
		return true;		
	}
	
	private boolean isLocationFree(int boardLocation) {
		if(board.get(boardLocation) == " ") {
			return true;
		}
		
		return false;
	}
	
	private boolean isDiagonalWin() {
		if(board.get(4) != " ") {
			if((board.get(4) == board.get(0) && board.get(4) == board.get(8)) 
				|| (board.get(4) == board.get(2) && board.get(4) == board.get(6))) {
				System.out.println("Diagonal Win");
				return true;
			}
		}		
		
		return false;
	}
	
	private boolean isVerticalWin() {
		for(int i = 0; i < COLUMNS; i ++) {
			if(board.get(i) != " " && 
				(board.get(i) == board.get(i + ROWS) && board.get(i) == board.get(i + (ROWS * 2)))) {
				System.out.println("Vertical Win");
				return true;
			}
		}
		
		return false;
	}
	
	private boolean isHorizontalWin() {
		for(int i = 0; i < board.size(); i += COLUMNS) {
			if(board.get(i) != " " && 
				(board.get(i) == board.get(i + 1) && board.get(i) == board.get(i + 2))) {
				System.out.println("Horizontal Win");
				return true;
			}
		}
		
		return false;
	}
	

}
