package recipes.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import provided.util.DaoBase;
import recipes.entity.Category;
import recipes.entity.Ingredient;
import recipes.entity.Recipe;
import recipes.entity.Step;
import recipes.entity.Unit;
import recipes.exception.DbException;

public class RecipeDao extends DaoBase {
	private static final String RECIPE_TABLE = "recipes";
	private static final String INGREDIENT_TABLE = "ingredient";
	private static final String UNIT_TABLE = "unit";
	private static final String STEP_TABLE = "step";
	private static final String CATEGORY_TABLE = "category";
	private static final String RECIPE_CATEGORY_TABLE = "recipe_category";
	
	public Optional<Recipe> fetchRecipeById(Integer recipeId) {
		String sql = "SELECT * FROM " + RECIPE_TABLE + " WHERE recipe_id = ?";
		
		try(Connection conn = DbConnection.getConnection()) {
			startTransaction(conn);
			
			try {
				Recipe recipe = null;
				try(PreparedStatement stmt = conn.prepareStatement(sql)) {
					setParameter(stmt, 1, recipeId, Integer.class);
					
					try(ResultSet rs = stmt.executeQuery()) {
						if(rs.next()) {
							recipe = extract(rs, Recipe.class);
						}
					}
				}
				
				if(Objects.nonNull(recipe)) {
					recipe.getIngredients().addAll(fetchRecipeIngredients(conn, recipeId));
					recipe.getSteps().addAll(fetchRecipeSteps(conn, recipeId));
					recipe.getCategories().addAll(fetchRecipeCategories(conn, recipeId));
					
				}
				
				return Optional.ofNullable(recipe);
				
			} catch (Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
		} catch (SQLException e) {
			throw new DbException(e);
		}
	}

	private List<Category> fetchRecipeCategories(Connection conn, Integer recipeId) throws SQLException {
		String sql = "" 
				+ "SELECT c.* "
				+ "FROM " + RECIPE_CATEGORY_TABLE + " rc "
				+ "LEFT JOIN " + CATEGORY_TABLE + " c USING (category_id) "
				+ "WHERE recipe_id = ? "
				+ "ORDER BY c.category_name";
		
		try(PreparedStatement stmt = conn.prepareStatement(sql)) {
			setParameter(stmt, 1, recipeId, Integer.class);
			
			try(ResultSet rs = stmt.executeQuery()) {
				List<Category> categories = new LinkedList<>();
				
				while(rs.next()) {
					categories.add(extract(rs, Category.class));		
				}
				
				return categories;
			}
		}
	}

	private List<Step> fetchRecipeSteps(Connection conn, Integer recipeId) throws SQLException {
		String sql = "SELECT * FROM " + STEP_TABLE + " WHERE recipe_id = ?";
		
		try(PreparedStatement stmt = conn.prepareStatement(sql)) {
			setParameter(stmt, 1, recipeId, Integer.class);
			
			try(ResultSet rs = stmt.executeQuery()) {
				List<Step> steps = new LinkedList<>();
				
				while(rs.next()) {
					steps.add(extract(rs, Step.class));		
				}
				
				return steps;
			}
		}
	}

	private List<Ingredient> fetchRecipeIngredients(Connection conn, Integer recipeId) throws SQLException {
		String sql = "" 
				+ "SELECT i.*, u.unit_name_singular, u.unit_name_plural "
				+ "FROM " +INGREDIENT_TABLE + " i "
				+ "LEFT JOIN " + UNIT_TABLE + "u USING (unit_id) "
				+ "WHERE recipe_id = ? "
				+ "ORDER BY i.ingredient_order";
		
		try(PreparedStatement stmt = conn.prepareStatement(sql)) {
			setParameter(stmt, 1, recipeId, Integer.class);
			
			try(ResultSet rs = stmt.executeQuery()) {
				List<Ingredient> ingredients = new LinkedList<>();
				
				while(rs.next()) {
					Ingredient ingredient = extract(rs, Ingredient.class);
					Unit unit = extract(rs, Unit.class);
					
					ingredient.setUnitId(unit);
					ingredients.add(ingredient);		
				}
				
				return ingredients;
			}
		}
	}

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

	public List<Recipe> fetchAllRecipes() {
		String sql = "SELECT * FROM " + RECIPE_TABLE + "ORDER BY recipe_name";
		
		try(Connection conn = DbConnection.getConnection()) {
			startTransaction(conn);
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)) {
				try(ResultSet rs = stmt.executeQuery()) {
					List<Recipe> recipes = new LinkedList<>();
					
					while(rs.next()) {
						recipes.add(extract(rs, Recipe.class));
					}
					
					return recipes;
				}
			} catch (Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
			
		}catch (Exception ex) {
			throw new DbException(ex);
		}
	}

	public List<Unit> fetchAllUnits() {
		String sql = "SELECT * FROM " + UNIT_TABLE + "ORDER BY unit_name_singular";
		
		try(Connection conn = DbConnection.getConnection()) {
			startTransaction(conn);
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)) {
				try(ResultSet rs = stmt.executeQuery()) {
					List<Unit> units = new LinkedList<>();
					
					while(rs.next()) {
						units.add(extract(rs, Unit.class));
					}
					
					return units;
				}
			} catch (Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
			
		}catch (Exception ex) {
			throw new DbException(ex);
		}
	}

	public void addIngredientToRecipe(Ingredient ingredient) {
		String sql = "INSERT INTO " + INGREDIENT_TABLE 
				+ " (recipe_id, unit_id, ingredient_name, instruction, ingredient_order, amount)"
				+ " VALUES (?, ?, ?, ?, ?, ?)";
		
		try(Connection conn = DbConnection.getConnection()) {
			startTransaction(conn);
			
			Integer order = getNextSequenceNumber(conn, ingredient.getRecipeId(), INGREDIENT_TABLE, "recipe_id");
			
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)) {
				setParameter(stmt, 1, ingredient.getRecipeId(), Integer.class);
				setParameter(stmt, 2, ingredient.getUnitId().getUnitId(), Integer.class);
				setParameter(stmt, 3, ingredient.getIngredientName(), String.class);
				setParameter(stmt, 4, ingredient.getInstruction(), String.class);
				setParameter(stmt, 5, order, Integer.class );
				setParameter(stmt, 6, ingredient.getAmount(), BigDecimal.class);
				
				stmt.executeUpdate();
				commitTransaction(conn);
				
			} catch (Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
			
		}catch (Exception ex) {
			throw new DbException(ex);
		}
		
	}

	public void addStepToRecipe(Step step) {
		String sql = "INSERT INTO " + STEP_TABLE 
				+ " (recipe_id, step_order, step_text)"
				+ " VALUES (?, ?, ?)";
		
		try(Connection conn = DbConnection.getConnection()) {
			startTransaction(conn);
			
			Integer order = getNextSequenceNumber(conn, step.getRecipeId(), STEP_TABLE, "recipe_id");
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)) {
				setParameter(stmt, 1, step.getRecipeId(), Integer.class);
				setParameter(stmt, 2, order, Integer.class);
				setParameter(stmt, 3, step.getStepText(), String.class);
				
				stmt.executeUpdate();
				commitTransaction(conn);
				
			} catch (Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
			
		} catch (Exception ex) {
			throw new DbException(ex);
		}
	}

	public List<Category> fetchAllCategories() {
		String sql = "SELECT * FROM " + CATEGORY_TABLE + "ORDER BY category_name";
		
		try(Connection conn = DbConnection.getConnection()) {
			startTransaction(conn);
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)) {
				try(ResultSet rs = stmt.executeQuery()) {
					List<Category> categories = new LinkedList<>();
					
					while(rs.next()) {
						categories.add(extract(rs, Category.class));
					}
					
					return categories;
				}
			} catch (Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
			
		}catch (Exception ex) {
			throw new DbException(ex);
		}
	}
	
	public void addCategoryToRecipe(Integer recipeId, String category) {
		String subQuery = "(SELECT category_id FROM +" + CATEGORY_TABLE + " WHERE category_name = ? )";
		String sql = "INSERT INTO " + RECIPE_CATEGORY_TABLE 
				+ " (recipe_id, category_id)"
				+ " VALUES (?, " + subQuery + ")";
		
		try(Connection conn = DbConnection.getConnection()) {
			startTransaction(conn);
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)) {
				setParameter(stmt, 1, recipeId, Integer.class);
				setParameter(stmt, 2, category, String.class);
				
				stmt.executeUpdate();
				commitTransaction(conn);
				
			} catch (Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
			
		} catch (Exception ex) {
			throw new DbException(ex);
		}
	}
	

	public List<Step> fetchAllSteps(Integer recipeId) {
		String sql = "SELECT * FROM " + STEP_TABLE + "WHERE recipe_id = ?";
		
		try(Connection conn = DbConnection.getConnection()) {
			startTransaction(conn);
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)) {
				setParameter(stmt, 1, recipeId, Integer.class);
				
				try(ResultSet rs = stmt.executeQuery()) {
					List<Step> steps = new LinkedList<>();
					
					while(rs.next()) {
						steps.add(extract(rs, Step.class));
					}
					
					return steps;
				}
			} catch (Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
			
		} catch (Exception ex) {
			throw new DbException(ex);
		}
	}

	public boolean modifyStep(Step step) {
		String sql = "UPDATE " + STEP_TABLE 
				+ " SET step_text = ?" 
				+ " WHERE step_id = ?";
		
		try(Connection conn = DbConnection.getConnection()) {
			startTransaction(conn);
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)) {
				setParameter(stmt, 1, step.getStepText(), String.class);
				setParameter(stmt, 2, step.getStepId(), Integer.class);
				
				boolean updated = stmt.executeUpdate() == 1;
				commitTransaction(conn);
				
				return updated;
				
			} catch (Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
			
		} catch (Exception ex) {
			throw new DbException(ex);
		}
		
	}

	public boolean deleteRecipe(Integer recipeId) {
		String sql = "DELETE FROM " + RECIPE_TABLE 
				+ " WHERE recipe_id = ?";
		
		try(Connection conn = DbConnection.getConnection()) {
			startTransaction(conn);
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)) {
				setParameter(stmt, 1, recipeId, Integer.class);
				
				boolean deleted = stmt.executeUpdate() == 1;
				commitTransaction(conn);
				
				return deleted;
				
			} catch (Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
			
		} catch (Exception ex) {
			throw new DbException(ex);
		}
	}


}
