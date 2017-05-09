package com.ssinitsa.training.culinary.dao.api;

import java.util.List;

import com.ssinitsa.training.culinary.datamodel.Rating;
import com.ssinitsa.training.culinary.datamodel.RecipeWithRating;

public interface IRatingDao {

	Rating get(Integer id);

	Rating insert(Rating rating);

	void update(Rating rating);

	List<Rating> getAll();

	void delete(Integer id);
	
	List<RecipeWithRating> getRecipeWithRating(Integer id);

}
