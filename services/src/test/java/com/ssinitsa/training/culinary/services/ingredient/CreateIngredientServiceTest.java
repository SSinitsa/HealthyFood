package com.ssinitsa.training.culinary.services.ingredient;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Test;
import org.springframework.util.Assert;

import com.ssinitsa.training.culinary.datamodel.Ingredient;
import com.ssinitsa.training.culinary.datamodel.IngredientCategory;
import com.ssinitsa.training.culinary.services.AbstractTest;
import com.ssinitsa.training.culinary.services.IIngredientService;

public class CreateIngredientServiceTest extends AbstractTest {

	@Inject
	private IIngredientService service;
	
	private Integer savedIngredientId;
	
	@After
	public void deleteCreatedIngredient(){
		service.delete(savedIngredientId);
	}

	@Test
	public void createTest() {
		Ingredient ingredient = new Ingredient();
		ingredient.setName("new ingredient from java");
		ingredient.setCalories(1);
		ingredient.setFats(0.2);
		ingredient.setProteins(0.3);
		ingredient.setCarbohydrates(0.4);
		ingredient.setCategory(IngredientCategory.fruit);
		service.save(ingredient);

		savedIngredientId = ingredient.getId();
		Ingredient ingredientFromDb = service.get(savedIngredientId);

		Assert.notNull(ingredientFromDb, "ingredient must be saved");

		Assert.notNull(ingredientFromDb.getCalories(), "calories column must not me empty");

		Assert.isTrue(ingredientFromDb.getCalories().equals(ingredient.getCalories()), "calories must be equals");

		Assert.notNull(ingredientFromDb.getFats(), "fats column must not me empty");

		Assert.isTrue(ingredientFromDb.getFats() == (ingredient.getFats()), "fats must be equals");

		Assert.notNull(ingredientFromDb.getProteins(), "proteins column must not me empty");

		Assert.isTrue(ingredientFromDb.getProteins() == (ingredient.getProteins()), "proteins must be equals");

		Assert.notNull(ingredientFromDb.getCarbohydrates(), "carbohydrates column must not me empty");

		Assert.isTrue(ingredientFromDb.getCarbohydrates() == (ingredient.getCarbohydrates()),
				"carbohydrates must be equals");

		Assert.notNull(ingredientFromDb.getCategory(), "category column must not me empty");

		Assert.isTrue(ingredientFromDb.getCategory().equals(ingredient.getCategory()), "category must be equals");

	}
	
	

}
