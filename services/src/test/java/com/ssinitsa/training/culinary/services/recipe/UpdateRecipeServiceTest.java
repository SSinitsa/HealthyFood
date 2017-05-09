package com.ssinitsa.training.culinary.services.recipe;

import java.sql.Timestamp;
import java.util.Date;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import com.ssinitsa.training.culinary.datamodel.DishCategory;
import com.ssinitsa.training.culinary.datamodel.Recipe;
import com.ssinitsa.training.culinary.datamodel.User;
import com.ssinitsa.training.culinary.services.AbstractTest;
import com.ssinitsa.training.culinary.services.IRecipeService;
import com.ssinitsa.training.culinary.services.IUserService;
import com.ssinitsa.training.culinary.services.user.BeforeUserServiceTest;

public class UpdateRecipeServiceTest extends AbstractTest {

	@Inject
	private IRecipeService serviceRecipe;

	@Inject
	private IUserService serviceUser;

	private Integer savedUserId;

	private Integer savedRecipeId;

	@Before
	public void createRecipeTest() {
		BeforeUserServiceTest beforeUser = new BeforeUserServiceTest();
		User newUser = beforeUser.createUser();
		serviceUser.save(newUser);
		savedUserId = newUser.getId();
		System.out.println(newUser.toString());
		BeforeRecipeServiceTest beforeRecipe = new BeforeRecipeServiceTest();
		Recipe newRecipe = beforeRecipe.createRecipe(savedUserId);
		serviceRecipe.save(newRecipe);
		savedRecipeId = newRecipe.getId();
	}

	@After
	public void deleteCreatedRecipe() {
		serviceRecipe.delete(savedRecipeId);
		serviceUser.delete(savedUserId);
	}

	@Test
	public void updateTest() {
		Recipe updatedRecipe = new Recipe();
		updatedRecipe.setName("2new recipe");
		updatedRecipe.setDescription("2something");
		updatedRecipe.setAuthorId(savedUserId);
		updatedRecipe.setCreated(new Timestamp(new Date().getTime()));
		updatedRecipe.setCategory(DishCategory.dessert);
		updatedRecipe.setVegetarian(false);
		updatedRecipe.setId(savedRecipeId);
		serviceRecipe.save(updatedRecipe);
		Recipe recipeFromDb = serviceRecipe.get(savedRecipeId);
		System.out.println(updatedRecipe.toString());

		Assert.notNull(recipeFromDb, "recipe must be saved");

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
