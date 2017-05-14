package com.ssinitsa.training.culinary.dao.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ssinitsa.training.culinary.dao.api.IRecipeDetailsDao;
import com.ssinitsa.training.culinary.dao.db.mapper.RecipeWithDetailsMapper;
import com.ssinitsa.training.culinary.datamodel.Recipe;
import com.ssinitsa.training.culinary.datamodel.RecipeDetails;
import com.ssinitsa.training.culinary.datamodel.RecipeWithDetails;

@Repository
public class RecipeDetailsDaoImpl extends GenericDaoImpl<RecipeDetails> implements IRecipeDetailsDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<RecipeWithDetails> getRecipeWithDetails(Integer id) {
		try {
			return (List<RecipeWithDetails>) jdbcTemplate.query(
					"select * from recipe_details d " + "left join recipe r on d.recipe_id=r.id "
							+ "left join ingredient i on d.ingredient_id=i.id " + "where r.id=?",
					new Object[] { id }, new RecipeWithDetailsMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public RecipeDetails insert(RecipeDetails entity) {
		final String INSERT_SQL = "insert into recipe_details (recipe_id, ingredient_id, quantity) values(?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setInt(1, entity.getRecipeId());
				ps.setInt(2, entity.getIngredientId());
				ps.setInt(3, entity.getQuantity());
				return ps;
			}
		}, keyHolder);
		entity.setId(keyHolder.getKey().intValue());

		return entity;
	}

	@Override
	public void update(RecipeDetails recipeDetails) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection
						.prepareStatement("update recipe_details set " + " quantity=? where id=?");
				System.out.println(recipeDetails.getId() + " " + recipeDetails.getQuantity());
				ps.setInt(1, recipeDetails.getQuantity());
				ps.setInt(2, recipeDetails.getId());
				return ps;
			}

		});

	}


	@Override
	public List<RecipeDetails> getByRecipeId(Integer id) {
		
			return jdbcTemplate.query("select * from recipe_details where recipe_id = ? ", new Object[] { id },
					new BeanPropertyRowMapper<RecipeDetails>(RecipeDetails.class));
		
	}

	@Override
	public void clearRecipeDetails(Integer recipeId) {
		jdbcTemplate.update("delete from recipe_details where recipe_id=" + recipeId);
		
	}

}
