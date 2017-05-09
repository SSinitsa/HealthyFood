package com.ssinitsa.training.culinary.webapp.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssinitsa.training.culinary.datamodel.RecipeDetails;
import com.ssinitsa.training.culinary.datamodel.RecipeWithDetails;
import com.ssinitsa.training.culinary.services.IRecipeDetailsService;
import com.ssinitsa.training.culinary.webapp.models.DetailsModel;
import com.ssinitsa.training.culinary.webapp.models.IdModel;
import com.ssinitsa.training.culinary.webapp.models.RecipeDetailsModel;
import com.ssinitsa.training.culinary.webapp.models.RecipeValuesModel;

import models.AverageRecipeValues;

@RestController
@RequestMapping("/details")
public class RecipeDetailsController {

	@Inject
	private IRecipeDetailsService recipeDetailsService;

	@RequestMapping(method = RequestMethod.GET)
	public HttpStatus getStatus() {
		return HttpStatus.NOT_FOUND;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByRecipeId(@PathVariable(value = "id") Integer recipeIdParam) {
		List<RecipeWithDetails> recipesWithDetails = recipeDetailsService.getWithDetails(recipeIdParam);
		List<DetailsModel> convertedRecipes = new ArrayList<>();
		for (RecipeWithDetails recipeWithDetails : recipesWithDetails) {
			convertedRecipes.add(entity2model(recipeWithDetails));
		}
		return new ResponseEntity<List<DetailsModel>>(convertedRecipes, HttpStatus.OK);
	}

	private DetailsModel entity2model(RecipeWithDetails recipeWithDetails) {
		DetailsModel recipeDetailsModel = new DetailsModel();
		recipeDetailsModel.setIngredient(recipeWithDetails.getIngredient().getName());
		recipeDetailsModel.setQuantity(recipeWithDetails.getQuantity());
		return recipeDetailsModel;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createRecipeDetails(@RequestBody RecipeDetailsModel detailsModel) {
		RecipeDetails recipeDetails = model2entity(detailsModel);
		recipeDetailsService.save(recipeDetails);
		return new ResponseEntity<IdModel>(new IdModel(recipeDetails.getId()), HttpStatus.CREATED);

	}
	
	@RequestMapping(value = "/multiple", method = RequestMethod.POST)
	public ResponseEntity<?> createAllRecipeDetails(@RequestBody List<RecipeDetailsModel> detailsModel) {
		List<IdModel> idModels = new ArrayList<>();
		for (Iterator<RecipeDetailsModel> i = detailsModel.iterator(); i.hasNext();) {
			RecipeDetailsModel model = i.next();
			RecipeDetails details = model2entity(model);
			recipeDetailsService.save(details);
			idModels.add(new IdModel(details.getId()));
		}
		return new ResponseEntity <List<IdModel>>(idModels, HttpStatus.CREATED);

	}
	

	private RecipeDetails model2entity(RecipeDetailsModel detailsModel) {
		RecipeDetails recipeDetails = new RecipeDetails();
		recipeDetails.setRecipeId(detailsModel.getRecipeId());
		recipeDetails.setIngredientId(detailsModel.getIngredientId());
		recipeDetails.setQuantity(detailsModel.getQuantity());
		return recipeDetails;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateRecipeDetails(@RequestBody RecipeDetailsModel detailsModel,
			@PathVariable(value = "id") Integer recipeDetailsIdParam) {
		RecipeDetails recipeDetails = recipeDetailsService.get(recipeDetailsIdParam);
		recipeDetails.setRecipeId(detailsModel.getRecipeId());
		recipeDetails.setIngredientId(detailsModel.getIngredientId());
		recipeDetails.setQuantity(detailsModel.getQuantity());
		recipeDetailsService.save(recipeDetails);
		return new ResponseEntity<IdModel>(HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteRecipeDetails(@PathVariable(value = "id") Integer recipeDetailsIdParam) {
		recipeDetailsService.delete(recipeDetailsIdParam);
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	@RequestMapping(value = "/clear/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> clearRecipeDetails(@PathVariable(value = "id") Integer recipeIdParam) {
		recipeDetailsService.clearRecipeDetails(recipeIdParam);
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	@RequestMapping(value = "/values/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getValues(@PathVariable(value = "id") Integer recipeIdParam) {
		AverageRecipeValues values = recipeDetailsService.getAvgValues(recipeIdParam);
		RecipeValuesModel convertedValuesModel = new RecipeValuesModel();
		convertedValuesModel.setCalories(values.getCalories());
		convertedValuesModel.setFats(values.getFats());
		convertedValuesModel.setProteins(values.getProteins());
		convertedValuesModel.setCarbohydrates(values.getCarbohydrates());
		convertedValuesModel.setWeight(recipeDetailsService.getRecipeWeight(recipeIdParam));
		return new ResponseEntity<RecipeValuesModel>(convertedValuesModel, HttpStatus.OK);
	}

}
