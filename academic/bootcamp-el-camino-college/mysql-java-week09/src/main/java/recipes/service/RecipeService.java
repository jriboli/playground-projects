package recipes.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import recipes.dao.RecipeDao;
import recipes.entity.Recipe;
import recipes.exception.DbException;

public class RecipeService {
	private static final String SCHEMA_FILE = "recipe_schema.sql";
	private static final String DATA_FILE = "recipe_data.sql";

	private RecipeDao recipeDao = new RecipeDao();

	public void createAndPopulateTables() {
		loadFromFile(SCHEMA_FILE);
		loadFromFile(DATA_FILE);
	}

	private void loadFromFile(String fileName) {
		String content = readFileContent(fileName);
		List<String> sqlStatements = convertContentToSqlStatements(content);

		//sqlStatements.forEach(line -> System.out.println(line));
		recipeDao.executeBatch(sqlStatements);
	}

	private List<String> convertContentToSqlStatements(String content) {
		content = removeComments(content);
		content = replaceWhitespaceSqequencesWithSingleSpace(content);
		
		return extractLinesFromContent(content);
	}

	private List<String> extractLinesFromContent(String content) {
		// TODO Auto-generated method stub
		List<String> lines = new LinkedList<>();
		
		while(!content.isEmpty()) {
			int semicolon = content.indexOf(";");
			
			if(semicolon == -1) {
				if(!content.isBlank()) {
					lines.add(content);
				}
				
				content = "";
			}
			else {
				lines.add(content.substring(0, semicolon).trim());
				content = content.substring(semicolon + 1);
			}
		}
		
		return lines;
	}

	private String replaceWhitespaceSqequencesWithSingleSpace(String content) {
		// TODO Auto-generated method stub
		return content.replaceAll("\\s+", " ");
	}

	private String removeComments(String content) {
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder(content);
		int commentPos = 0;
		
		//int testHolder = builder.indexOf("-- ", commentPos); 
		//System.out.println(testHolder);
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
		// TODO Auto-generated method stub
		try {
			Path path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());
			return Files.readString(path);
		} catch (URISyntaxException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DbException(e);
		}
	}

	public Recipe addRecipe(Recipe recipe) {
		// TODO Auto-generated method stub
		return recipeDao.insertRecipe(recipe);
	}
}
