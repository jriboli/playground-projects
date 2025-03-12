package projects;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import projects.entity.Category;
import projects.entity.Material;
import projects.entity.Project;
import projects.entity.Step;
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
			"4) Select a project",
			"5) Delete a project"
	);
	
	private List<String> projectOperations = List.of(
			"1) Show Project Details",
			"2) Modify Project Info",
			"3) Edit Materials",
			"4) Edit Steps",
			"5) Edit Categories",
			"6) Exit Project"
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
			if(Objects.isNull(currProject)) {
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
					case 5: 
						deleteProject();
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
							modifyProjectDetails();
							break;
						case 3:
							currItem = "material";
							break;
						case 4:
							currItem = "step";
							break;
						case 5:
							currItem = "category";
							break;
						case 6:
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
			difficulty = getIntInput("Enter the project difficulty (1-5)");
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
	
	private void deleteProject() {
		listProjects();
		Integer selectedProjectId = getIntInput("Enter the project to delete");
		
		if(Objects.nonNull(selectedProjectId)) {
			projectService.deleteProject(selectedProjectId);
			
			if(Objects.nonNull(currProject) && currProject.getProjectId() == selectedProjectId) {
				currProject = null;
			}
		}
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
	
	private void modifyProjectDetails() {
		// TODO Auto-generated method stub
		// Get details from user
		String projectName = getStringInput(String.format("Enter the project name [%s]", currProject.getProjectName()));
		BigDecimal estimatedHours = getBigDecimalInput(String.format("Enter the estimated hours [%s]", currProject.getEstimatedHours()));
		BigDecimal actualHours = getBigDecimalInput(String.format("Enter the actual hours [%s]", currProject.getActualHours()));
		// Had to add some validation to the Difficulty into. Make sure value between 1 - 5
		boolean validDifficulty = false;
		Integer difficulty = 0;
		while(!validDifficulty) {
			difficulty = getIntInput(String.format("Enter the poject difficulty (1-5) [%s]", currProject.getDifficulty()));
			validDifficulty = isDifficultyValid(difficulty);
			if(!validDifficulty) {
				System.out.println("Invalid difficulty level, please use 1-5. Try again.");
			}
		}
		String notes = getStringInput(String.format("Enter the project notes [%s]", currProject.getNotes()));
		
		// Add details to project object
		Project project = new Project();		
		project.setProjectName(Objects.isNull(projectName) ? currProject.getProjectName() : projectName);
		project.setEstimatedHours(Objects.isNull(estimatedHours)? currProject.getEstimatedHours() : estimatedHours);
		project.setActualHours(Objects.isNull(actualHours) ? currProject.getActualHours(): actualHours);
		project.setDifficulty(Objects.isNull(difficulty) ? currProject.getDifficulty() : difficulty);
		project.setNotes(Objects.isNull(notes) ? currProject.getNotes() : notes);
		
		// Save the project object to the database
		projectService.updateProject(project);
		//System.out.println("You have successfully create project: \n" + dbProject);
		
		// Set current Project 
		currProject = projectService.fetchSingleProjectById(project.getProjectId());
	}
	
	// ITEM OPERATION METHODS 
	private void showItemDetails() {
		switch(currItem) {
		case "material":
			showMaterials();
			break;
		case "step":
			showSteps();
			break;
		case "category":
			showCategories();
			break;
		default:
			System.out.println("Invalid Item. Please use 'material', 'step' or 'category'");
		}
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
		
		// Needed to repopulate Project with updated items
		currProject = projectService.fetchSingleProjectById(currProject.getProjectId());
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
		
		// Needed to repopulate Project with updated items
		currProject = projectService.fetchSingleProjectById(currProject.getProjectId());
	}
	
	private void deleteCategories() {
		if(Objects.isNull(currProject)) {
			System.out.println("Error - please select a Project to work with");
		}
		
		currProject.getCategories().forEach(category -> System.out.println("    " + category));
		Integer selectedCategory = getIntInput("Select category to delete.");
		
		projectService.deleteCategory(selectedCategory, currProject.getProjectId());		
		
	}

	private void deleteSteps() {
		if(Objects.isNull(currProject)) {
			System.out.println("Error - please select a Project to work with");
		}
		
		currProject.getSteps().forEach(step -> System.out.println("    " + step));
		Integer selectedStep = getIntInput("Select step to delete.");
		
		projectService.deleteStep(selectedStep, currProject.getProjectId());
	}

	private void deleteMaterials() {
		if(Objects.isNull(currProject)) {
			System.out.println("Error - please select a Project to work with");
		}
		
		currProject.getMaterials().forEach(material -> System.out.println("    " + material));
		Integer selectedMaterial = getIntInput("Select material to delete.");
		
		projectService.deleteMaterial(selectedMaterial, currProject.getProjectId());		
	}

	private void addCategories() {
		if(Objects.isNull(currProject)) {
			System.out.println("Error - please select a Project to work with");
		}
		
		List<Category> categories = projectService.fetchAllCategories();
		categories.forEach(category -> System.out.println("    " + category));
		
		Integer selectedCategory = getIntInput("Select a category to add to Project");
		projectService.addCategory(selectedCategory, currProject.getProjectId());		
	}

	private void addSteps() {
		if(Objects.isNull(currProject)) {
			System.out.println("Error - please select a Project to work with");
		}
		
		Step step = new Step();
		
		String stepText = getStringInput("Enter step text: ");
		Integer stepOrder = getIntInput("Enter step order: ");
		
		step.setProjectId(currProject.getProjectId());
		step.setStepText(stepText);
		step.setStepOrder(stepOrder);
		
		projectService.addStep(step, currProject.getProjectId());	
	}

	private void addMaterials() {
		if(Objects.isNull(currProject)) {
			System.out.println("Error - please select a Project to work with");
		}
		
		Material material = new Material();
		
		String materialName = getStringInput("Enter material name: ");
		Integer numRequired = getIntInput("Enter material quantity: ");
		BigDecimal cost = getBigDecimalInput("Enter material cost: ");
		
		material.setProjectId(currProject.getProjectId());
		material.setMaterialName(materialName);
		material.setNumRequired(numRequired);
		material.setCost(cost);
		
		projectService.addMaterial(material, currProject.getProjectId());
	}
	
	private void showCategories() {
		System.out.println("Project Categories ----");
		currProject.getCategories().forEach(category -> System.out.println("   " + category));
		
	}

	private void showSteps() {
		System.out.println("Project Steps ----");
		currProject.getSteps().forEach(step -> System.out.println("   " + step));
		
	}

	private void showMaterials() {
		System.out.println("Project Materials ----");
		currProject.getMaterials().forEach(material -> System.out.println("   " + material));
		
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
