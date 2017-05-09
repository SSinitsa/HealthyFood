package com.ssinitsa.training.culinary.services.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ssinitsa.training.culinary.dao.api.IRatingDao;
import com.ssinitsa.training.culinary.datamodel.Rating;
import com.ssinitsa.training.culinary.datamodel.RecipeWithRating;
import com.ssinitsa.training.culinary.services.IRatingService;

@Service
public class RatingServiceImpl implements IRatingService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RatingServiceImpl.class);

	@Inject
	private IRatingDao ratingDao;

	@Override
	public Rating get(Integer id) {
		return ratingDao.get(id);
	}

	/*@Override
	public List<Rating> getAll() {
		return ratingDao.getAll();
	}*/

	@Override
	public void save(Rating rating) {
		if (rating.getId() == null) {
			ratingDao.insert(rating);
			LOGGER.info("Add new Vote: "+rating.toString());
		} else {
			ratingDao.update(rating);
			LOGGER.info("Update Vote: "+rating.toString());
		}
	}

	@Override
	public void delete(Integer id) {
		ratingDao.delete(id);
		LOGGER.info("Delete Vote.id="+id);

	}

	/*@Override
	public void saveMultiple(Rating... ratingAray) {

		for (Rating rating : ratingAray) {
			save(rating);
		}

	}*/

	@Override
	public List<RecipeWithRating> getRecipeWithRating(Integer id) {
		return ratingDao.getRecipeWithRating(id);
	}

	@Override
	public BigDecimal getAverageRating(Integer id) {
		Double sumRating =(double) 0;
		List<RecipeWithRating> recipeWithRatings = ratingDao.getRecipeWithRating(id);
		for (Iterator<RecipeWithRating> i = recipeWithRatings.iterator(); i.hasNext();){
			RecipeWithRating rating = i.next();
			sumRating += rating.getVote();
		}
		BigDecimal avgRating = new BigDecimal(sumRating/recipeWithRatings.size());
		return avgRating.setScale(2, RoundingMode.HALF_EVEN);
	}
}
