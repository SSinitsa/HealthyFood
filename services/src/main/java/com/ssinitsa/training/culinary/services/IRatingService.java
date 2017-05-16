package com.ssinitsa.training.culinary.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ssinitsa.training.culinary.datamodel.Rating;
import com.ssinitsa.training.culinary.datamodel.RecipeWithRating;

public interface IRatingService {

	Rating get(Integer id);

	@Transactional
	void save(Rating rating);

	@Transactional
	void delete(Integer id);
	
	List<RecipeWithRating> getRecipeWithRating(Integer id);
	
	BigDecimal getAverageRating (Integer id);
}
