package Week05;

import java.util.ArrayList;
import java.util.List;

public class Team {

	private List<Player> players = new ArrayList<Player>();
	private String name;
	
	public Team(String name) { 
		setName(name);
	}
	
	public void describe() {
		System.out.println("Team: "+ name);
		for(Player p : players) {
			p.describe();
		}
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}
	
	public void removePlayer(String name) {
		for(Player p : players) {
			if(p.getName().equals(name)) { 
				players.remove(p);
			}
		}
	}
	
	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}

}
