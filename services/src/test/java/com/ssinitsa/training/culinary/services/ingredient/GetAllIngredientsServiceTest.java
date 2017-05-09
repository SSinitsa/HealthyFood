package com.ssinitsa.training.culinary.services.ingredient;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import com.ssinitsa.training.culinary.datamodel.Ingredient;
import com.ssinitsa.training.culinary.datamodel.IngredientCategory;
import com.ssinitsa.training.culinary.services.AbstractTest;
import com.ssinitsa.training.culinary.services.IIngredientService;

public class GetAllIngredientsServiceTest extends AbstractTest {

	@Inject
	private IIngredientService ingredientService;

	private Integer savedIngredientId;
	private Integer savedIngredientId2;

	@Test
	public void orderByNameTest() {
		List<Ingredient> ingredients = ingredientService.getAll();
		Collections.sort(ingredients, ingredientService.orderBy("name"));
		//Assert.isTrue(compareStrings((ingredients.get(0).getName()), (ingredients.get(1).getName())));
		System.out.println(ingredients);
		System.out.println((ingredients.get(1).getName()).compareTo((ingredients.get(0).getName())));
	}
}
