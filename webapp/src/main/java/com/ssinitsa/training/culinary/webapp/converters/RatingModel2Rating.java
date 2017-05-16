package com.ssinitsa.training.culinary.webapp.converters;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ssinitsa.training.culinary.datamodel.Rating;
import com.ssinitsa.training.culinary.webapp.models.RatingModel;

@Component
public class RatingModel2Rating extends Model2Json<RatingModel>{
	
	private final static String baseFile = "storage.json";
	private Rating rating;
	
	public Rating ratingModel2rating(RatingModel ratingModel){
		
		model2json(ratingModel);
		try {
			rating = mapper.readValue(new File(baseFile), Rating.class);
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
		return rating;
	}

}
