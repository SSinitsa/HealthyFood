package com.ssinitsa.training.culinary.webapp.converters;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ssinitsa.training.culinary.datamodel.Recipe;
import com.ssinitsa.training.culinary.webapp.models.RecipeModel;

@Component
public class Recipe2RecipeModel extends Model2Json<Recipe>{
	
	private final static String baseFile = "storage.json";
	private RecipeModel recipeModel;
	
	public RecipeModel recipe2recipeModel(Recipe recipe){
		
		model2json(recipe);
		try {
			recipeModel = mapper.readValue(new File(baseFile), RecipeModel.class);
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
		return recipeModel;
	}

}
