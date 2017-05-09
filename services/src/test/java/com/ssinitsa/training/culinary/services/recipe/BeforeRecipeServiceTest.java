package com.ssinitsa.training.culinary.services.recipe;

import java.sql.Timestamp;
import java.util.Date;

import com.ssinitsa.training.culinary.datamodel.DishCategory;
import com.ssinitsa.training.culinary.datamodel.Recipe;

public class BeforeRecipeServiceTest {
	
	public Recipe createRecipe(Integer userId) {
	Recipe recipe = new Recipe();
	recipe.setName("new recipe");
	recipe.setDescription("something");
	recipe.setAuthorId(userId);
	recipe.setCreated(new Timestamp(new Date().getTime()));
	recipe.setCategory(DishCategory.hot);
	recipe.setVegetarian(true);
	System.out.println(recipe.toString());
	
	return recipe;
	
}}
