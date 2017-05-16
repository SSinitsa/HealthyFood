package com.ssinitsa.training.culinary.webapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RecipeDetailsModel {
	
	private Integer id;

	private Integer recipeId;

	private String recipe;

	private Integer authorId;

	private String author;
	
	private Integer ingredientId;

	private String ingredient;

	private Integer quantity;

	public Integer getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(Integer recipeId) {
		this.recipeId = recipeId;
	}

	public String getRecipe() {
		return recipe;
	}

	public void setRecipe(String recipe) {
		this.recipe = recipe;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(Integer ingredientId) {
		this.ingredientId = ingredientId;
	}

	public String getIngredient() {
		return ingredient;
	}

	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "RecipeDetailsModel [recipeId=" + recipeId + ", recipe=" + recipe + ", authorId=" + authorId
				+ ", author=" + author + ", ingredientId=" + ingredientId + ", ingredient=" + ingredient + ", quantity="
				+ quantity + "]";
	}

}
