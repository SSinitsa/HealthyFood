package com.ssinitsa.training.culinary.webapp.converters;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ssinitsa.training.culinary.datamodel.Recipe;
import com.ssinitsa.training.culinary.webapp.models.RecipeModel;

@Component
public class RecipeModel2Recipe extends Model2Json<RecipeModel>{
	
	private final static String baseFile = "storage.json";
	private Recipe recipe;
	
	public Recipe recipeModel2recipe(RecipeModel recipeModel){
		
		model2json(recipeModel);
		try {
			recipe = mapper.readValue(new File(baseFile), Recipe.class);
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
		return recipe;
	}

}
