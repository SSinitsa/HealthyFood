package com.ssinitsa.training.culinary.services.recipe;

import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import com.ssinitsa.training.culinary.dao.api.filter.RecipeFilter;
import com.ssinitsa.training.culinary.datamodel.DishCategory;
import com.ssinitsa.training.culinary.datamodel.Recipe;
import com.ssinitsa.training.culinary.datamodel.RecipeWithDetails;
import com.ssinitsa.training.culinary.datamodel.User;
import com.ssinitsa.training.culinary.services.AbstractTest;
import com.ssinitsa.training.culinary.services.IRecipeService;
import com.ssinitsa.training.culinary.services.IUserService;
import com.ssinitsa.training.culinary.services.user.BeforeUserServiceTest;

public class GetAllRecipeServiceTest extends AbstractTest {

	@Inject
	private IRecipeService serviceRecipe;

	@Inject
	private IUserService serviceUser;

	private Integer savedUserId;
	private Integer savedUserId2;

	private Integer savedRecipeId;
	private Integer savedRecipeId2;

	/*@Before
	public void createRecipeTest() {
		
		BeforeUserServiceTest beforeUser = new BeforeUserServiceTest();
		
		User newUser = beforeUser.createUser();
		serviceUser.save(newUser);
		savedUserId = newUser.getId();
		System.out.println(newUser.toString());
		
		User newUser2 = beforeUser.createUser();
		serviceUser.save(newUser2);
		savedUserId2 = newUser2.getId();
		System.out.println(newUser2.toString());
		
		BeforeRecipeServiceTest beforeRecipe = new BeforeRecipeServiceTest();
		
		Recipe newRecipe = beforeRecipe.createRecipe(savedUserId);
		serviceRecipe.save(newRecipe);
		savedRecipeId = newRecipe.getId();
		
		Recipe newRecipe2 = beforeRecipe.createRecipe(savedUserId2);
		serviceRecipe.save(newRecipe2);
		savedRecipeId2 = newRecipe2.getId();
		
	}

	@After
	public void deleteCreatedRecipe() {
		serviceRecipe.delete(savedRecipeId);
		serviceUser.delete(savedUserId);
	}*/

	@Test
	public void updateTest() {
		//List <Recipe> recipes = serviceRecipe.getAll();
		//System.out.println(recipes);
		
		List<Recipe> allRecipes;
		RecipeFilter filter = new RecipeFilter();
		filter.setAuthorId(47);
		filter.setCategory(DishCategory.drink.name());
		filter.setVegetarian(true);
		
		System.out.println(filter.toString());

		allRecipes = serviceRecipe.search(filter);
		
		System.out.println(allRecipes);
	}

}
