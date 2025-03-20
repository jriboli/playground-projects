package projects.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import provided.util.DaoBase;
import projects.entity.Category;
import projects.entity.Material;
import projects.entity.Project;
import projects.entity.Step;
import projects.exception.DbException;

public class ProjectsDao extends DaoBase {
	private static final String PROJECT_TABLE = "project";
	private static final String MATERIAL_TABLE = "material";
	private static final String STEP_TABLE = "step";
	private static final String CATEGORY_TABLE = "category";
	private static final String PROJECT_CATEGORY_TABLE = "project_category";

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

	public Project insertProject(Project project) {
		String sql = ""
				+ "INSERT INTO " + PROJECT_TABLE + " "
				+ "(project_name, estimated_hours, actual_hours, difficulty, notes) "
				+ "VALUES (?, ?, ?, ?, ?)";
		
		try(Connection conn = DbConnection.getConnection()) {
			startTransaction(conn);
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)) {
				setParameter(stmt, 1, project.getProjectName(), String.class);
				setParameter(stmt, 2, project.getEstimatedHours(), BigDecimal.class);
				setParameter(stmt, 3, project.getActualHours(), BigDecimal.class);
				setParameter(stmt, 4, project.getDifficulty(), Integer.class);
				setParameter(stmt, 5, project.getNotes(), String.class );
				
				stmt.executeUpdate();
				Integer projectId = getLastInsertId(conn, PROJECT_TABLE);
				
				commitTransaction(conn);
				
				project.setProjectId(projectId);
				return project;
			} catch (Exception e) {
				rollbackTransaction(conn);
				e.printStackTrace();
				throw new DbException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
	}
	
	// FETCH METHODS 
	public Optional<Project> fetchSingleProjectById(Integer projectId) {
		String sql = ""
				+ "SELECT * FROM " + PROJECT_TABLE + " "
				+ "WHERE project_id =" + projectId + " ";
		
		// TO DO : Need to look into this method
		// TO DO : Understand 'Optional' better
		return getProjects(sql);
	}
	
	public List<Project> fetchAllProjects() {
		String sql = "SELECT * FROM " + PROJECT_TABLE + " ";
		 return getProjectAndDetails(sql);
	}

	public List<Material> fetchMaterialsByProjectId(Integer projectId) {
		String sql = ""
				+ "SELECT * FROM " + MATERIAL_TABLE + " "
				+ "WHERE project_id =" + projectId + " ";
		
		return getMaterials(sql);
	}
	
	public List<Step> fetchStepsByProjectId(Integer projectId) {
		String sql = ""
				+ "SELECT * FROM " + STEP_TABLE + " "
				+ "WHERE project_id =" + projectId + " ";
		
		return getSteps(sql);		
	}
	
	public List<Category> fetchCategoriesByProjectId(Integer projectId) {
		// Could not use simple SELECT, had to add JOIN as CATEGORY details only stored in CATEGORY TABLE, though
		// no project_id in CATEGORY TABLE
		String sql = ""
				+ "SELECT c.* FROM " + PROJECT_CATEGORY_TABLE + " pc "
				+ "JOIN " + CATEGORY_TABLE + " c USING(category_id) "
				+ "WHERE pc.project_id =" + projectId + " ";
		
		return getCategories(sql);
	}
	
	public List<Category> fetchAllCategories() {
		String sql = ""
				+ "SELECT * FROM " + CATEGORY_TABLE + " ";
		
		return getCategories(sql);
	}
	
	// SELECT QUERY BY OBJECT METHODS 
	private List<Category> getCategories(String query) {
		// Moved this out into its own Method as I feel we will be calling these more from with different queries
		try(Connection conn = DbConnection.getConnection()) {			
			try(Statement stmt = conn.createStatement()) {
				
				ResultSet results = stmt.executeQuery(query);
				
				List<Category> categoryResults = new LinkedList<>();
				while(results.next()) {
					Category c = new Category();
					c.setCategoryId(Integer.valueOf(results.getString("category_id")));
					c.setCategoryName(results.getString("category_name"));
					
					categoryResults.add(c);
				}
				
				return categoryResults;
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new DbException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
	}
	
	private List<Step> getSteps(String query) {
		try(Connection conn = DbConnection.getConnection()) {			
			try(Statement stmt = conn.createStatement()) {
				
				ResultSet results = stmt.executeQuery(query);
				
				List<Step> stepResults = new LinkedList<>();
				while(results.next()) {
					Step s = new Step();
					s.setProjectId(Integer.valueOf(results.getString("project_id")));
					s.setStepId(Integer.valueOf(results.getString("step_id")));
					s.setStepText(results.getString("step_text"));
					s.setStepOrder(Integer.valueOf(results.getString("step_order")));
					
					stepResults.add(s);
				}
				
				return stepResults;
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new DbException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
	}
	
	private List<Material> getMaterials(String query) {
		try(Connection conn = DbConnection.getConnection()) {			
			try(Statement stmt = conn.createStatement()) {
				
				ResultSet results = stmt.executeQuery(query);
				
				List<Material> materialResults = new LinkedList<>();
				while(results.next()) {
					Material m = new Material();
					m.setProjectId(Integer.valueOf(results.getString("project_id")));
					m.setMaterialId(Integer.valueOf(results.getString("material_id")));
					m.setMaterialName(results.getString("material_name"));
					m.setNumRequired(Integer.valueOf(results.getString("num_required")));
					// Just a hint for working with BigDecimal --- BigDecimal(input).setScale(2)
					m.setCost(results.getBigDecimal("cost"));
					
					materialResults.add(m);
				}
				
				return materialResults;
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new DbException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
	}

	private Optional<Project> getProjects(String query) {
		try(Connection conn = DbConnection.getConnection()) {
			startTransaction(conn);
			
			try {			
				Project project = null;
				
				try(PreparedStatement stmt = conn.prepareStatement(query)){
					try(ResultSet rs = stmt.executeQuery()){
						
						if(rs.next()) {
							
							project = extract(rs, Project.class);
							
							if(Objects.nonNull(project)) {
								// TO DO : Am I not closing connections ??
								project.setMaterials(fetchMaterialsByProjectId(project.getProjectId()));
								project.setSteps(fetchStepsByProjectId(project.getProjectId()));
								project.setCategories(fetchCategoriesByProjectId(project.getProjectId()));
								
								// TO DO : Why does professor use the following:
								// Okay I think I understand what is happening here
								// instead of replacing, we are adding the results of fetchMaterial to the 
								// existing List of materials for the project
								// project.getMaterials().addAll(fetchMaterialsByProjectId(project.getProjectId()));
							}
						}
					}
				}
				
				commitTransaction(conn);
				return Optional.ofNullable(project);
			} catch(Exception e) {
				rollbackTransaction(conn);
				throw new DbException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
	}

	// UPDATE METHODS
	public boolean updateProject(Project project) {
		String sql = ""
				+ "UPDATE " + PROJECT_TABLE + " "
				+ "SET project_name = ?, estimated_hours = ?, actual_hours = ?, difficulty = ?, notes = ?"
				+ "WHERE project_id = ?";
		
		try(Connection conn = DbConnection.getConnection()) {
			startTransaction(conn);
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)) {
				setParameter(stmt, 1, project.getProjectName(), String.class);
				setParameter(stmt, 2, project.getEstimatedHours(), BigDecimal.class);
				setParameter(stmt, 3, project.getActualHours(), BigDecimal.class);
				setParameter(stmt, 4, project.getDifficulty(), Integer.class);
				setParameter(stmt, 5, project.getNotes(), String.class );
				setParameter(stmt, 6, project.getProjectId(), Integer.class );
				
				boolean updated = stmt.executeUpdate() == 1;				
				commitTransaction(conn);

				return updated;
			} catch (Exception e) {
				rollbackTransaction(conn);
				e.printStackTrace();
				throw new DbException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
	}
	
	// DELETE METHODS 
	public boolean deleteProject(Integer selectedProjectId) {
		String sql = ""
				+ "DELETE FROM " + PROJECT_TABLE + " "
				+ "WHERE project_id = ?";
		
		try(Connection conn = DbConnection.getConnection()) {
			startTransaction(conn);
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)) {
				setParameter(stmt, 1, selectedProjectId, Integer.class);
				
				boolean deleted = stmt.executeUpdate() == 1;				
				commitTransaction(conn);

				return deleted;
			} catch (Exception e) {
				rollbackTransaction(conn);
				e.printStackTrace();
				throw new DbException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
	}
	
	// Left over from trying to make this method myself
	private List<Project> getProjectAndDetails(String query) {
		try(Connection conn = DbConnection.getConnection()) {			
			try(Statement stmt = conn.createStatement()) {
				
				ResultSet results = stmt.executeQuery(query);
				
				List<Project> projectResults = new LinkedList<>();
				
				while(results.next()) {
					Project p = new Project();
					p.setProjectId(Integer.valueOf(results.getString("project_id")));
					p.setProjectName(results.getString("project_name"));
					p.setEstimatedHours(results.getBigDecimal("estimated_hours"));
					p.setActualHours(results.getBigDecimal("actual_hours"));
					p.setDifficulty(Integer.valueOf(results.getString("difficulty")));
					p.setNotes(results.getString("notes"));
					
					p.setMaterials(fetchMaterialsByProjectId(p.getProjectId()));
					p.setSteps(fetchStepsByProjectId(p.getProjectId()));
					p.setCategories(fetchCategoriesByProjectId(p.getProjectId()));
					
					projectResults.add(p);
				}
				
				return projectResults;
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new DbException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
	}
	

	public void addCategoryToProject(Integer categoryId, Integer projectId) {
		String sql = ""
				+ "INSERT INTO " + PROJECT_CATEGORY_TABLE + " "
				+ "(category_id, project_id) "
				+ "VALUES (?, ?)";
		
		try(Connection conn = DbConnection.getConnection()) {
			startTransaction(conn);
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)) {
				setParameter(stmt, 1, categoryId, Integer.class);
				setParameter(stmt, 2, projectId, Integer.class);
				
				stmt.executeUpdate();				
				commitTransaction(conn);
				
			} catch (Exception e) {
				rollbackTransaction(conn);
				e.printStackTrace();
				throw new DbException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		
	}

	public void addStepToProject(Step step, Integer projectId) {
		String sql = ""
				+ "INSERT INTO " + STEP_TABLE + " "
				+ "(project_id, step_text, step_order) "
				+ "VALUES (?, ?, ?)";
		
		try(Connection conn = DbConnection.getConnection()) {
			startTransaction(conn);
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)) {
				setParameter(stmt, 1, projectId, Integer.class);
				setParameter(stmt, 2, step.getStepText(), String.class);
				setParameter(stmt, 3, step.getStepOrder(), Integer.class);
				
				stmt.executeUpdate();				
				commitTransaction(conn);
				
			} catch (Exception e) {
				rollbackTransaction(conn);
				e.printStackTrace();
				throw new DbException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		
	}

	public void addMaterialToProject(Material material, Integer projectId) {
		String sql = ""
				+ "INSERT INTO " + MATERIAL_TABLE + " "
				+ "(project_id, material_name, num_required, cost) "
				+ "VALUES (?, ?, ?, ?)";
		
		try(Connection conn = DbConnection.getConnection()) {
			startTransaction(conn);
			
			try(PreparedStatement stmt = conn.prepareStatement(sql)) {
				setParameter(stmt, 1, projectId, Integer.class);
				setParameter(stmt, 2, material.getMaterialName(), String.class);
				setParameter(stmt, 3, material.getNumRequired(), Integer.class);
				setParameter(stmt, 4, material.getCost(), BigDecimal.class);
				
				stmt.executeUpdate();				
				commitTransaction(conn);
				
			} catch (Exception e) {
				rollbackTransaction(conn);
				e.printStackTrace();
				throw new DbException(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		
	}

	public boolean deleteCategory(Integer categoryId, Integer projectId) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteStep(Integer stepId, Integer projectId) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean deleteMaterial(Integer materialId, Integer projectId) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
