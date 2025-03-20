package projects;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import projects.entity.Project;
import projects.exception.DbException;
import projects.service.ProjectService;

public class ProjectsApp {
	private Scanner scanner = new Scanner(System.in);
	private ProjectService projectService = new ProjectService();

	private List<String> operations = List.of(
			"1) Create and populate all tables",
			"2) Add a project",
			"3) Add materials (TBD)",
			"4) Add steps (TBD)",
			"5) Add categories (TBD)",
			"6) Find a project",
			"7) Update a project (TBD)"
	);

	public static void main(String[] args) {
		System.out.println("Welcome to Projectopia!!");
		new ProjectsApp().processUserSelections();
	}

	/**
	 * Method will present the user with operations/actions, ask for user input, and then perform said action.
	 */
	private void processUserSelections() {
		// Using boolean here to continually prompt use for action until exit is triggered
		boolean done = false;

		while (!done) {
			int operation = getUserSelection();
			try {
				switch (operation) {
				case -1:
					done = exitMenu();
					break;
				case 1:
					createTables();
					break;
				case 2:
					addProject();
					break;
				case 3:
					addMaterials();
					break;
				case 4:
					addSteps();
					break;
				case 5:
					addCategories();
					break;
				case 6:
					findAProject();
					break;
				case 7: 
					updateAProject();
				default:
					System.out.println("\n" + operation + " is not valid. Try again");
					break;
				}
			} catch (Exception e) {
				System.out.println("\nError: " + e.toString() + " Try again");
			}
		}
	}

	private int getUserSelection() {
		printOperations();
		Integer op = getIntInput("\nEnter an operation number (press Enter to quit)");

		return Objects.isNull(op) ? -1 : op;
	}
	
	// OPERATIONS
	private void updateAProject() {
		System.out.println("Does nothing currently ... but soon");
	}

	private void findAProject() {
		// Allow the user to search for project by name
		String projectName = getStringInput("Enter the full or partial name of the project");
		
		Project dbResult = projectService.findAProject(projectName);
		if(dbResult.getProjectId() != null) {
			System.out.println("Project: \n" + dbResult);
		} else {
			System.out.println(String.format("No project found under '%s'", projectName));
		}
	}

	private void addCategories() {
		System.out.println("Does nothing currently ... but soon");
	}

	private void addSteps() {
		System.out.println("Does nothing currently ... but soon");
	}

	private void addMaterials() {
		System.out.println("Does nothing currently ... but soon");
	}

	private void addProject() {
		// Allow user to add a new project to the database
		
		// Get details from user
		String projectName = getStringInput("Enter the project name");
		BigDecimal estimatedHours = getBigDecimalInput("Enter the estimated hours");
		BigDecimal actualHours = getBigDecimalInput("Enter the actual hours");
		// Had to add some validation to the Difficulty into. Make sure value between 1 - 5
		boolean validDifficulty = false;
		Integer difficulty = 0;
		while(!validDifficulty) {
			difficulty = getIntInput("Enter the poject difficulty (1-5)");
			validDifficulty = isDifficultyValid(difficulty);
			if(!validDifficulty) {
				System.out.println("Invalid difficulty level, please use 1-5. Try again.");
			}
		}
		String notes = getStringInput("Enter the project notes");
		
		// Add details to project object
		Project project = new Project();		
		project.setProjectName(projectName);
		project.setEstimatedHours(estimatedHours);
		project.setActualHours(actualHours);
		project.setDifficulty(difficulty);
		project.setNotes(notes);
		
		// Save the project object to the database
		Project dbProject = projectService.addProject(project);
		System.out.println("You have successfully create project: \n" + dbProject);
	}

	private void createTables() {
		// Loading some values into database via a file
		projectService.createAndPopulateTables();
		System.out.println("\nTables created and populated.");
	}
	
	// USER INPUT
	private Integer getIntInput(String prompt) {
		// Validating user input - cause you can never trust the user
		String input = getStringInput(prompt);

		if (Objects.isNull(input)) {
			return null;
		}

		try {
			// Commented out my original code: 
			//return Integer.parseInt(input);
			
			// Reason : The API for Integer.valueOf(String) does indeed say that the String is interpreted exactly as if it were given to 
			//Integer.parseInt(String). However, valueOf(String) returns a new Integer() object whereas parseInt(String) returns
			//a primitive int.			
			
			return Integer.valueOf(input);
		} catch (Exception e) {
			throw new DbException(input + " is not a valid number.");
		}
	}

	private BigDecimal getBigDecimalInput(String prompt) {
		String input = getStringInput(prompt);

		if (Objects.isNull(input)) {
			return null;
		}

		try {
			// Commented out my original code:
			//double placeHolder = Double.parseDouble(input);
			//return BigDecimal.valueOf(placeHolder);
			
			// Reason: BigDecimal.valueOf(double) will use the canonical String representation of the double value passed in to 
			//instantiate the BigDecimal object. If you use new BigDecimal(d) however, then the BigDecimal will try to represent 
			//the double value as accurately as possible - more digits
			
			return new BigDecimal(input).setScale(2);
		} catch (Exception e) {
			throw new DbException(input + " is not a valid decimal number.");
		}
	}

	private String getStringInput(String prompt) {
		System.out.print(prompt + ": ");
		String line = scanner.nextLine();

		return line.isBlank() ? null : line.trim();
	}

	private boolean isDifficultyValid(Integer difficulty) {
		if(difficulty < 1 || difficulty > 5)
			return false;
		
		return true;
	}
	
	// MENU DISPLAY
	private void printOperations() {
		System.out.println();
		System.out.println("***********************************");
		System.out.println("***********************************");
		System.out.println("Select from the following options:");
		
		// Commented out my original code:
		//for(String operation : operations) {
		//	System.out.println(operation);
		//}
		
		// Reason: Because ???
		
		operations.forEach(operation -> System.out.println("\t" + operation));
	}
	
	private boolean exitMenu() {
		System.out.println("\nExiting the menu. TTFN!");
		return true;
	}

}
