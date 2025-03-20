package recipes;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import recipes.entity.Recipe;
import recipes.exception.DbException;
import recipes.service.RecipeService;

public class Recipes {
	private Scanner sc = new Scanner(System.in);
	private RecipeService rs = new RecipeService();

	private List<String> operations = List.of(
			"1) Create and populate all tables",
			"2) Add a recipe"
	);

	public static void main(String[] args) {
		// TODO Auto-generated constructor stub
		// Connection conn = DbConnection.getConnection();

		new Recipes().displayMenu();
	}

	private void displayMenu() {
		// TODO Auto-generated method stub
		boolean done = false;

		while (!done) {
			int operation = getOperation();
			try {
				switch (operation) {
				case -1:
					done = exitMenu();
					break;
				case 1:
					createTables();
					break;
				case 2:
					addRecipe();
					break;
				default:
					System.out.println("\n" + operation + " is not valid. Try again");
					break;
				}
			} catch (Exception e) {
				System.out.println("\nError: " + e.toString() + " Try again");
			}
		}
	}

	private void addRecipe() {
		// TODO Auto-generated method stub
		String name = getStringInput("Enter the recipe name");
		String notes = getStringInput("Enter the recipe notes");
		Integer numOfServings = getIntInput("Enter the number of servings");
		Integer	prepMinutes = getIntInput("Enter prep time in minutes");
		Integer	cookMinutes = getIntInput("Enter cook time in minutes");
		
		LocalTime prepTime = minutesToLocalTime(prepMinutes);
		LocalTime cookTime = minutesToLocalTime(cookMinutes);
		
		Recipe recipe = new Recipe();
		
		recipe.setRecipeName(name);
		recipe.setNotes(notes);
		recipe.setNumOfServings(numOfServings);
		recipe.setPrepTime(prepTime);
		recipe.setCookTime(cookTime);
		
		Recipe dbRecipe = rs.addRecipe(recipe);
		System.out.println("YOu added this recipe: \n" + dbRecipe);
	}

	private LocalTime minutesToLocalTime(Integer numMinutes) {
		// TODO Auto-generated method stub
		int min = Objects.isNull(numMinutes) ? 0 : numMinutes;
		int hours = min / 60;
		int minutes = min % 60;
		
		return LocalTime.of(hours, minutes);
	}

	private void createTables() {
		// TODO Auto-generated method stub
		rs.createAndPopulateTables();
		System.out.println("\nTables created and populated.");
	}

	private boolean exitMenu() {
		// TODO Auto-generated method stub
		System.out.println("\nExiting the menu. TTFN!");
		return true;
	}

	private int getOperation() {
		printOperations();
		Integer op = getIntInput("\nEnter an operation number (press Enter to quit)");

		return Objects.isNull(op) ? -1 : op;
	}

	private Integer getIntInput(String prompt) {
		// TODO Auto-generated method stub
		String input = getStringInput(prompt);

		if (Objects.isNull(input)) {
			return null;
		}

		try {
			return Integer.parseInt(input);
		} catch (Exception e) {
			throw new DbException(input + " is not a valid number.");
		}
	}

	private Double getDoubleInput(String prompt) {
		// TODO Auto-generated method stub
		String input = getStringInput(prompt);

		if (Objects.isNull(input)) {
			return null;
		}

		try {
			return Double.parseDouble(input);
		} catch (Exception e) {
			throw new DbException(input + " is not a valid number.");
		}
	}

	private String getStringInput(String prompt) {
		// TODO Auto-generated method stub
		System.out.print(prompt + ": ");
		String line = sc.nextLine();

		return line.isBlank() ? null : line.trim();
	}

	private void printOperations() {
		// TODO Auto-generated method stub
		System.out.println();
		System.out.println("Here is what you can do:");
//		for(String operation : operations) {
//			System.out.println(operation);
//		}
		operations.forEach(operation -> System.out.println("\t" + operation));
	}

}
