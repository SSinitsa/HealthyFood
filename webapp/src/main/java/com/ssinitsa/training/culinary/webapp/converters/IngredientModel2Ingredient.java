package com.ssinitsa.training.culinary.webapp.converters;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ssinitsa.training.culinary.datamodel.Ingredient;
import com.ssinitsa.training.culinary.webapp.models.IngredientModel;

@Component
public class IngredientModel2Ingredient extends Model2Json<IngredientModel>{
	
	private final static String baseFile = "storage.json";
	private Ingredient ingredient;
	
	public Ingredient ingredientModel2ingredient(IngredientModel ingredientModel){
		
		model2json(ingredientModel);
		try {
			ingredient = mapper.readValue(new File(baseFile), Ingredient.class);
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
		return ingredient;
	}

}
