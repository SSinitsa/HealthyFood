package com.ssinitsa.training.culinary.webapp.converters;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ssinitsa.training.culinary.datamodel.Feedback;
import com.ssinitsa.training.culinary.datamodel.RecipeWithFeedback;
import com.ssinitsa.training.culinary.webapp.models.FeedbackModel;

@Component
public class Feedback2FeedbackModel extends Model2Json<RecipeWithFeedback>{
	
	private final static String baseFile = "storage.json";
	private FeedbackModel feedbackModel;
	
	public FeedbackModel feedback2feedbackModel(RecipeWithFeedback feedback){
		
		model2json(feedback);
		try {
			feedbackModel = mapper.readValue(new File(baseFile), FeedbackModel.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return feedbackModel;
	}

}
