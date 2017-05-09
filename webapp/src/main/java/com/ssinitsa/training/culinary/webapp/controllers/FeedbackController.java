package com.ssinitsa.training.culinary.webapp.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssinitsa.training.culinary.datamodel.Feedback;
import com.ssinitsa.training.culinary.datamodel.RecipeWithFeedback;
import com.ssinitsa.training.culinary.services.IFeedbackService;
import com.ssinitsa.training.culinary.webapp.models.FeedbackModel;
import com.ssinitsa.training.culinary.webapp.models.IdModel;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

	@Inject
	private IFeedbackService feedbackService;

	@RequestMapping(method = RequestMethod.GET)
	public HttpStatus getStatus() {
		return HttpStatus.NOT_FOUND;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByRecipeId(@PathVariable(value = "id") Integer recipeIdParam) {
		List<RecipeWithFeedback> recipeWithFeedbacks = feedbackService.getRecipeWithFedback(recipeIdParam);
		List<FeedbackModel> convertedFeedbacks = new ArrayList<>();
		for (RecipeWithFeedback recipeWithFeedback : recipeWithFeedbacks) {
			convertedFeedbacks.add(entity2model(recipeWithFeedback));
		}
		return new ResponseEntity<List<FeedbackModel>>(convertedFeedbacks, HttpStatus.OK);
	}

	private FeedbackModel entity2model(RecipeWithFeedback recipeWithFeedback) {
		FeedbackModel feedbackModel = new FeedbackModel();
		feedbackModel.setText(recipeWithFeedback.getText());
		feedbackModel.setAuthorFirstName(recipeWithFeedback.getUser().getFirstName());
		feedbackModel.setAuthorLastName(recipeWithFeedback.getUser().getLastName());
		feedbackModel.setRecipe(recipeWithFeedback.getRecipe().getName());
		feedbackModel.setCreated(recipeWithFeedback.getCreated());
		return feedbackModel;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createFeedback(@RequestBody FeedbackModel feedbackModel) {
		Feedback feedback = model2entity(feedbackModel);
		feedbackService.save(feedback);
		return new ResponseEntity<IdModel>(new IdModel(feedback.getId()), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateFeedback(@RequestBody FeedbackModel feedbackModel,
			@PathVariable(value = "id") Integer feedbackIdParam){
		Feedback feedback = feedbackService.get(feedbackIdParam);
		feedback.setText(feedbackModel.getText());
		feedbackService.save(feedback);
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	private Feedback model2entity(FeedbackModel feedbackModel) {
		Feedback feedback = new Feedback();
		feedback.setText(feedbackModel.getText());
		feedback.setUserId(feedbackModel.getAuthorId());
		feedback.setRecipeId(feedbackModel.getRecipeId());
		feedback.setCreated(new Timestamp(new Date().getTime()));
		return feedback;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteFeedback(@PathVariable(value = "id") Integer feedbackIdParam) {
		feedbackService.delete(feedbackIdParam);
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}


}
