package com.ssinitsa.training.culinary.services.ingredient;


import com.ssinitsa.training.culinary.datamodel.Ingredient;
import com.ssinitsa.training.culinary.datamodel.IngredientCategory;

public class BeforeIngredientServiceTest {

	public Ingredient createIngredient() {
		Ingredient ingredient = new Ingredient();
		ingredient.setName("new ingredient from java");
		ingredient.setCalories(3);
		ingredient.setFats(0.2);
		ingredient.setProteins(0.3);
		ingredient.setCarbohydrates(0.4);
		ingredient.setCategory(IngredientCategory.fruit);
		return ingredient;

	}
}
