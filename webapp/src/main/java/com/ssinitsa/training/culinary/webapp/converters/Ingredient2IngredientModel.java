package com.ssinitsa.training.culinary.webapp.converters;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ssinitsa.training.culinary.datamodel.Ingredient;
import com.ssinitsa.training.culinary.webapp.models.IngredientModel;

@Component
public class Ingredient2IngredientModel extends Model2Json<Ingredient>{
	
	private final static String baseFile = "storage.json";
	private IngredientModel ingredientModel;
	
	public IngredientModel ingredient2ingredientModel(Ingredient ingredient){
		
		model2json(ingredient);
		try {
			ingredientModel = mapper.readValue(new File(baseFile), IngredientModel.class);
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
		return ingredientModel;
	}

}
