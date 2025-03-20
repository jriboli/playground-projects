package recipes.entity;

import java.math.BigDecimal;

public class Ingredient {
	private Integer ingredientId;
	private Integer recipeId;
	private Unit unitId;
	private String ingredientName;
	private String instruction;
	private Integer ingredientOrder;
	private BigDecimal amount;
	
	public Integer getIngredientId() {
		return ingredientId;
	}
	
	public void setIngredientId(Integer ingredientId) {
		this.ingredientId = ingredientId;
	}
	
	public Integer getRecipeId() {
		return recipeId;
	}
	
	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}
	
	public Unit getUnitId() {
		return unitId;
	}
	
	public void setUnitId(Unit unitId) {
		this.unitId = unitId;
	}
	
	public String getIngredientName() {
		return ingredientName;
	}
	
	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}
	
	public String getInstruction() {
		return instruction;
	}
	
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	
	public Integer getIngredientOrder() {
		return ingredientOrder;
	}
	
	public void setIngredientOrder(Integer ingredientOrder) {
		this.ingredientOrder = ingredientOrder;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "Ingredient [ingredientId=" + ingredientId + ", recipeId=" + recipeId + ", unitId=" + unitId
				+ ", ingredientName=" + ingredientName + ", instruction=" + instruction + ", ingredientOrder="
				+ ingredientOrder + ", amount=" + amount + "]";
	}
}
