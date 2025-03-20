package projects.entity;

public class Project_Category {
    private Integer projectId;
    private Integer categoryId;

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	@Override
    public String toString() {

        return "";
    }
}