package com.ssinitsa.training.culinary.services.feedback;

import java.sql.Timestamp;
import java.util.Date;

import com.ssinitsa.training.culinary.datamodel.Feedback;

public class BeforeFeedbackServiceTest {

	public Feedback createFeedback(Integer userId, Integer recipeId) {
		Feedback feedback = new Feedback();
		feedback.setText("something");
		feedback.setUserId(userId);
		feedback.setRecipeId(recipeId);
		feedback.setCreated(new Timestamp(new Date().getTime()));
		System.out.println(feedback.toString());

		return feedback;

	}
}
