package recipes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.List;

import provided.util.DaoBase;
import recipes.entity.Recipe;
import recipes.exception.DbException;

public class RecipeDao extends DaoBase {
	private static final String RECIPE_TABLE = "recipes";
	private static final String INGREDIENT_TABLE = "ingredient";
	private static final String UNIT_TABLE = "unit";
	private static final String STEP_TABLE = "step";
	private static final String CATEGORY_TABLE = "category";
	private static final String RECIPE_CATEGORY_TABLE = "recipe_category";

	public void executeBatch(List<String> sqlBatch) {
		try(Connection conn = DbConnection.getConnection()) {
			startTransaction(conn);
			
			try(Statement stmt = conn.createStatement()) {
				for(String sql : sqlBatch) {
					stmt.addBatch(sql);
				}
				
				stmt.executeBatch();
				commitTransaction(conn);
			} catch (Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
		} catch (SQLException e) {
			throw new DbException(e);
		}
	}

	public Recipe insertRecipe(Recipe recipe) {
		// TODO Auto-generated method stub
		String sql = ""
				+ "INSERT INTO " + RECIPE_TABLE + " "
				+ "(recipe_name, notes, num_servings, prep_time, cook_time) "
				+ "(?, ?, ?, ?, ?)";
		
		try(Connection conn = DbConnection.getConnection()) {
			startTransaction(conn);
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)) {
				setParameter(stmt, 1, recipe.getRecipeName(), String.class);
				setParameter(stmt, 2, recipe.getNotes(), String.class);
				setParameter(stmt, 3, recipe.getNumOfServings(), Integer.class);
				setParameter(stmt, 4, recipe.getPrepTime(), LocalTime.class);
				setParameter(stmt, 5, recipe.getCookTime(), LocalTime.class );
				
				stmt.executeUpdate();
				Integer recipeId = getLastInsertId(conn, RECIPE_TABLE);
				
				commitTransaction(conn);
				
				recipe.setRecipeId(recipeId);
				return recipe;
			} catch (Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
		} catch (SQLException e) {
			throw new DbException(e);
		}
	}

}
