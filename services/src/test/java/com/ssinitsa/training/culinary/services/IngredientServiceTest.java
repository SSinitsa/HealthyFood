package com.ssinitsa.training.culinary.services;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.ssinitsa.training.culinary.datamodel.Ingredient;
import com.ssinitsa.training.culinary.datamodel.IngredientCategory;

public class IngredientServiceTest extends AbstractTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(IngredientServiceTest.class);

	@Inject
	private IIngredientService ingredientService;

	private Ingredient newIngredient;
	
	private Integer savedIngredientId;

	private Integer savedIngredientId2;

	@Before
	public void createEntity() {
		newIngredient = createEntity(new Ingredient());
		ingredientService.save(newIngredient);
		savedIngredientId = newIngredient.getId();
	}

	@After
	public void deleteEntity() {
		if (ingredientService.get(savedIngredientId) != null) {
			ingredientService.delete(savedIngredientId);
		}
		if (ingredientService.get(savedIngredientId2) != null) {
			ingredientService.delete(savedIngredientId2);
		}
	}

	@Test
	public void getTest() {
		LOGGER.info("Ingredient getTest");
		Ingredient ingredientFromDb = ingredientService.get(savedIngredientId);
		Assert.notNull(ingredientFromDb, "Ingredient must be exist");
		Assert.isTrue(!hasEmptyField(ingredientFromDb), "Ingredient should'nt have empty columns");
	}

	@Test
	public void insertTest() {
		LOGGER.info("Ingredient insertTest");
		Ingredient newIngredient2 = createEntity(new Ingredient());
		ingredientService.save(newIngredient2);
		savedIngredientId2 = newIngredient2.getId();
		Ingredient ingredientFromDb = ingredientService.get(savedIngredientId2);
		Assert.isTrue(newIngredient2.equals(ingredientFromDb), "Ingredients should be equals");
		Assert.isTrue(!hasEmptyField(ingredientFromDb), "Ingredient should'nt have empty columns");
	}

	@Test
	public void updateTest() {
		LOGGER.info("Ingredient updateTest");
		Ingredient updatingIngredient = createEntity(new Ingredient());
		updatingIngredient.setId(savedIngredientId);
		updatingIngredient.setCategory(IngredientCategory.vegetable);
		ingredientService.save(updatingIngredient);
		Ingredient ingredientFromDb = ingredientService.get(savedIngredientId);
		Assert.isTrue(newIngredient.getId().equals(ingredientFromDb.getId()), "ID columns must be equals");
		Assert.isTrue(!newIngredient.getName().equals(ingredientFromDb.getName()), "Name columns must be not equals");
		Assert.isTrue(!newIngredient.getCalories().equals(ingredientFromDb.getCalories()), "Calories columns must be not equals");
		Assert.isTrue(!newIngredient.getFats().equals(ingredientFromDb.getFats()), "Fats columns must be not equals");
		Assert.isTrue(!newIngredient.getProteins().equals(ingredientFromDb.getProteins()), "Proteins columns must be not equals");
		Assert.isTrue(!newIngredient.getCarbohydrates().equals(ingredientFromDb.getCarbohydrates()), "Carbohydrates columns must be not equals");
		Assert.isTrue(!newIngredient.getCategory().equals(ingredientFromDb.getCategory()), "Category columns must be not equals");
	}

	@Test
	public void getAllTest() {
		LOGGER.info("Ingredient getAllTest");
		Ingredient newIngredient2 = createEntity(new Ingredient());
		ingredientService.save(newIngredient2);
		savedIngredientId2 = newIngredient2.getId();
		List<Ingredient> ingredients = ingredientService.getAll();
		Assert.notEmpty(ingredients, "List of Ingredients should'nt be empty");
		Assert.isTrue(ingredients.size() > 1, "The number of Ingredients should be more than 1");
	}
	
	@Test
	public void deleteTest(){
		LOGGER.info("Ingredient deleteTest");
		ingredientService.delete(savedIngredientId);
		Ingredient ingredientFromDb = ingredientService.get(savedIngredientId);
		Assert.isNull(ingredientFromDb, "Ingredient must not exist");
	}

	@Test
	public void getByCategoryTest() {
		LOGGER.info("Ingredient getByCategoryTest, category = fruit");
		List<Ingredient> ingredients = ingredientService.getByCategory("fruit");
		Assert.notEmpty(ingredients);
		for (Ingredient ingredient : ingredients) {
			Assert.isTrue(ingredient.getCategory().equals(IngredientCategory.fruit), "All Ingredients must be \"fruit\"");
		}
	}

	@Test
	public void orderByNameTest() {
		LOGGER.info("Ingredient orderByNameTest");
		List<Ingredient> ingredients = ingredientService.getAll();
		Collections.sort(ingredients, ingredientService.orderBy("name"));
		Assert.isTrue((((ingredients.get(0).getName()).compareTo(ingredients.get(1).getName()))<=0), "The next Name column should be higher than the previous one.");
	}

	@Test
	public void descendingOrderByCaloriesTest() {
		LOGGER.info("Ingredient descendingOrderByCaloriesTest");
		List<Ingredient> ingredients = ingredientService.getAll();
		Collections.sort(ingredients, ingredientService.descendingOrderBy("calories"));
		Assert.isTrue((ingredients.get(0).getCalories()>=ingredients.get(1).getCalories()), "The next Calories column should be less than the previous one");
	}

}
