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
	private Project currProject;
	private String currItem = "";

	private List<String> operations = List.of(
			"1) Create and populate all tables",
			"2) Add a project",
			"3) List projects",
			"4) Select a project"
	);
	
	private List<String> projectOperations = List.of(
			"1) Show Project Details",
			"2) Edit Materials",
			"3) Edit Steps",
			"4) Edit Categories",
			"5) Exit Project"
	);
	
	private List<String> itemOperations = List.of(
			"1) Show all project",
			"2) Add a",
			"3) Delete a",
			"4) Exit"
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
			if(currProject == null) {
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
						listProjects();
						break;
					case 4:
						setCurrentProjectById();
						break;
					default:
						System.out.println("\n" + operation + " is not valid. Try again");
						break;
					}
				} catch (Exception e) {
					System.out.println("\nError: " + e.toString() + " Try again");
				}
			} 
			else {
				if(currItem == "") {
					int operation = getProjectSelection();
					try {
						switch (operation) {
						case 1:
							showProjectDetails();
							break;
						case 2:
							currItem = "material";
							break;
						case 3:
							currItem = "step";
							break;
						case 4:
							currItem = "category";
							break;
						case 5:
							// Go back to main menu
							currProject = null;
							break;
						default:
							System.out.println("\n" + operation + " is not valid. Try again");
							break;
						}
					} catch (Exception e) {
						System.out.println("\nError: " + e.toString() + " Try again");
					}
				}
				else {
					int operation = getItemSelection(currItem);
					try {
						switch (operation) {
						case 1:
							showItemDetails();
							break;
						case 2:
							addItem();
							break;
						case 3:
							deleteItem();
							break;
						case 4:
							// Go back to Project menu
							currItem = "";
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
		}
	}

	private int getUserSelection() {
		printOperations();
		Integer op = getIntInput("Enter an operation number (press Enter to quit)");

		return Objects.isNull(op) ? -1 : op;
	}
	
	private int getProjectSelection() {
		printProjectOperations();
		Integer op = getIntInput("Enter an operation number:");

		return Objects.isNull(op) ? -1 : op;
	}
	
	private int getItemSelection(String item) {
		printItemOperations(item);
		Integer op = getIntInput("Enter an operation number:");

		return Objects.isNull(op) ? -1 : op;
	}
	
	// MAIN OPERATION METHODS
	private void listProjects() {
		List<Project> projects = projectService.fetchAllProjects();
		
		System.out.println("\nProjects:");
		
		projects.forEach(project -> System.out.println(" " + project.getProjectId() + ": " + project.getProjectName()));
	}
	
	private void setCurrentProjectById() {
		listProjects();
		Integer projectId = getIntInput("Enter a project ID to select a project");
		
		currProject = null;
		
		
		currProject = projectService.fetchSingleProjectById(projectId);
		
		if(Objects.isNull(currProject)) {
			System.out.println("\nYou are not working with a project.");
		} else {
			System.out.println("\nYou are working with project: " + currProject);
		}
		
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
		
		// Set current Project 
		currProject = projectService.fetchSingleProjectById(dbProject.getProjectId());
	}
	
	private void createTables() {
		// Loading some values into database via a file
		projectService.createAndPopulateTables();
		System.out.println("\nTables created and populated.");
	}
	
	// PROJECT OPERATION METHODS
	private void showProjectDetails() {
		System.out.println("\nProject Details: " + currProject);
	}
	
	// ITEM OPERATION METHODS 
	private void showItemDetails() {
		System.out.println("Does nothing currently ... but soon");
	}
	
	private void addItem() {
		switch(currItem) {
		case "material":
			addMaterials();
			break;
		case "step":
			addSteps();
			break;
		case "category":
			addCategories();
			break;
		default:
			System.out.println("Invalid Item. Please use 'material', 'step' or 'category'");
		}
	}
	
	private void deleteItem() {
		switch(currItem) {
		case "material":
			deleteMaterials();
			break;
		case "step":
			deleteSteps();
			break;
		case "category":
			deleteCategories();
			break;
		default:
			System.out.println("Invalid Item. Please use 'material', 'step' or 'category'");
		}
	}
	
	private void deleteCategories() {
		System.out.println("Does nothing currently ... but soon");
	}

	private void deleteSteps() {
		System.out.println("Does nothing currently ... but soon");
	}

	private void deleteMaterials() {
		System.out.println("Does nothing currently ... but soon");		
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
	
	// USER INPUT METHODS
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
		String projectStr = "";
		if(currProject != null)
			projectStr = "[Current Project : " + currProject.getProjectId() + " : " + currProject.getProjectName() + "] ";
		System.out.print("\n" + projectStr + prompt + ": ");
		String line = scanner.nextLine();

		return line.isBlank() ? null : line.trim();
	}

	private boolean isDifficultyValid(Integer difficulty) {
		if(difficulty < 1 || difficulty > 5)
			return false;
		
		return true;
	}
	
	// MENU DISPLAY METHODS 
	private void printOperations() { 
		System.out.println();
		System.out.println("***********************************");
		System.out.println("***********************************");
		System.out.println("[MAIN MENU] Select from the following options:");
		
		// Commented out my original code:
		//for(String operation : operations) {
		//	System.out.println(operation);
		//}
		
		// Reason: Because ???
		
		operations.forEach(operation -> System.out.println("\t" + operation));
	}
	
	private void printProjectOperations() {
		System.out.println();
		System.out.println("***********************************");
		System.out.println("***********************************");
		System.out.println("[PROJECT MENU] Select from the following options:");
		
		projectOperations.forEach(operation -> System.out.println("\t" + operation));
	}
	
	private void printItemOperations(String item) {
		System.out.println();
		System.out.println("***********************************");
		System.out.println("***********************************");
		System.out.println("[" + item.toUpperCase() + " MENU] Select from the following options:");
		
		itemOperations.forEach(operation -> System.out.println("\t" + operation + " " + item));
	}

	private boolean exitMenu() {
		System.out.println("\nExiting the menu. TTFN!");
		return true;
	}

}
