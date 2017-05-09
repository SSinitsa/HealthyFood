package com.ssinitsa.training.culinary.services.feedback;

import java.sql.Timestamp;
import java.util.Date;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import com.ssinitsa.training.culinary.datamodel.Feedback;
import com.ssinitsa.training.culinary.datamodel.Recipe;
import com.ssinitsa.training.culinary.datamodel.User;
import com.ssinitsa.training.culinary.services.AbstractTest;
import com.ssinitsa.training.culinary.services.IFeedbackService;
import com.ssinitsa.training.culinary.services.IRecipeService;
import com.ssinitsa.training.culinary.services.IUserService;
import com.ssinitsa.training.culinary.services.recipe.BeforeRecipeServiceTest;
import com.ssinitsa.training.culinary.services.user.BeforeUserServiceTest;

public class CreateFeedbackServiceTest extends AbstractTest {

	@Inject
	private IFeedbackService serviceFeedback;
	
	@Inject
	private IUserService serviceUser;

	@Inject
	private IRecipeService serviceRecipe;

	private Integer savedAuthorId;
	
	private Integer savedUserId;

	private Integer savedRecipeId;
	
	private Integer savedFeedbackId;

	@Before
	public void createRecipeAndIngredientTest() {
		BeforeUserServiceTest beforeUser = new BeforeUserServiceTest();
		
		User newAuthor = beforeUser.createUser();
		serviceUser.save(newAuthor);
		savedAuthorId = newAuthor.getId();
		System.out.println(newAuthor.toString());
		
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
		serviceFeedback.delete(savedFeedbackId);
		serviceRecipe.delete(savedRecipeId);
		serviceUser.delete(savedAuthorId);
		serviceUser.delete(savedUserId);
	}

	@Test
	public void createTest() {
		Feedback feedback = new Feedback();
		feedback.setText("bla-bla-bla");
		feedback.setUserId(savedAuthorId);
		feedback.setRecipeId(savedRecipeId);
		feedback.setCreated(new Timestamp(new Date().getTime()));
		serviceFeedback.save(feedback);
		System.out.println(feedback.toString());
		
		
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
