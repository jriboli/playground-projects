package recipes.entity;

public class Step {
	private Integer stepId;
	private Integer recipeId;
	private Integer stepOrder;
	private String stepText;
	
	public Integer getStepId() {
		return stepId;
	}
	
	public void setStepId(Integer stepId) {
		this.stepId = stepId;
	}
	
	public Integer getRecipeId() {
		return recipeId;
	}
	
	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}
	
	public Integer getStepOrder() {
		return stepOrder;
	}
	
	public void setStepOrder(Integer stepOrder) {
		this.stepOrder = stepOrder;
	}
	
	public String getStepText() {
		return stepText;
	}
	
	public void setStepText(String stepText) {
		this.stepText = stepText;
	}

	@Override
	public String toString() {
		return "Step [stepId=" + stepId + ", recipeId=" + recipeId + ", stepOrder=" + stepOrder + ", stepText="
				+ stepText + "]";
	}
}
