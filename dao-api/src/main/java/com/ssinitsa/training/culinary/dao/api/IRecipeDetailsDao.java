package com.ssinitsa.training.culinary.dao.api;

import java.util.List;

import com.ssinitsa.training.culinary.datamodel.RecipeDetails;
import com.ssinitsa.training.culinary.datamodel.RecipeWithDetails;

public interface IRecipeDetailsDao {
	
	List <RecipeWithDetails> getRecipeWithDetails(Integer id);
	
	RecipeDetails get (Integer id);

	RecipeDetails insert(RecipeDetails recipeDetails);

	void update(RecipeDetails recipeDetails);

	List<RecipeDetails> getByRecipeId(Integer id);

	void delete(Integer id);
	
	void clearRecipeDetails(Integer recipeId);
	
}
