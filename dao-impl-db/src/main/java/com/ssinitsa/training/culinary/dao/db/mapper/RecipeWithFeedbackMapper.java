package com.ssinitsa.training.culinary.dao.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ssinitsa.training.culinary.datamodel.Recipe;
import com.ssinitsa.training.culinary.datamodel.RecipeWithFeedback;
import com.ssinitsa.training.culinary.datamodel.User;

public final class RecipeWithFeedbackMapper implements RowMapper<RecipeWithFeedback> {

	@Override
	public RecipeWithFeedback mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		RecipeWithFeedback recipeWithFeedback = new RecipeWithFeedback();
		
		Recipe recipe = new Recipe();
		recipe.setId(rs.getInt(6));
		recipe.setName(rs.getString(7));
		
		User user = new User();
		user.setId(rs.getInt(13));
		user.setFirstName(rs.getString(17));
		user.setLastName(rs.getString(18));
		
		recipeWithFeedback.setRecipe(recipe);
		recipeWithFeedback.setText(rs.getString(2));
		recipeWithFeedback.setUser(user);
		recipeWithFeedback.setCreated(rs.getTimestamp(5));
		return recipeWithFeedback;
	}

}
