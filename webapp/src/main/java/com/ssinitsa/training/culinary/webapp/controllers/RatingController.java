package com.ssinitsa.training.culinary.webapp.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssinitsa.training.culinary.datamodel.Rating;
import com.ssinitsa.training.culinary.datamodel.RecipeWithRating;
import com.ssinitsa.training.culinary.services.IRatingService;
import com.ssinitsa.training.culinary.webapp.models.IdModel;
import com.ssinitsa.training.culinary.webapp.models.RatingModel;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	
	 @Inject
	 private IRatingService ratingService;
	 
	 @RequestMapping(method = RequestMethod.GET)
		public HttpStatus getStatus() {
			return HttpStatus.NOT_FOUND;
		}
	 
	 @RequestMapping(value = "/{id}", method = RequestMethod.GET)
		public ResponseEntity<?> getByRecipeId(@PathVariable(value = "id") Integer recipeIdParam) {
		 List<RecipeWithRating> recipeWithRatings = ratingService.getRecipeWithRating(recipeIdParam);
			List<RatingModel> convertedRatings = new ArrayList<>();
			for (RecipeWithRating recipeWithRating : recipeWithRatings) {
				convertedRatings.add(entity2model(recipeWithRating));
			}
			return new ResponseEntity<List<RatingModel>>(convertedRatings, HttpStatus.OK);
	 }

	private RatingModel entity2model(RecipeWithRating recipeWithRating) {
		RatingModel ratingModel = new RatingModel();
		ratingModel.setUserFirstName(recipeWithRating.getUser().getFirstName());
		ratingModel.setUserLastName(recipeWithRating.getUser().getLastName());
		ratingModel.setVote(recipeWithRating.getVote());
		return ratingModel;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> setVote(@RequestBody RatingModel ratingModel) {
		Rating rating = model2entity(ratingModel);
		ratingService.save(rating);
		return new ResponseEntity<IdModel>(new IdModel(rating.getId()), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteVote(@PathVariable(value = "id") Integer ratingIdParam) {
		ratingService.delete(ratingIdParam);
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	private Rating model2entity(RatingModel ratingModel) {
		Rating rating = new Rating();
		rating.setRecipeId(ratingModel.getRecipeId());
		rating.setUserId(ratingModel.getUserId());
		rating.setVote(ratingModel.getVote());
		return rating;
	}
	 
	@RequestMapping(value = "/average/{id}", method = RequestMethod.GET)
	public BigDecimal getAverageRating(@PathVariable(value = "id") Integer recipeIdParam){
		BigDecimal avgRating = ratingService.getAverageRating(recipeIdParam);
		return avgRating;
		
	}
}
