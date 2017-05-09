package com.ssinitsa.training.culinary.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ssinitsa.training.culinary.datamodel.Feedback;
import com.ssinitsa.training.culinary.datamodel.RecipeWithFeedback;

public interface IFeedbackService {

	Feedback get(Integer id);

	@Transactional
	void save(Feedback feedback);

	//List<Feedback> getAll();
	
	@Transactional
	void delete(Integer id);

	List<RecipeWithFeedback> getRecipeWithFedback(Integer id);
}
