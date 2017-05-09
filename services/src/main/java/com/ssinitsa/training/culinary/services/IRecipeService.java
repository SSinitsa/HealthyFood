package com.ssinitsa.training.culinary.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ssinitsa.training.culinary.dao.api.filter.RecipeFilter;
import com.ssinitsa.training.culinary.datamodel.Recipe;

public interface IRecipeService {

	Recipe get(Integer id);

	@Transactional
	void save(Recipe recipe);

	List<Recipe> getAll();

	@Transactional
	void delete(Integer id);

	List<Recipe> search(RecipeFilter recipeFilter);
}
