package com.ssinitsa.training.culinary.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ssinitsa.training.culinary.dao.api.IRecipeDao;
import com.ssinitsa.training.culinary.dao.api.filter.RecipeFilter;
import com.ssinitsa.training.culinary.datamodel.Recipe;
import com.ssinitsa.training.culinary.services.IRecipeService;

@Service
public class RecipeServiceImpl implements IRecipeService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RecipeServiceImpl.class);

	@Inject
	private IRecipeDao recipeDao;

	@Override
	public Recipe get(Integer id) {
		return recipeDao.get(id);
	}

	@Override
	public List<Recipe> getAll() {
		return recipeDao.getAll();
	}

	@Override
	public void save(Recipe recipe) {
		if (recipe.getId() == null) {
			recipeDao.insert(recipe);
			LOGGER.info("Add new recipe: "+recipe.toString());
		} else {
			recipeDao.update(recipe);
			LOGGER.info("Update recipe: "+recipe.toString());
		}
	}

	@Override
	public void delete(Integer id) {
		recipeDao.delete(id);
		LOGGER.info("Delete recipe.id="+id);
	}

	@Override
	public List<Recipe> search(RecipeFilter recipeFilter) {
		return recipeDao.search(recipeFilter);
	}

}
