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
import com.ssinitsa.training.culinary.datamodel.UserRole;
import com.ssinitsa.training.culinary.services.AbstractTest;
import com.ssinitsa.training.culinary.services.IRecipeService;
import com.ssinitsa.training.culinary.services.IUserService;

public class CreateRecipeServiceTest extends AbstractTest {

	@Inject
	private IRecipeService service;

	@Inject
	private IUserService serviceUser;

	private Integer savedUserId;

	private Integer savedRecipeId;

	@Before
	public void createUserTest() {
		User user = new User();
		user.setLogin("new login");
		user.setPassword("new password");
		user.setRegistrated(new Timestamp(new Date().getTime()));
		user.setFirstName("new FN");
		user.setLastName("new LN");
		user.setEmail("new e-mail");
		user.setRole(UserRole.admin);
		serviceUser.save(user);
		savedUserId = user.getId();
		System.out.println(serviceUser.get(savedUserId).toString());

	}

	@After
	public void deleteCreatedRecipe() {
		//service.delete(savedRecipeId);
		//serviceUser.delete(savedUserId);
	}

	@Test
	public void createTest() {
		Recipe recipe = new Recipe();
		recipe.setName("new recipe");
		recipe.setDescription("something");
		recipe.setAuthorId(savedUserId);
		recipe.setCreated(new Timestamp(new Date().getTime()));
		recipe.setCategory(DishCategory.breakfast);
		recipe.setVegetarian(true);
		service.save(recipe);

		savedRecipeId = recipe.getId();
		Recipe recipeFromDb = service.get(savedRecipeId);
		System.out.println(recipeFromDb.toString());

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
