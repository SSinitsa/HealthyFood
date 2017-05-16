package com.ssinitsa.training.culinary.webapp.converters;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ssinitsa.training.culinary.datamodel.Rating;
import com.ssinitsa.training.culinary.webapp.models.RatingModel;

@Component
public class Rating2RatingModel extends Model2Json<Rating>{
	
	private final static String baseFile = "storage.json";
	private RatingModel ratingModel;
	
	public RatingModel rating2ratingModel(Rating rating){
		
		model2json(rating);
		try {
			ratingModel = mapper.readValue(new File(baseFile), RatingModel.class);
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
		return ratingModel;
	}

}
