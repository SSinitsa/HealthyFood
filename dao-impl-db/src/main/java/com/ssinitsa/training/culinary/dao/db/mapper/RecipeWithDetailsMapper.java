package com.ssinitsa.training.culinary.dao.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ssinitsa.training.culinary.datamodel.DishCategory;
import com.ssinitsa.training.culinary.datamodel.Ingredient;
import com.ssinitsa.training.culinary.datamodel.IngredientCategory;
import com.ssinitsa.training.culinary.datamodel.Recipe;
import com.ssinitsa.training.culinary.datamodel.RecipeWithDetails;

public final class RecipeWithDetailsMapper implements RowMapper<RecipeWithDetails> {

	@Override
	public RecipeWithDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Ingredient ingredient = new Ingredient();
		ingredient.setId(rs.getInt(12));
		ingredient.setName(rs.getString(13));
		ingredient.setFats(rs.getDouble("fats"));
		ingredient.setProteins(rs.getDouble("proteins"));
		ingredient.setCarbohydrates(rs.getDouble("carbohydrates"));
		ingredient.setCalories(rs.getInt("calories"));
		ingredient.setCategory(IngredientCategory.valueOf(rs.getString(18)));
		
		Recipe recipe = new Recipe();
		recipe.setId(rs.getInt(5));
		recipe.setName(rs.getString(6));
		recipe.setAuthorId(rs.getInt("author_id"));
		recipe.setCategory(DishCategory.valueOf(rs.getString(10)));
		
		
		RecipeWithDetails recipeWithDetails = new RecipeWithDetails();
		recipeWithDetails.setRecipe(recipe);
		recipeWithDetails.setIngredient(ingredient);
		recipeWithDetails.setQuantity(rs.getInt("quantity"));
		
				
		return recipeWithDetails;
	}

}
