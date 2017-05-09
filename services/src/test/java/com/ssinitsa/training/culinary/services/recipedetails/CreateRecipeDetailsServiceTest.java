package com.ssinitsa.training.culinary.services.recipedetails;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import com.ssinitsa.training.culinary.datamodel.Ingredient;
import com.ssinitsa.training.culinary.datamodel.Recipe;
import com.ssinitsa.training.culinary.datamodel.RecipeDetails;
import com.ssinitsa.training.culinary.datamodel.User;
import com.ssinitsa.training.culinary.services.AbstractTest;
import com.ssinitsa.training.culinary.services.IIngredientService;
import com.ssinitsa.training.culinary.services.IRecipeDetailsService;
import com.ssinitsa.training.culinary.services.IRecipeService;
import com.ssinitsa.training.culinary.services.IUserService;
import com.ssinitsa.training.culinary.services.ingredient.BeforeIngredientServiceTest;
import com.ssinitsa.training.culinary.services.recipe.BeforeRecipeServiceTest;
import com.ssinitsa.training.culinary.services.user.BeforeUserServiceTest;

public class CreateRecipeDetailsServiceTest extends AbstractTest {

	@Inject
	private IUserService serviceUser;

	@Inject
	private IRecipeDetailsService serviceRecipeDetails;

	@Inject
	private IRecipeService serviceRecipe;

	@Inject
	private IIngredientService serviceIngredient;

	private Integer savedUserId;

	private Integer savedRecipeDetailsId;

	private Integer savedRecipeId;

	private Integer savedIngredientId;

	@Before
	public void createRecipeAndIngredientTest() {
		BeforeUserServiceTest beforeUser = new BeforeUserServiceTest();
		User newUser = beforeUser.createUser();
		serviceUser.save(newUser);
		savedUserId = newUser.getId();
		System.out.println(newUser.toString());
		BeforeRecipeServiceTest beforeRecipe = new BeforeRecipeServiceTest();
		Recipe newRecipe = beforeRecipe.createRecipe(savedUserId);
		serviceRecipe.save(newRecipe);
		savedRecipeId = newRecipe.getId();

		BeforeIngredientServiceTest beforeIngredient = new BeforeIngredientServiceTest();
		Ingredient newIngredient = beforeIngredient.createIngredient();
		serviceIngredient.save(newIngredient);
		savedIngredientId = newIngredient.getId();

	}

	@After
	public void deleteCreatedRecipe() {
		// service.delete(savedRecipeId);
		// serviceUser.delete(savedUserId);
	}

	@Test
	public void createTest() {
		RecipeDetails recipeDetails = new RecipeDetails();
		recipeDetails.setRecipeId(savedRecipeId);
		recipeDetails.setIngredientId(savedIngredientId);
		recipeDetails.setQuantity(100);
		serviceRecipeDetails.save(recipeDetails);
		
		
		/*
		 * Assert.notNull(recipeFromDb.getName(),
		 * "name column must not me empty");
		 * 
		 * Assert.isTrue(recipeFromDb.getCalories().equals(recipe.getCalories())
		 * , "calories must be equals");
		 * 
		 * Assert.notNull(ingredientFromDb.getFats(),
		 * "fats column must not me empty");
		 * 
		 * Assert.isTrue(ingredientFromDb.getFats() == (recipe.getFats()),
		 * "fats must be equals");
		 * 
		 * Assert.notNull(ingredientFromDb.getProteins(),
		 * "proteins column must not me empty");
		 * 
		 * Assert.isTrue(ingredientFromDb.getProteins() ==
		 * (recipe.getProteins()), "proteins must be equals");
		 * 
		 * Assert.notNull(ingredientFromDb.getCarbohydrates(),
		 * "carbohydrates column must not me empty");
		 * 
		 * Assert.isTrue(ingredientFromDb.getCarbohydrates() ==
		 * (recipe.getCarbohydrates()), "carbohydrates must be equals");
		 * 
		 * Assert.notNull(ingredientFromDb.getCategory(),
		 * "category column must not me empty");
		 * 
		 * Assert.isTrue(ingredientFromDb.getCategory().equals(recipe.
		 * getCategory()), "category must be equals");
		 */

	}

}
