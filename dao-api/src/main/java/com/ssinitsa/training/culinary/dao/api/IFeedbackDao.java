package com.ssinitsa.training.culinary.dao.api;

import java.util.List;

import com.ssinitsa.training.culinary.datamodel.Feedback;
import com.ssinitsa.training.culinary.datamodel.RecipeWithFeedback;

public interface IFeedbackDao {
	
	List <RecipeWithFeedback> getRecipeWithFeedback(Integer id);
	
	Feedback get(Integer id);

	Feedback insert(Feedback feedback);

	void update(Feedback feedback);

	List<Feedback> getAll();

	void delete(Integer id);
	
	void clearFeedbacks(Integer recipeId);

}
