package com.ssinitsa.training.culinary.webapp.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssinitsa.training.culinary.dao.api.filter.RecipeFilter;
import com.ssinitsa.training.culinary.datamodel.Recipe;
import com.ssinitsa.training.culinary.services.IRecipeDetailsService;
import com.ssinitsa.training.culinary.services.IRecipeService;
import com.ssinitsa.training.culinary.services.IUserService;
import com.ssinitsa.training.culinary.webapp.converters.Recipe2RecipeModel;
import com.ssinitsa.training.culinary.webapp.converters.RecipeModel2Recipe;
import com.ssinitsa.training.culinary.webapp.models.IdModel;
import com.ssinitsa.training.culinary.webapp.models.RecipeModel;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

	@Inject
	private IRecipeService recipeService;

	@Inject
	private IUserService userService;
	
	@Inject
	private IRecipeDetailsService recipeDetailService;
	
	@Inject
	private Recipe2RecipeModel entityConverter;
	
	@Inject
	private RecipeModel2Recipe modelConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll(@RequestParam(required = false) Integer author, String category, Boolean vegetarian,
			Integer ingredient, Integer from, Integer to, Integer page, Integer limit) {
		List<Recipe> allRecipes;
		RecipeFilter filter = new RecipeFilter();
		filter.setAuthorId(author);
		filter.setCategory(category);
		filter.setVegetarian(vegetarian);
		filter.setIngredientId(ingredient);
		allRecipes = recipeService.search(filter);
		List<RecipeModel> convertedRecipes = new ArrayList<>();
		for (Recipe recipe : allRecipes) {
			RecipeModel convertingRecipe = entityConverter.recipe2recipeModel(recipe);
			convertingRecipe.setAuthor(userService.get(convertingRecipe.getAuthorId()).getLogin());
			convertedRecipes.add(convertingRecipe);
		}
		if (from != null || to != null) {
			convertedRecipes = caloryFilter(convertedRecipes, from, to);
		}
		if (page != null && limit != null) {
			convertedRecipes = getPage(convertedRecipes, page, limit);
		}
		return new ResponseEntity<List<RecipeModel>>(convertedRecipes, HttpStatus.OK);
	}

	private List<RecipeModel> getPage(List<RecipeModel> convertedRecipes, Integer page, Integer limit) {
		List <RecipeModel> pageOfRecipes = new ArrayList<>();
		int from = (page - 1) * limit;
		int to = page * limit;
		if (to > convertedRecipes.size()) {
			to = convertedRecipes.size();
		}
		for (RecipeModel recipeModel : convertedRecipes.subList(from, to)) {
		pageOfRecipes.add(recipeModel);
	}
		return pageOfRecipes;
	}

	private List<RecipeModel> caloryFilter(List<RecipeModel> convertedRecipes, Integer caloryFrom, Integer caloryTo) {
		List<RecipeModel> filteredRecipes = new ArrayList<>();
		for (RecipeModel recipeModel : convertedRecipes){
			Integer avgCalories = recipeDetailService.getAvgValues(recipeModel.getId()).getCalories();
			if (avgCalories>=caloryFrom&&avgCalories<=caloryTo){
			filteredRecipes.add(recipeModel);
			}
		}
		return filteredRecipes;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable(value = "id") Integer ingredientIdParam) {
		Recipe recipe = recipeService.get(ingredientIdParam);
		RecipeModel recipeModel = entityConverter.recipe2recipeModel(recipe);
		recipeModel.setAuthor(userService.get(recipeModel.getAuthorId()).getLogin());
		System.out.println(recipeModel);
		return new ResponseEntity<RecipeModel>(recipeModel, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createRecipe(@RequestBody RecipeModel recipeModel) {
		Recipe recipe = modelConverter.recipeModel2recipe(recipeModel);
		recipe.setCreated(new Timestamp(new Date().getTime()));
		recipeService.save(recipe);
		return new ResponseEntity<IdModel>(new IdModel(recipe.getId()), HttpStatus.CREATED);
	}


	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateRecipe(@RequestBody RecipeModel recipeModel,
			@PathVariable(value = "id") Integer ingredientIdParam) {
		Recipe recipe = recipeService.get(ingredientIdParam);
		recipe.setName(recipeModel.getName());
		recipe.setDescription(recipeModel.getDescription());
		//recipe.setAuthorId(recipeModel.getAuthorId());
		recipe.setCategory(recipeModel.getCategory());
		recipe.setVegetarian(recipeModel.isVegetarian());
		recipeService.save(recipe);
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRecipe(@PathVariable(value = "id") Integer ingredientIdParam) {
		recipeService.delete(ingredientIdParam);
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

}
