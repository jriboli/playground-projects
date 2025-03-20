package recipes.entity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class Recipe {
	private Integer recipeId;
	private String recipeName;
	private String notes;
	private Integer numOfServings;
	private LocalTime prepTime;
	private LocalTime cookTime;
	private LocalDateTime createdAt;
	
	private List<Ingredient> ingredients = new LinkedList<>();
	private List<Step> steps = new LinkedList<>();
	private List<Category> categories = new LinkedList<>();
	
	public Integer getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}
	public String getRecipeName() {
		return recipeName;
	}
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Integer getNumOfServings() {
		return numOfServings;
	}
	public void setNumOfServings(Integer numOfServings) {
		this.numOfServings = numOfServings;
	}
	public LocalTime getPrepTime() {
		return prepTime;
	}
	public void setPrepTime(LocalTime prepTime) {
		this.prepTime = prepTime;
	}
	public LocalTime getCookTime() {
		return cookTime;
	}
	public void setCookTime(LocalTime cookTime) {
		this.cookTime = cookTime;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public List<Step> getSteps() {
		return steps;
	}
	public void setSteps(List<Step> steps) {
		this.steps = steps;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	@Override
	public String toString() {
		return "Recipe [recipeId=" + recipeId + ", recipeName=" + recipeName + ", notes=" + notes + ", numOfServings="
				+ numOfServings + ", prepTime=" + prepTime + ", cookTime=" + cookTime + ", createdAt=" + createdAt
				+ ", ingredients=" + ingredients + ", steps=" + steps + ", categories=" + categories + "]";
	}
	
	
}
