package com.ssinitsa.training.culinary.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.ssinitsa.training.culinary.datamodel.Ingredient;
import com.ssinitsa.training.culinary.datamodel.Recipe;
import com.ssinitsa.training.culinary.datamodel.RecipeDetails;
import com.ssinitsa.training.culinary.datamodel.RecipeWithDetails;
import com.ssinitsa.training.culinary.datamodel.User;

import models.AverageRecipeValues;

public class RecipeDetailsServiceTest extends AbstractTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(RecipeDetailsServiceTest.class);

	@Inject
	private IRecipeDetailsService recipeDetailsService;

	@Inject
	private IUserService userService;

	@Inject
	private IRecipeService recipeService;

	@Inject
	private IIngredientService ingredientService;

	private RecipeDetails newDetails;

	private Ingredient ingredient;

	private Recipe recipe;

	private User userForRecipe;

	private Integer recipeAuthorId;

	private Integer recipeId;

	private Integer ingredientId;

	private Integer ingredientId2;

	private Integer savedDetailsId;

	private Integer savedDetailsId2;

	@Before
	public void createEntity() {
		userForRecipe = createEntity(new User());
		userService.save(userForRecipe);
		recipeAuthorId = userForRecipe.getId();

		recipe = createEntity(new Recipe(), recipeAuthorId);
		recipeService.save(recipe);
		recipeId = recipe.getId();

		ingredient = createEntity(new Ingredient());
		ingredientService.save(ingredient);
		ingredientId = ingredient.getId();

		newDetails = createEntity(new RecipeDetails(), recipeId, ingredientId);
		recipeDetailsService.save(newDetails);
		savedDetailsId = newDetails.getId();
	}

	@After
	public void deleteEntity() {
		if (recipeDetailsService.get(savedDetailsId) != null) {
			recipeDetailsService.delete(savedDetailsId);
		}
		if (recipeDetailsService.get(savedDetailsId2) != null) {
			recipeDetailsService.delete(savedDetailsId2);
		}
		if (ingredientService.get(ingredientId) != null) {
			ingredientService.delete(ingredientId);
		}
		if (ingredientService.get(ingredientId2) != null) {
			ingredientService.delete(ingredientId2);
		}
		recipeService.delete(recipeId);
		userService.delete(recipeAuthorId);

	}

	@Test
	public void getTest() {
		LOGGER.debug("RecipeDetails getTest");
		RecipeDetails detailsFromDb = recipeDetailsService.get(savedDetailsId);
		Assert.notNull(detailsFromDb, "Recipe Details must be exist");
		Assert.isTrue(!hasEmptyField(detailsFromDb), "Recipe Details should'nt have empty columns");
	}

	@Test
	public void insertTest() {
		LOGGER.debug("RecipeDetails insertTest");
		RecipeDetails newDetails2 = createEntity(new RecipeDetails(), recipeId, ingredientId);
		recipeDetailsService.save(newDetails2);
		savedDetailsId2 = newDetails2.getId();
		RecipeDetails detailsFromDb = recipeDetailsService.get(savedDetailsId2);
		Assert.isTrue(newDetails2.equals(detailsFromDb), "Recipes should be equals");
		Assert.isTrue(!hasEmptyField(detailsFromDb), "Recipe should'nt have empty columns");
	}

	@Test
	public void updateTest() {
		LOGGER.debug("RecipeDetails updateTest");
		RecipeDetails updatingDetails = newDetails;
		Ingredient ingredient2 = createEntity(new Ingredient());
		ingredientService.save(ingredient2);
		ingredientId2 = ingredient2.getId();
		updatingDetails.setIngredientId(ingredientId2);
		updatingDetails.setQuantity((int) (Math.random() * 500));
		recipeDetailsService.save(updatingDetails);
		RecipeDetails detailsFromDb = recipeDetailsService.get(savedDetailsId);
		Assert.isTrue(newDetails.getId().equals(detailsFromDb.getId()), "ID columns must be equals");
		Assert.isTrue(newDetails.getRecipeId().equals(detailsFromDb.getRecipeId()), "RecipeId columns must be equals");
		Assert.isTrue(!newDetails.getIngredientId().equals(detailsFromDb.getIngredientId()),
				"IngredientId columns must be not equals");
		Assert.isTrue(!newDetails.getQuantity().equals(detailsFromDb.getQuantity()),
				"Quantity columns must be not equals");
	}

	/*
	 * @Test public void saveMultipleTest(){
	 * LOGGER.info("RecipeDetails saveMultipleTest");
	 * recipeDetailsService.delete(savedDetailsId); RecipeDetails newDetails =
	 * createEntity(new RecipeDetails(), recipeId, ingredientId); Ingredient
	 * ingredient2 = createEntity(new Ingredient());
	 * ingredientService.save(ingredient2); ingredientId2 = ingredient2.getId();
	 * RecipeDetails newDetails2 = createEntity(new RecipeDetails(), recipeId,
	 * ingredientId2); recipeDetailsService.saveMultiple(newDetails,
	 * newDetails2); savedDetailsId = newDetails.getId(); savedDetailsId2 =
	 * newDetails2.getId();
	 * 
	 * RecipeDetails detailsFromDb = recipeDetailsService.get(savedDetailsId);
	 * RecipeDetails detailsFromDb2 = recipeDetailsService.get(savedDetailsId2);
	 * System.out.println(detailsFromDb.toString());
	 * System.out.println(detailsFromDb2.toString());
	 * Assert.notNull(detailsFromDb, "!!!");
	 * Assert.notNull(detailsFromDb2,"___"); }
	 */

	@Test
	public void deleteTest() {
		LOGGER.debug("RecipeDetails deleteTest");
		recipeDetailsService.delete(savedDetailsId);
		RecipeDetails detailsFromDb = recipeDetailsService.get(savedDetailsId);
		Assert.isNull(detailsFromDb, "RecipeDetails must not exist");
	}

	@Test
	public void clearDetailsTest() {
		LOGGER.debug("RecipeDetails clearDetailsTest");
		Ingredient ingredient2 = createEntity(new Ingredient());
		ingredientService.save(ingredient2);
		ingredientId2 = ingredient2.getId();
		RecipeDetails newDetails2 = createEntity(new RecipeDetails(), recipeId, ingredientId2);
		recipeDetailsService.save(newDetails2);
		savedDetailsId2 = newDetails2.getId();
		recipeDetailsService.clearRecipeDetails(recipeId);
		Assert.isTrue(recipeDetailsService.getWithDetails(recipeId).isEmpty(),
				"Details of Recipe ID=" + recipeId + " must not exist");
	}

	@Test
	public void getRecipeWithDetailsTest() {
		LOGGER.debug("RecipeDetails getRecipeWithDetailsTest");
		Ingredient ingredient2 = createEntity(new Ingredient());
		ingredientService.save(ingredient2);
		ingredientId2 = ingredient2.getId();
		RecipeDetails newDetails2 = createEntity(new RecipeDetails(), recipeId, ingredientId2);
		recipeDetailsService.save(newDetails2);
		savedDetailsId2 = newDetails2.getId();
		List<RecipeWithDetails> recipeWithDetails = recipeDetailsService.getWithDetails(recipeId);
		Assert.notEmpty(recipeWithDetails, "List of Details should'nt be empty");
		for (RecipeWithDetails details : recipeWithDetails) {
			Assert.notNull(details.getRecipe(), "Recipe Details must exist");
			Assert.isTrue(details.getRecipe().getId().equals(recipeId), "Recipe ID must be equals");
			Assert.notNull(details.getIngredient(), "Ingredient Details must exist");
			Assert.isTrue(details.getIngredient().getId().equals(ingredientId)
					|| details.getIngredient().getId().equals(ingredientId2), "Ingredient ID must be equals");
			Assert.notNull(details.getQuantity(), "Quantity column must exist");
		}

	}

	@Test
	public void getRecipeWeight() {
		LOGGER.debug("RecipeDetails getRecipeWeight");
		Ingredient ingredient2 = createEntity(new Ingredient());
		ingredientService.save(ingredient2);
		ingredientId2 = ingredient2.getId();
		RecipeDetails newDetails2 = createEntity(new RecipeDetails(), recipeId, ingredientId2);
		recipeDetailsService.save(newDetails2);
		savedDetailsId2 = newDetails2.getId();
		Integer recipeWeight = recipeDetailsService.getRecipeWeight(recipeId);
		Assert.isTrue(recipeWeight == (newDetails.getQuantity() + newDetails2.getQuantity()),
				"RecipeWeight should be equal to sum of Quantity columns");
	}

	@Test
	public void getAverageValues() {
		LOGGER.debug("RecipeDetails getAverageValues");
		Ingredient ingredient2 = createEntity(new Ingredient());
		ingredientService.save(ingredient2);
		ingredientId2 = ingredient2.getId();
		RecipeDetails newDetails2 = createEntity(new RecipeDetails(), recipeId, ingredientId2);
		recipeDetailsService.save(newDetails2);
		savedDetailsId2 = newDetails2.getId();
		AverageRecipeValues values = recipeDetailsService.getAvgValues(recipeId);
		Assert.isTrue(
				values.getCalories() == ((ingredient.getCalories() * newDetails.getQuantity())
						+ (ingredient2.getCalories() * newDetails2.getQuantity()))
						/ (newDetails.getQuantity() + newDetails2.getQuantity()),
				"Caloric value of dish should be equal to average calories of ingredients");
		BigDecimal avgFats = new BigDecimal(((ingredient.getFats() * newDetails.getQuantity())
				+ (ingredient2.getFats() * newDetails2.getQuantity()))
				/ (newDetails.getQuantity() + newDetails2.getQuantity()));
		Assert.isTrue(values.getFats().equals(avgFats.setScale(3, RoundingMode.HALF_EVEN)),
				"Fats value of dish should be equal to average fats of ingredients");
		BigDecimal avgProteins = new BigDecimal(((ingredient.getProteins() * newDetails.getQuantity())
				+ (ingredient2.getProteins() * newDetails2.getQuantity()))
				/ (newDetails.getQuantity() + newDetails2.getQuantity()));
		Assert.isTrue(values.getProteins().equals(avgProteins.setScale(3, RoundingMode.HALF_EVEN)),
				"Proteins value of dish should be equal to average proteins of ingredients");
		BigDecimal avgCarbohydrates = new BigDecimal(((ingredient.getCarbohydrates() * newDetails.getQuantity())
				+ (ingredient2.getCarbohydrates() * newDetails2.getQuantity()))
				/ (newDetails.getQuantity() + newDetails2.getQuantity()));
		Assert.isTrue(values.getCarbohydrates().equals(avgCarbohydrates.setScale(3, RoundingMode.HALF_EVEN)),
				"Carbohydrates value of dish should be equal to average carbohydrates of ingredients");

	}

}
