package com.ssinitsa.training.culinary.dao.api;

import java.util.List;

import com.ssinitsa.training.culinary.dao.api.filter.RecipeFilter;
import com.ssinitsa.training.culinary.datamodel.Recipe;
import com.ssinitsa.training.culinary.datamodel.RecipeWithDetails;

public interface IRecipeDao {

	Recipe get(Integer id);

	Recipe insert(Recipe recipe);

	void update(Recipe recipe);

	List<Recipe> getAll();

	void delete(Integer id);
	
	List<Recipe> search(RecipeFilter recipeFilter);

}
