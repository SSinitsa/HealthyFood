package com.ssinitsa.training.culinary.webapp.converters;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ssinitsa.training.culinary.datamodel.RecipeDetails;
import com.ssinitsa.training.culinary.webapp.models.RecipeDetailsModel;

@Component
public class DetailsModel2Details extends Model2Json<RecipeDetailsModel>{
	
	private final static String baseFile = "storage.json";
	private RecipeDetails recipeDetails;
	
	public RecipeDetails recipeDetailsModel2recipeDetails(RecipeDetailsModel recipeDetailsModel){
		
		model2json(recipeDetailsModel);
		try {
			recipeDetails = mapper.readValue(new File(baseFile), RecipeDetails.class);
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
		return recipeDetails;
	}

}
