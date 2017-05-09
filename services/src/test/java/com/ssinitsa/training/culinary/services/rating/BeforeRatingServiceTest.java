package com.ssinitsa.training.culinary.services.rating;

import com.ssinitsa.training.culinary.datamodel.Rating;

public class BeforeRatingServiceTest {

	public Rating createRating(Integer userId, Integer recipeId) {
		Rating rating = new Rating();
		rating.setUserId(userId);
		rating.setRecipeId(recipeId);
		rating.setVote((int)(Math.random()*10));
		System.out.println(rating.toString());
		
		return rating;

	}
}
