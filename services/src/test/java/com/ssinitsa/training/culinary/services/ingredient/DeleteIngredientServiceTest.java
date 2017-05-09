package com.ssinitsa.training.culinary.services.ingredient;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import com.ssinitsa.training.culinary.datamodel.Ingredient;
import com.ssinitsa.training.culinary.services.AbstractTest;
import com.ssinitsa.training.culinary.services.IIngredientService;

public class DeleteIngredientServiceTest extends AbstractTest {

	@Inject
	private IIngredientService service;

	private Integer savedIngredientId;
	private Ingredient ingredientFromDb;

	@Before
	public void createTest() {
		BeforeIngredientServiceTest beforeIngredient = new BeforeIngredientServiceTest();
		Ingredient newIngredient = beforeIngredient.createIngredient();
		service.save(newIngredient);
		savedIngredientId = newIngredient.getId();
		System.out.println(newIngredient.toString());
	}

	@Test
	public void deleteTest() {
		service.delete(savedIngredientId);
		ingredientFromDb = service.get(savedIngredientId);
		Assert.isNull(ingredientFromDb, "ingredient must not exist");
	}
}
