package com.ssinitsa.training.culinary.webapp.controllers;

import java.util.ArrayList;
import java.util.Collections;
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

import com.ssinitsa.training.culinary.webapp.models.IdModel;
import com.ssinitsa.training.culinary.datamodel.Ingredient;
import com.ssinitsa.training.culinary.datamodel.IngredientCategory;
import com.ssinitsa.training.culinary.services.IIngredientService;
import com.ssinitsa.training.culinary.webapp.models.IngredientModel;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

	@Inject
	private IIngredientService ingredientService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll(@RequestParam(required = false) String category, String order, boolean descending,
			Integer page, Integer limit) {
		List<Ingredient> allIngredients= new ArrayList<>();
		if (category == null) {
			allIngredients = ingredientService.getAll();
		} else {
			allIngredients = ingredientService.getByCategory(category);
		}
		if (order != null && descending != true) {
			Collections.sort(allIngredients, ingredientService.orderBy(order));
		}
		if (order != null && descending == true) {
			Collections.sort(allIngredients, ingredientService.descendingOrderBy(order));
		}
		List<IngredientModel> convertedIngredients = new ArrayList<>();
		for (Ingredient ingredient : allIngredients) {
			convertedIngredients.add(entity2model(ingredient));
		}
		if (page != null && limit != null) {
			convertedIngredients = getPage(convertedIngredients, page, limit);
		}

		return new ResponseEntity<List<IngredientModel>>(convertedIngredients, HttpStatus.OK);
	}

	private List<IngredientModel> getPage(List<IngredientModel> convertedIngredients, Integer page, Integer limit) {
		List<IngredientModel> pageOfIngredients = new ArrayList<>();
		int from = (page - 1) * limit;
		int to = page * limit;
		if (to > convertedIngredients.size()) {
			to = convertedIngredients.size();
		}
		for (IngredientModel ingredientModel : convertedIngredients.subList(from, to)) {
			pageOfIngredients.add(ingredientModel);
		}
		return pageOfIngredients;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable(value = "id") Integer ingredientIdParam) {
		Ingredient ingredient = ingredientService.get(ingredientIdParam);
		IngredientModel ingredientModel = entity2model(ingredient);
		return new ResponseEntity<IngredientModel>(ingredientModel, HttpStatus.OK);
	}

	private IngredientModel entity2model(Ingredient ingredient) {
		IngredientModel ingredientModel = new IngredientModel();
		ingredientModel.setName(ingredient.getName());
		ingredientModel.setCalories(ingredient.getCalories());
		ingredientModel.setFats(ingredient.getFats());
		ingredientModel.setProteins(ingredient.getProteins());
		ingredientModel.setCarbohydrates(ingredient.getCarbohydrates());
		ingredientModel.setCategory(ingredient.getCategory().name());
		return ingredientModel;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createIngredient(@RequestBody IngredientModel ingredientModel) {
		Ingredient ingredient = model2entity(ingredientModel);
		ingredientService.save(ingredient);
		return new ResponseEntity<IdModel>(new IdModel(ingredient.getId()), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/multiple", method = RequestMethod.POST)
	public ResponseEntity<?> createIngredient(@RequestBody IngredientModel... ingredientModelArray){
		List<IdModel> idModels = new ArrayList<>();
		for (IngredientModel ingredientModel : ingredientModelArray){
			Ingredient ingredient = model2entity(ingredientModel);
			ingredientService.save(ingredient);
			idModels.add(new IdModel(ingredient.getId()));
		}
		return new ResponseEntity <List<IdModel>>(idModels, HttpStatus.CREATED);
		
		
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateIngredient(@RequestBody IngredientModel ingredientModel,
			@PathVariable(value = "id") Integer ingredientIdParam) {
		Ingredient ingredient = ingredientService.get(ingredientIdParam);
		ingredient.setName(ingredientModel.getName());
		ingredient.setCalories(ingredientModel.getCalories());
		ingredient.setFats(ingredientModel.getFats());
		ingredient.setProteins(ingredientModel.getProteins());
		ingredient.setCarbohydrates(ingredientModel.getCarbohydrates());
		ingredient.setCategory(IngredientCategory.valueOf(ingredientModel.getCategory()));
		ingredientService.save(ingredient);
		return new ResponseEntity<IdModel>(HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteIngredient(@PathVariable(value = "id") Integer ingredientIdParam) {
		ingredientService.delete(ingredientIdParam);
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	private Ingredient model2entity(IngredientModel ingredientModel) {
		Ingredient ingredient = new Ingredient();
		ingredient.setName(ingredientModel.getName());
		ingredient.setCalories(ingredientModel.getCalories());
		ingredient.setFats(ingredientModel.getFats());
		ingredient.setProteins(ingredientModel.getProteins());
		ingredient.setCarbohydrates(ingredientModel.getCarbohydrates());
		ingredient.setCategory(IngredientCategory.valueOf(ingredientModel.getCategory()));
		return ingredient;
	}

}
