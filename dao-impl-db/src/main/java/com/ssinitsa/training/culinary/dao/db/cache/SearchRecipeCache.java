package com.ssinitsa.training.culinary.dao.db.cache;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.ssinitsa.training.culinary.datamodel.Recipe;



public class SearchRecipeCache extends Cache<List<Recipe>>{

	@Override
	protected String getCacheName() {
		return "recipeSearch:";
	}

	@Override
	protected Integer getLifeTime() {
		return 60;
	}

}
