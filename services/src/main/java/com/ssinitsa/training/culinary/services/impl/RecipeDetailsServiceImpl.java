package com.ssinitsa.training.culinary.services.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ssinitsa.training.culinary.dao.api.IRecipeDetailsDao;
import com.ssinitsa.training.culinary.datamodel.RecipeDetails;
import com.ssinitsa.training.culinary.datamodel.RecipeWithDetails;
import com.ssinitsa.training.culinary.services.IRecipeDetailsService;
import com.ssinitsa.training.culinary.services.models.AverageRecipeValues;

@Service
public class RecipeDetailsServiceImpl implements IRecipeDetailsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RecipeDetailsServiceImpl.class);

	@Inject
	private IRecipeDetailsDao recipeDetailsDao;

	@Override
	public RecipeDetails get(Integer id) {
		return recipeDetailsDao.get(id);
	}

	@Override
	public void save(RecipeDetails recipeDetails) {
		if (recipeDetails.getId() == null) {
			recipeDetailsDao.insert(recipeDetails);
			LOGGER.info("Add new details: "+recipeDetails.toString());
		} else {
			System.out.println("Update RecipeDetails");
			LOGGER.info("Update details: "+recipeDetails.toString());
		}
	}

	@Override
	public void delete(Integer id) {
		recipeDetailsDao.delete(id);
		LOGGER.info("Delete recipeDetails.id=: "+id);

	}

	/*@Override
	public void saveMultiple(RecipeDetails... recipeDetailsArray) {

		for (RecipeDetails recipeDetails : recipeDetailsArray) {
			save(recipeDetails);
			LOGGER.info("Add new details from array: "+recipeDetails.toString());
		}

	}*/

	@Override
	public List<RecipeWithDetails> getWithDetails(Integer recipeId) {
		return recipeDetailsDao.getRecipeWithDetails(recipeId);
	}

	@Override
	public AverageRecipeValues getAvgValues(Integer recipeId) {
		Integer sumCal = 0;
		Integer dishWeight = 0;
		Double sumFats = (double) 0;
		Double sumProteins = (double) 0;
		Double sumCarbohydrates = (double) 0;
		AverageRecipeValues averageRecipeValues = new AverageRecipeValues();
		List<RecipeWithDetails> recipeWithDetails = recipeDetailsDao.getRecipeWithDetails(recipeId);
		for (Iterator<RecipeWithDetails> i = recipeWithDetails.iterator(); i.hasNext();) {
			RecipeWithDetails ingredient = i.next();
			sumCal += ingredient.getIngredient().getCalories() * ingredient.getQuantity();
			sumFats += ingredient.getIngredient().getFats() * ingredient.getQuantity();
			sumProteins += ingredient.getIngredient().getProteins() * ingredient.getQuantity();
			sumCarbohydrates += ingredient.getIngredient().getCarbohydrates() * ingredient.getQuantity();
			dishWeight += ingredient.getQuantity();
		}
		Integer avgCal = sumCal / dishWeight;
		Double avgFats = sumFats / dishWeight;
		Double avgProteins = sumProteins / dishWeight;
		Double avgCarbohydrates = sumCarbohydrates / dishWeight;
		averageRecipeValues.setCalories(avgCal);
		averageRecipeValues.setFats(new BigDecimal(avgFats).setScale(3, RoundingMode.HALF_EVEN));
		averageRecipeValues.setProteins(new BigDecimal(avgProteins).setScale(3, RoundingMode.HALF_EVEN));
		averageRecipeValues.setCarbohydrates(new BigDecimal(avgCarbohydrates).setScale(3, RoundingMode.HALF_EVEN));
		return averageRecipeValues;
	}

	@Override
	public Integer getRecipeWeight(Integer recipeId) {
		Integer weight = 0;
		List<RecipeWithDetails> recipeWithDetails = recipeDetailsDao.getRecipeWithDetails(recipeId);
		for (Iterator<RecipeWithDetails> i = recipeWithDetails.iterator(); i.hasNext();) {
			RecipeWithDetails ingredient = i.next();
			weight += ingredient.getQuantity();
		}
		return weight;
	}

	@Override
	public void clearRecipeDetails(Integer recipeId) {
		recipeDetailsDao.clearRecipeDetails(recipeId);
		LOGGER.info("Delete all details from recipe.id="+recipeId);
	}

}
