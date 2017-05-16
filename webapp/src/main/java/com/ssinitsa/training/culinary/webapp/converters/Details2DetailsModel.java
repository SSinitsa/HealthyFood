package com.ssinitsa.training.culinary.webapp.converters;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ssinitsa.training.culinary.datamodel.RecipeDetails;
import com.ssinitsa.training.culinary.webapp.models.RecipeDetailsModel;

@Component
public class Details2DetailsModel extends Model2Json<RecipeDetails>{
	
	private final static String baseFile = "storage.json";
	private RecipeDetailsModel recipeDetailsModel;
	
	public RecipeDetailsModel recipeDetails2recipeDetailsModel(RecipeDetails recipeDetails){
		
		model2json(recipeDetails);
		try {
			recipeDetailsModel = mapper.readValue(new File(baseFile), RecipeDetailsModel.class);
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
		return recipeDetailsModel;
	}

}
