package com.ssinitsa.training.culinary.services;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ssinitsa.training.culinary.datamodel.DishCategory;
import com.ssinitsa.training.culinary.datamodel.Feedback;
import com.ssinitsa.training.culinary.datamodel.Ingredient;
import com.ssinitsa.training.culinary.datamodel.IngredientCategory;
import com.ssinitsa.training.culinary.datamodel.Rating;
import com.ssinitsa.training.culinary.datamodel.Recipe;
import com.ssinitsa.training.culinary.datamodel.RecipeDetails;
import com.ssinitsa.training.culinary.datamodel.User;
import com.ssinitsa.training.culinary.datamodel.UserRole;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:services-context-test.xml")
public class AbstractTest {

	public Ingredient createEntity(Ingredient ingredient) {
		ingredient.setName("Ingredient" + (int) (Math.random() * 1000000));
		ingredient.setCalories((int) (Math.random() * 200));
		ingredient.setFats(BigDecimal.valueOf(Math.random() * 5).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
		ingredient.setProteins(
				BigDecimal.valueOf(Math.random() * 5).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
		ingredient.setCarbohydrates(
				BigDecimal.valueOf(Math.random() * 5).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue());
		ingredient.setCategory(IngredientCategory.fruit);
		return ingredient;
	}

	public User createEntity(User user) {
		user.setLogin("Login" + (int) (Math.random() * 1000000));
		user.setPassword("" + (int) (Math.random() * 100000000));
		user.setRegistrated(new Timestamp(new Date().getTime()));
		user.setFirstName("FName" + (Math.random() * 1000000));
		user.setLastName("LName" + (Math.random() * 1000000));
		user.setEmail("E-mail" + (Math.random() * 1000000));
		user.setRole(UserRole.user);
		return user;
	}

	public Recipe createEntity(Recipe recipe, Integer authorId) {
		recipe.setName("Recipe" + (int) (Math.random() * 1000000));
		recipe.setDescription("Description" + (int) (Math.random() * 1000000));
		recipe.setAuthorId(authorId);
		recipe.setCreated(new Timestamp(new Date().getTime()));
		recipe.setCategory(DishCategory.dessert);
		recipe.setVegetarian(true);
		return recipe;
	}

	public RecipeDetails createEntity(RecipeDetails recipeDetails, Integer recipeId, Integer ingredientId) {
		recipeDetails.setRecipeId(recipeId);
		recipeDetails.setIngredientId(ingredientId);
		recipeDetails.setQuantity((int) (Math.random() * 500));
		return recipeDetails;
	}

	public Feedback createEntity(Feedback feedback, Integer userId, Integer recipeId) {
		feedback.setText("Text" + (int) (Math.random() * 1000000));
		feedback.setUserId(userId);
		feedback.setRecipeId(recipeId);
		feedback.setCreated(new Timestamp(new Date().getTime()));
		return feedback;
	}

	public Rating createEntity(Rating rating, Integer userId, Integer recipeId) {
		rating.setUserId(userId);
		rating.setRecipeId(recipeId);
		rating.setVote(1+((int) (Math.random() * 4)));
		return rating;
	}

	public boolean hasEmptyField(Ingredient ingredient) {
		return ingredient.getId() == null || ingredient.getName() == null || ingredient.getCalories() == null
				|| ingredient.getFats() == null || ingredient.getProteins() == null
				|| ingredient.getCarbohydrates() == null || ingredient.getCategory() == null;
	}

	public boolean hasEmptyField(User user) {
		return user.getId() == null || user.getLogin() == null || user.getPassword() == null
				|| user.getRegistrated() == null || user.getFirstName() == null || user.getLastName() == null
				|| user.getEmail() == null || user.getRole() == null;
	}

	public boolean hasEmptyField(Recipe recipe) {
		return recipe.getId() == null || recipe.getName() == null || recipe.getDescription() == null
				|| recipe.getAuthorId() == null || recipe.getCreated() == null || recipe.getCategory() == null
				|| recipe.isVegetarian() == null;
	}

	public boolean hasEmptyField(RecipeDetails details) {
		return details.getId() == null || details.getRecipeId() == null || details.getIngredientId() == null
				|| details.getQuantity() == null;
	}

	public boolean hasEmptyField(Feedback feedback) {
		return feedback.getId() == null || feedback.getText() == null || feedback.getRecipeId() == null
				|| feedback.getUserId() == null || feedback.getCreated() == null;
	}

	public boolean hasEmptyField(Rating rating) {
		return rating.getId() == null || rating.getRecipeId() == null || rating.getUserId() == null
				|| rating.getVote() == null;
	}
}
