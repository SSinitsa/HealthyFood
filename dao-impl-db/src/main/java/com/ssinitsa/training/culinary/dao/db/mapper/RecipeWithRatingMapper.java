package com.ssinitsa.training.culinary.dao.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ssinitsa.training.culinary.datamodel.Recipe;
import com.ssinitsa.training.culinary.datamodel.RecipeWithRating;
import com.ssinitsa.training.culinary.datamodel.User;

public final class RecipeWithRatingMapper implements RowMapper<RecipeWithRating> {

	@Override
	public RecipeWithRating mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		RecipeWithRating recipeWithRating = new RecipeWithRating();
		
		Recipe recipe = new Recipe();
		recipe.setId(rs.getInt(5));
		recipe.setName(rs.getString(6));
		
		User user = new User();
		user.setId(rs.getInt(12));
		user.setFirstName(rs.getString(16));
		user.setLastName(rs.getString(17));
		
		recipeWithRating.setUser(user);
		recipeWithRating.setRecipe(recipe);
		recipeWithRating.setVote(rs.getInt(4));
		return recipeWithRating;
	}

}
