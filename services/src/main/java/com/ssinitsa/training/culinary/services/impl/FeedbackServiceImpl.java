package com.ssinitsa.training.culinary.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ssinitsa.training.culinary.dao.api.IFeedbackDao;
import com.ssinitsa.training.culinary.datamodel.Feedback;
import com.ssinitsa.training.culinary.datamodel.RecipeWithFeedback;
import com.ssinitsa.training.culinary.services.IFeedbackService;

@Service
public class FeedbackServiceImpl implements IFeedbackService {

	private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackServiceImpl.class);

	@Inject
	private IFeedbackDao feedbackDao;

	@Override
	public Feedback get(Integer id) {
		return feedbackDao.get(id);
	}

	/*@Override
	public List<Feedback> getAll() {
		return feedbackDao.getAll();
	}*/

	@Override
	public void save(Feedback feedback) {
		if (feedback.getId() == null) {
			System.out.println("Insert new Feedback");
			feedbackDao.insert(feedback);
			LOGGER.info("Add new Feedback.id={} text={} for Recipe.id={} from User.id={}", feedback.getId(),
					feedback.getText(), feedback.getRecipeId(), feedback.getUserId());
		} else {
			feedbackDao.update(feedback);
			LOGGER.info("Update Feedback.id={} text={} for Recipe.id={} by User.id={}", feedback.getId(),
					feedback.getText(), feedback.getRecipeId(), feedback.getUserId());
		}
	}

	@Override
	public void delete(Integer id) {
		feedbackDao.delete(id);
		LOGGER.info("Delete Feedback.id"+id);

	}

	
	@Override
	public List<RecipeWithFeedback> getRecipeWithFedback(Integer id) {
		return feedbackDao.getRecipeWithFeedback(id);
	}
}
