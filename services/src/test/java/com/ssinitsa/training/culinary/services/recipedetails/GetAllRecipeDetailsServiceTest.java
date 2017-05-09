package com.ssinitsa.training.culinary.services.recipedetails;

import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

public class GetAllRecipeDetailsServiceTest extends AbstractTest {

	@Inject
	private IUserService serviceUser;

	@Inject
	private IRecipeService serviceRecipe;

	@Inject
	private IIngredientService serviceIngredient;

	@Inject
	private IRecipeDetailsService serviceRecipeDetails;

	private Integer savedUserId;
	private Integer savedRecipeId;
	private Integer savedIngredientId;
	private Integer savedIngredientId2;
	private Integer savedRecipeDetailsId;
	private Integer savedRecipeDetailsId2;

	/*@Before
	public void createRecipeDetails() {

		BeforeUserServiceTest beforeUser = new BeforeUserServiceTest();
		User newUser = beforeUser.createUser();
		serviceUser.save(newUser);
		savedUserId = newUser.getId();

		BeforeRecipeServiceTest beforeRecipe = new BeforeRecipeServiceTest();
		Recipe newRecipe = beforeRecipe.createRecipe(savedUserId);
		serviceRecipe.save(newRecipe);
		savedRecipeId = newRecipe.getId();

		BeforeIngredientServiceTest beforeIngredient = new BeforeIngredientServiceTest();
		Ingredient newIngredient = beforeIngredient.createIngredient();
		serviceIngredient.save(newIngredient);
		savedIngredientId = newIngredient.getId();
		Ingredient newIngredient2 = beforeIngredient.createIngredient();
		serviceIngredient.save(newIngredient2);
		savedIngredientId2 = newIngredient2.getId();
		
		

		BeforeRecipeDetailsServiceTest beforeRecipeDetails = new BeforeRecipeDetailsServiceTest();
		RecipeDetails newRecipeDetails = beforeRecipeDetails.createRecipeDetails(savedRecipeId, savedIngredientId);
		serviceRecipeDetails.save(newRecipeDetails);
		savedRecipeDetailsId = newRecipeDetails.getId();
		System.out.println(serviceRecipeDetails.get(savedRecipeDetailsId).toString());
		RecipeDetails newRecipeDetails2 = beforeRecipeDetails.createRecipeDetails(savedRecipeId, savedIngredientId2);
		serviceRecipeDetails.save(newRecipeDetails2);
		savedRecipeDetailsId2 = newRecipeDetails2.getId();
		System.out.println(serviceRecipeDetails.get(savedRecipeDetailsId).toString());
		System.out.println(serviceRecipeDetails.get(savedRecipeDetailsId2).toString());

	}

	@After
	public void deleteAfterTest() {
		serviceRecipeDetails.delete(savedRecipeDetailsId);
		serviceRecipeDetails.delete(savedRecipeDetailsId2);
		serviceIngredient.delete(savedIngredientId);
		serviceIngredient.delete(savedIngredientId2);
		serviceRecipe.delete(savedRecipeId);
		serviceUser.delete(savedUserId);
	}*/

	@Test
	public void getAllRecipeDetailsTest() {
		//List <RecipeDetails> recipeDetails = serviceRecipeDetails.getAll();
		//System.out.println(recipeDetails);
		System.out.println(serviceRecipeDetails.getAvgValues(46).toString());
		
		
	}

}
