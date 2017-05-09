package com.ssinitsa.training.culinary.services;

import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.ssinitsa.training.culinary.dao.api.filter.RecipeFilter;
import com.ssinitsa.training.culinary.datamodel.DishCategory;
import com.ssinitsa.training.culinary.datamodel.Recipe;
import com.ssinitsa.training.culinary.datamodel.User;

public class RecipeServiceTest extends AbstractTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(RecipeServiceTest.class);

	@Inject
	private IRecipeService recipeService;

	@Inject
	private IUserService userService;

	private Recipe newRecipe;

	private Integer savedRecipeId;

	private Integer savedRecipeId2;

	private Integer recipeAuthorId;

	@Before
	public void createEntity() {
		User userForRecipe = createEntity(new User());
		userService.save(userForRecipe);
		recipeAuthorId = userForRecipe.getId();
		
		newRecipe = createEntity(new Recipe(), recipeAuthorId);
		recipeService.save(newRecipe);
		savedRecipeId = newRecipe.getId();
	}
	
	@After
	public void deleteEntity(){
		if (recipeService.get(savedRecipeId) != null) {
			recipeService.delete(savedRecipeId);
		}
		if (recipeService.get(savedRecipeId2) != null) {
			recipeService.delete(savedRecipeId2);
		}
		userService.delete(recipeAuthorId);
	}

	@Test
	public void getTest() {
		LOGGER.debug("Recipe getTest");
		Recipe recipeFromDb = recipeService.get(savedRecipeId);
		Assert.notNull(recipeFromDb, "Recipe must be exist");
		Assert.isTrue(!hasEmptyField(recipeFromDb), "Recipe should'nt have empty columns");
	}

	@Test
	public void getAllTest() {
		LOGGER.debug("Recipe getAllTest");
		Recipe newRecipe2 = createEntity(new Recipe(), recipeAuthorId);
		recipeService.save(newRecipe2);
		savedRecipeId2 = newRecipe2.getId();
		List<Recipe> recipes = recipeService.getAll();
		Assert.notEmpty(recipes, "List of Recipes should'nt be empty");
		Assert.isTrue(recipes.size() > 1, "The number of Recipes should be more than 1");
	}

	@Test
	public void insertTest() {
		LOGGER.debug("Recipe insertTest");
		Recipe newRecipe2 = createEntity(new Recipe(), recipeAuthorId);
		recipeService.save(newRecipe2);
		savedRecipeId2 = newRecipe2.getId();
		Recipe recipeFromDb = recipeService.get(savedRecipeId2);
		Assert.isTrue(newRecipe2.equals(recipeFromDb), "Recipes should be equals");
		Assert.isTrue(!hasEmptyField(recipeFromDb), "Recipe should'nt have empty columns");
	}

	@Test
	public void updateTest() {
		LOGGER.debug("Recipe updateTest");
		Recipe updatingRecipe = createEntity(new Recipe(), recipeAuthorId);
		updatingRecipe.setId(savedRecipeId);
		updatingRecipe.setCreated(newRecipe.getCreated());
		updatingRecipe.setCategory(DishCategory.breakfast);
		updatingRecipe.setVegetarian(false);
		recipeService.save(updatingRecipe);
		Recipe recipeFromDb = recipeService.get(savedRecipeId);
		Assert.isTrue(newRecipe.getId().equals(recipeFromDb.getId()), "ID columns must be equals");
		Assert.isTrue(newRecipe.getAuthorId().equals(recipeFromDb.getAuthorId()),"AuthorId columns must be equals");
		Assert.isTrue(newRecipe.getCreated().equals(recipeFromDb.getCreated()),"Created columns must be equals");
		Assert.isTrue(!newRecipe.getCategory().equals(recipeFromDb.getCategory()),"Category columns must be not equals");
		Assert.isTrue(!newRecipe.getDescription().equals(recipeFromDb.getDescription()),"Description columns must be not equals");
		Assert.isTrue(!newRecipe.getName().equals(recipeFromDb.getName()),"Name columns must be not equals");
		Assert.isTrue(!newRecipe.isVegetarian().equals(recipeFromDb.isVegetarian()),"Vegetarian columns must be not equals");
	}
	
	@Test
	public void deleteTest(){
		LOGGER.debug("Recipe deleteTest");
		recipeService.delete(savedRecipeId);
		Recipe recipeFromDb = recipeService.get(savedRecipeId);
		Assert.isNull(recipeFromDb, "Recipe must not exist");
	}
	
	@Test
	public void searchByCategoryTest(){
		LOGGER.debug("Recipe searchByCategoryTest");
		Recipe newRecipe2 = createEntity(new Recipe(), recipeAuthorId);
		newRecipe2.setCategory(DishCategory.breakfast);
		recipeService.save(newRecipe2);
		savedRecipeId2 = newRecipe2.getId();
		RecipeFilter filter = new RecipeFilter();
		filter.setCategory("breakfast");
		List<Recipe> filteredRecipes = recipeService.search(filter);
		for (Recipe recipe : filteredRecipes){
			Assert.isTrue(recipe.getCategory().name().equals("breakfast"), "All categories must be \"breakfast\"");
		}
		
	}
}
