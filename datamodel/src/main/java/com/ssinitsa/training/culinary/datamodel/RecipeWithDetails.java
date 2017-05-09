package com.ssinitsa.training.culinary.datamodel;

public class RecipeWithDetails {

	private Recipe recipe;

	private Ingredient ingredient;

	private Integer quantity;


	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String toString() {
		return "RecipeWithDetails [recipe=" + recipe.toString() + ", ingredient=" + ingredient.toString() + ", quantity="+quantity+"]";
	}
}
