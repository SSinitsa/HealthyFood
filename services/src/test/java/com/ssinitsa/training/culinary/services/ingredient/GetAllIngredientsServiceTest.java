package com.ssinitsa.training.culinary.services.ingredient;

import javax.inject.Inject;

import org.junit.Test;

import com.ssinitsa.training.culinary.services.AbstractTest;
import com.ssinitsa.training.culinary.services.IIngredientService;

public class GetAllIngredientsServiceTest extends AbstractTest {

	@Inject
	private IIngredientService ingredientService;

	
	@Test
	public void getTest() {
		System.out.println(ingredientService.get(500));
	}
}
