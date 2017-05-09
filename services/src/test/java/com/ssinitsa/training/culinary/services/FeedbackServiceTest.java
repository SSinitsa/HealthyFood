package com.ssinitsa.training.culinary.services;

import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.ssinitsa.training.culinary.datamodel.Feedback;
import com.ssinitsa.training.culinary.datamodel.Recipe;
import com.ssinitsa.training.culinary.datamodel.RecipeWithFeedback;
import com.ssinitsa.training.culinary.datamodel.User;

public class FeedbackServiceTest extends AbstractTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackServiceTest.class);

	@Inject
	private IFeedbackService feedbackService;

	@Inject
	private IRecipeService recipeService;

	@Inject
	private IUserService userService;

	private User userForRecipe;

	private User userForFeedback;

	private Recipe recipe;

	private Feedback newFeedback;

	private Integer userForRecipeId;

	private Integer recipeId;

	private Integer userForFeedbackId;

	private Integer userForFeedbackId2;

	private Integer savedFeedbackId;

	private Integer savedFeedbackId2;

	@Before
	public void createEntity() {
		userForRecipe = createEntity(new User());
		userService.save(userForRecipe);
		userForRecipeId = userForRecipe.getId();

		recipe = createEntity(new Recipe(), userForRecipeId);
		recipeService.save(recipe);
		recipeId = recipe.getId();

		userForFeedback = createEntity(new User());
		userService.save(userForFeedback);
		userForFeedbackId = userForFeedback.getId();

		newFeedback = createEntity(new Feedback(), userForFeedbackId, recipeId);
		feedbackService.save(newFeedback);
		savedFeedbackId = newFeedback.getId();
	}

	@After
	public void deleteEntity() {
		if (feedbackService.get(savedFeedbackId) != null) {
			feedbackService.delete(savedFeedbackId);
		}
		if (feedbackService.get(savedFeedbackId2) != null) {
			feedbackService.delete(savedFeedbackId2);
		}
		if (userService.get(userForFeedbackId) != null) {
			userService.delete(userForFeedbackId);
		}
		if (userService.get(userForFeedbackId2) != null) {
			userService.delete(userForFeedbackId2);
		}
		recipeService.delete(recipeId);
		userService.delete(userForRecipeId);
	}

	@Test
	public void getTest() {
		LOGGER.debug("Feedback getTest");
		Feedback feedbackFromDb = feedbackService.get(savedFeedbackId);
		Assert.notNull(feedbackFromDb, "Feedback must be exist");
		Assert.isTrue(!hasEmptyField(feedbackFromDb), "Feedback should'nt have empty columns");
	}

	@Test
	public void insertTest() {
		LOGGER.debug("Feedback insertTest");
		Feedback newFeedback2 = createEntity(new Feedback(), userForFeedbackId, recipeId);
		feedbackService.save(newFeedback2);
		savedFeedbackId2 = newFeedback2.getId();
		Feedback feedbackFromDb = feedbackService.get(savedFeedbackId2);
		Assert.isTrue(newFeedback2.equals(feedbackFromDb), "Feedback should be equals");
		Assert.isTrue(!hasEmptyField(feedbackFromDb), "Feedback should'nt have empty columns");
	}

	@Test
	public void updateTest() {
		LOGGER.debug("Feedback updateTest");
		Feedback updatingFeedback = new Feedback();
		updatingFeedback.setId(savedFeedbackId);
		updatingFeedback.setText("updated text");
		updatingFeedback.setUserId(userForFeedbackId);
		updatingFeedback.setRecipeId(recipeId);
		updatingFeedback.setCreated(newFeedback.getCreated());
		feedbackService.save(updatingFeedback);
		Feedback feedbackFromDb = feedbackService.get(savedFeedbackId);
		Assert.isTrue(newFeedback.getId().equals(feedbackFromDb.getId()), "ID columns must be equals");
		Assert.isTrue(newFeedback.getRecipeId().equals(feedbackFromDb.getRecipeId()),
				"RecipeId columns must be equals");
		Assert.isTrue(newFeedback.getUserId().equals(feedbackFromDb.getUserId()), "UserId columns must be not equals");
		Assert.isTrue(newFeedback.getCreated().equals(feedbackFromDb.getCreated()), "Created columns must be equals");
		Assert.isTrue(!newFeedback.getText().equals(feedbackFromDb.getText()), "Text columns must be not equals");
	}

	@Test
	public void deleteTest() {
		LOGGER.debug("Feedback deleteTest");
		feedbackService.delete(savedFeedbackId);
		Feedback feedbackFromDb = feedbackService.get(savedFeedbackId);
		Assert.isNull(feedbackFromDb, "RecipeDetails must not exist");
	}

	@Test
	public void getRecipeWithFedbackTest() {
		LOGGER.debug("Feedback getRecipeWithFedbackTest");
		User userForFeedback2 = createEntity(new User());
		userService.save(userForFeedback2);
		userForFeedbackId2 = userForFeedback2.getId();
		Feedback newFeedback2 = createEntity(new Feedback(), userForFeedbackId2, recipeId);
		feedbackService.save(newFeedback2);
		savedFeedbackId2 = newFeedback2.getId();
		List<RecipeWithFeedback> recipeWithFeedbacks = feedbackService.getRecipeWithFedback(recipeId);
		Assert.notEmpty(recipeWithFeedbacks, "Feedback must exist");
		for (RecipeWithFeedback recipeWithFeedback : recipeWithFeedbacks) {
			Assert.isTrue(recipeWithFeedback.getRecipe().getId().equals(recipeId), "Feedback ID must be equals");
			Assert.isTrue(recipeWithFeedback.getUser().getId().equals(userForFeedbackId)
					|| recipeWithFeedback.getUser().getId().equals(userForFeedbackId2), "User ID must be equals");
			Assert.isTrue(recipeWithFeedback.getText().equals(newFeedback.getText())
					|| recipeWithFeedback.getText().equals(newFeedback2.getText()), "Text columns must be equals");
		}

	}
}
