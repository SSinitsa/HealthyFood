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

import com.ssinitsa.training.culinary.datamodel.Rating;
import com.ssinitsa.training.culinary.datamodel.Recipe;
import com.ssinitsa.training.culinary.datamodel.RecipeWithRating;
import com.ssinitsa.training.culinary.datamodel.User;

public class RatingServiceTest extends AbstractTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(RatingServiceTest.class);

	@Inject
	private IRatingService ratingService;

	@Inject
	private IRecipeService recipeService;

	@Inject
	private IUserService userService;

	private User userForRecipe;

	private Integer userForRecipeId;

	private Recipe recipe;

	private Integer recipeId;

	private User userForRating;

	private Integer userForRatingId;

	private Integer userForRatingId2;

	private Rating newRating;

	private Integer savedRatingId;

	private Integer savedRatingId2;

	@Before
	public void createEntity() {
		userForRecipe = createEntity(new User());
		userService.save(userForRecipe);
		userForRecipeId = userForRecipe.getId();

		recipe = createEntity(new Recipe(), userForRecipeId);
		recipeService.save(recipe);
		recipeId = recipe.getId();

		userForRating = createEntity(new User());
		userService.save(userForRating);
		userForRatingId = userForRating.getId();

		newRating = createEntity(new Rating(), userForRatingId, recipeId);
		ratingService.save(newRating);
		savedRatingId = newRating.getId();
	}

	@After
	public void deleteEntity() {
		if (ratingService.get(savedRatingId) != null) {
			ratingService.delete(savedRatingId);
		}
		if (ratingService.get(savedRatingId2) != null) {
			ratingService.delete(savedRatingId2);
		}
		if (userService.get(userForRatingId) != null) {
			userService.delete(userForRatingId);
		}
		if (userService.get(userForRatingId2) != null) {
			userService.delete(userForRatingId2);
		}
		recipeService.delete(recipeId);
		userService.delete(userForRecipeId);
	}

	@Test
	public void getTest() {
		LOGGER.debug("Rating getTest");
		Rating ratingFromDb = ratingService.get(savedRatingId);
		Assert.notNull(ratingFromDb, "Rating must be exist");
		Assert.isTrue(!hasEmptyField(ratingFromDb), "Rating should'nt have empty columns");
	}

	@Test
	public void insertTest() {
		LOGGER.debug("Rating insertTest");
		Rating newRating2 = createEntity(new Rating(), userForRatingId, recipeId);
		ratingService.save(newRating2);
		savedRatingId2 = newRating2.getId();
		Rating ratingFromDb = ratingService.get(savedRatingId2);
		Assert.isTrue(newRating2.equals(ratingFromDb), "Rating should be equals");
		Assert.isTrue(!hasEmptyField(ratingFromDb), "Rating should'nt have empty columns");
	}

	@Test
	public void updateTest() {
		LOGGER.debug("Rating updateTest");
		Rating updatingRating = new Rating();
		updatingRating.setId(savedRatingId);
		updatingRating.setUserId(userForRatingId);
		updatingRating.setRecipeId(recipeId);
		updatingRating.setVote(newRating.getVote() + 1);
		ratingService.save(updatingRating);
		Rating ratingFromDb = ratingService.get(savedRatingId);
		Assert.isTrue(newRating.getId().equals(ratingFromDb.getId()), "ID columns must be equals");
		Assert.isTrue(newRating.getRecipeId().equals(ratingFromDb.getRecipeId()), "RecipeId columns must be equals");
		Assert.isTrue(newRating.getUserId().equals(ratingFromDb.getUserId()), "UserId columns must be not equals");
		Assert.isTrue(!newRating.getVote().equals(ratingFromDb.getVote()), "Created columns must be equals");
	}

	@Test
	public void deleteTest() {
		LOGGER.debug("Rating deleteTest");
		ratingService.delete(savedRatingId);
		Rating ratingFromDb = ratingService.get(savedRatingId);
		Assert.isNull(ratingFromDb, "RecipeDetails must not exist");
	}

	@Test
	public void getRecipeWithRatingTest() {
		LOGGER.debug("Rating getRecipeWithRatingTest");
		User userForRating2 = createEntity(new User());
		userService.save(userForRating2);
		userForRatingId2 = userForRating2.getId();
		Rating newRating2 = createEntity(new Rating(), userForRatingId2, recipeId);
		ratingService.save(newRating2);
		savedRatingId2 = newRating2.getId();
		List<RecipeWithRating> recipeWithRatings = ratingService.getRecipeWithRating(recipeId);
		Assert.notEmpty(recipeWithRatings, "");
		for (RecipeWithRating recipeWithRating : recipeWithRatings) {
			Assert.isTrue(recipeWithRating.getRecipe().getId().equals(recipeId), "Recipe ID must be equals");
			Assert.isTrue(recipeWithRating.getUser().getId().equals(userForRatingId)
					|| recipeWithRating.getUser().getId().equals(userForRatingId2), "User ID must be equals");
			Assert.isTrue(recipeWithRating.getVote().equals(newRating.getVote())
					|| recipeWithRating.getVote().equals(newRating2.getVote()), "Vote column must be equals");
		}

	}

	@Test
	public void getAverageRating() {
		LOGGER.debug("Rating getAverageRating");
		User userForRating2 = createEntity(new User());
		userService.save(userForRating2);
		userForRatingId2 = userForRating2.getId();
		Rating newRating2 = createEntity(new Rating(), userForRatingId2, recipeId);
		ratingService.save(newRating2);
		savedRatingId2 = newRating2.getId();
		BigDecimal avgRating = ratingService.getAverageRating(recipeId);
		Double sumVotes = (double) ((newRating.getVote() + newRating2.getVote()));
		Assert.isTrue(avgRating.equals(new BigDecimal(sumVotes / 2).setScale(2, RoundingMode.HALF_EVEN)),
				"Incorrect value of the average rating");

	}
}
