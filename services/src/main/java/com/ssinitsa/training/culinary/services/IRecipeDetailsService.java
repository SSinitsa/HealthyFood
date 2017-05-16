package com.ssinitsa.training.culinary.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ssinitsa.training.culinary.datamodel.RecipeDetails;
import com.ssinitsa.training.culinary.datamodel.RecipeWithDetails;
import com.ssinitsa.training.culinary.services.models.AverageRecipeValues;

public interface IRecipeDetailsService {

	RecipeDetails get(Integer id);
	
	@Transactional
	void save(RecipeDetails recipeDetails);

	@Transactional
	void delete(Integer id);
	
	@Transactional
	void clearRecipeDetails (Integer recipeId);

	List<RecipeWithDetails> getWithDetails(Integer recipeId);
	
	Integer getRecipeWeight (Integer recipeId);
	
	AverageRecipeValues getAvgValues (Integer recipeId);
	
}
