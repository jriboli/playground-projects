package projects.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import projects.dao.ProjectsDao;
import projects.entity.Category;
import projects.entity.Material;
import projects.entity.Project;
import projects.entity.Step;
import projects.exception.DbException;


public class ProjectService {
	private static final String SCHEMA_FILE = "projects_schema.sql";
	private static final String DATA_FILE = "projects_data.sql";

	private ProjectsDao projectsDao = new ProjectsDao();

	// CONVERT FILE TO COMMANDS
	public void createAndPopulateTables() {
		loadFromFile(SCHEMA_FILE);
		loadFromFile(DATA_FILE);
	}

	private void loadFromFile(String fileName) {
		String content = readFileContent(fileName);
		List<String> sqlStatements = convertContentToSqlStatements(content);

		//sqlStatements.forEach(line -> System.out.println(line));
		projectsDao.executeBatch(sqlStatements);
	}

	private List<String> convertContentToSqlStatements(String content) {
		content = removeComments(content);
		content = replaceWhitespaceSqequencesWithSingleSpace(content);
		
		return extractLinesFromContent(content);
	}

	private List<String> extractLinesFromContent(String content) {
		List<String> lines = new LinkedList<>();
		
		while(!content.isEmpty()) {
			int semicolon = content.indexOf(";");
			
			if(semicolon == -1) {
				if(!content.isBlank()) {
					lines.add(content);
				}
				content = "";
			} else {
				lines.add(content.substring(0, semicolon).trim());
				content = content.substring(semicolon + 1);
			}
		}
		
		return lines;
	}

	private String replaceWhitespaceSqequencesWithSingleSpace(String content) {
		return content.replaceAll("\\s+", " ");
	}

	private String removeComments(String content) {
		StringBuilder builder = new StringBuilder(content);
		int commentPos = 0;
		
		while ((commentPos = builder.indexOf("-- ", commentPos)) != -1) {
			int eolPos = builder.indexOf("\n", commentPos + 1);
			
			if(eolPos == -1) {
				builder.replace(commentPos, builder.length(), "");
			}
			else {
				builder.replace(commentPos, eolPos + 1, "");
			}
		}
		return builder.toString();
	}

	private String readFileContent(String fileName) {
		try {
			Path path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());
			return Files.readString(path);
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
	}

	// OPERATIONS
	public Project addProject(Project project) {
		return projectsDao.insertProject(project);
	}

	public List<Project> fetchAllProjects() {
		return projectsDao.fetchAllProjects();
	}

	public Project fetchSingleProjectById(Integer projectId) {
		return projectsDao.fetchSingleProjectById(projectId).orElseThrow(
				() -> new NoSuchElementException("Project with ID=" + projectId + " does not exist."));
	}

	public void updateProject(Project project) {
		if(!projectsDao.updateProject(project)){
			throw new DbException("Project with ID=" + project.getProjectId() + " does not exist");// TODO Auto-generated method stub
		}
	}

	public void deleteProject(Integer selectedProjectId) {
		if(!projectsDao.deleteProject(selectedProjectId)) {
			throw new DbException("Project with ID=" + selectedProjectId + " does not exist");
		}
		
	}
	
	public List<Category> fetchAllCategories() {
		return projectsDao.fetchAllCategories(); 
	}
	
	public void addCategory(Integer categoryId, Integer projectId) {
		projectsDao.addCategoryToProject(categoryId, projectId);
	}
	
	public void addStep(Step step, Integer projectId) {
		projectsDao.addStepToProject(step, projectId);
	}
	
	public void addMaterial(Material material, Integer projectId) {
		projectsDao.addMaterialToProject(material, projectId);
	}
	
	public void deleteCategory(Integer categoryId, Integer projectId) {
		if(!projectsDao.deleteCategory(categoryId, projectId)) {
			throw new DbException("Category with ID=" + categoryId + " does not exist");
		}
	}
	
	public void deleteStep(Integer stepId, Integer projectId) {
		if(!projectsDao.deleteStep(stepId, projectId)) {
			throw new DbException("Step with ID=" + stepId + " does not exist");
		}
	}

	public void deleteMaterial(Integer materialId, Integer projectId) {
		if(!projectsDao.deleteMaterial(materialId, projectId)) {
			throw new DbException("Material with ID=" + materialId + " does not exist");
		}
	}
}