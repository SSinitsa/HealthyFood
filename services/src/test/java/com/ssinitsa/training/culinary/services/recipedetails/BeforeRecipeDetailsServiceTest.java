package com.ssinitsa.training.culinary.services.recipedetails;

import com.ssinitsa.training.culinary.datamodel.RecipeDetails;


public class BeforeRecipeDetailsServiceTest {
	
	public RecipeDetails createRecipeDetails(Integer recipeId, Integer ingredientId) {
	RecipeDetails recipeDetails = new RecipeDetails();
	recipeDetails.setRecipeId(recipeId);
	recipeDetails.setIngredientId(ingredientId);
	recipeDetails.setQuantity(100);	
	System.out.println(recipeDetails.toString());
	
	return recipeDetails;
	
}}
