package Week05;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Week05_OOP_MenuApp {
	private static Scanner scanner = new Scanner(System.in);
	private static List<Team> teams = new ArrayList<Team>();
	
	public static void main(String[] args) {
		
		Player p1 = new Player("Rocket", "Full Back", "Running Fast");
		Player p2 = new Player("Drax", "Line Man", "Like a bolder");
		Player p3 = new Player("Gamora", "Nose Tackle", "Agile and Sneaky");
		
		Team team = new Team("The Gaurdians");
		
		team.addPlayer(p1);
		team.addPlayer(p2);
		team.addPlayer(p3);		
		team.describe();
		
		System.out.println("------------------------------------------------");
		
		team.removePlayer("Drax");
		team.describe();
		
		System.out.println("------------------------------------------------");
		
		int decision = 0;
		
		while(true) {
			
			printOptions();
			decision = scanner.nextInt();
			if( decision == 1 ) {
				teams.add(createNewTeam());				
			} else if( decision == 2 ) {
				addPlayerToTeam();
			} else if( decision == 3 ) {
				removePlayerFromTeam();
			} else if( decision == 4 ) {
				describeTeam();
			} else {
				System.out.println("Choose a valid option.");
			}
		}
	}
	
	private static void printOptions() {
		System.out.println("1) Create New Team");
		System.out.println("2) Add Player to Team");
		System.out.println("3) Remove Player from Team");
		System.out.println("4) View Team Info");
	}
	
	private static void printTeams() {
		System.out.println("Choose a Team: ");
		int teamNumber = 1;
		for(Team t : teams) {
			System.out.println(teamNumber + ") " + t.getName());
		}
	}
	
	private static Team chooseATeam() {
		printTeams();
		int choosenTeam = scanner.nextInt();
		return teams.get(choosenTeam -1);
	}
	
	private static Team createNewTeam() {
		System.out.println("Enter Team Name: ");
		String teamName = scanner.next();
		Team newTeam = new Team(teamName);
		
		return newTeam;
	}
	
	private static void addPlayerToTeam() {
		Team choosenTeam = chooseATeam();
		
		System.out.println(String.format("Adding player for the '%s'", choosenTeam.getName()));
		System.out.println("Enter Name: ");
		String pName = scanner.next();
		System.out.println("Enter Position: ");
		String pPosition = scanner.next();
		System.out.println("Enter Specialty: ");
		String pSpecialty = scanner.next();
		
		Player newPlayer = new Player(pName, pPosition, pSpecialty);
		choosenTeam.addPlayer(newPlayer);
	}
	
	private static void removePlayerFromTeam() {
		Team choosenTeam = chooseATeam();
		
		System.out.println(String.format("Choose a Player from Team - '%s': ", choosenTeam.getName()));
		choosenTeam.describe();
		System.out.println("Enter Players name: ");
		String choosenPlayer = scanner.next();
		
		choosenTeam.removePlayer(choosenPlayer);
	}
	
	private static void describeTeam() {
		Team choosenTeam = chooseATeam();
		choosenTeam.describe();
	}

}
