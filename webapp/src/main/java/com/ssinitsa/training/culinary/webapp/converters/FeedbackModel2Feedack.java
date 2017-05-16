package com.ssinitsa.training.culinary.webapp.converters;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ssinitsa.training.culinary.datamodel.Feedback;
import com.ssinitsa.training.culinary.webapp.models.FeedbackModel;

@Component
public class FeedbackModel2Feedack extends Model2Json<FeedbackModel>{
	
	private final static String baseFile = "storage.json";
	private Feedback feedback;
	
	public Feedback feedbackModel2feedback(FeedbackModel feedbackModel){
		
		model2json(feedbackModel);
		try {
			feedback = mapper.readValue(new File(baseFile), Feedback.class);
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
		return feedback;
	}

}
