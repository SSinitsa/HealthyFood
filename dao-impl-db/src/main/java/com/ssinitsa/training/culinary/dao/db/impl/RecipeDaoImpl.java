package com.ssinitsa.training.culinary.dao.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ssinitsa.training.culinary.dao.api.IRecipeDao;
import com.ssinitsa.training.culinary.dao.api.filter.RecipeFilter;
import com.ssinitsa.training.culinary.datamodel.Recipe;

@Repository
public class RecipeDaoImpl extends GenericDaoImpl<Recipe> implements IRecipeDao {

	@Inject
	private JdbcTemplate jdbcTemplate;


	@Override
	public Recipe insert(Recipe entity) {
		final String INSERT_SQL = "insert into recipe (name, description, author_id, created, category, vegetarian) values(?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setString(1, entity.getName());
				ps.setString(2, entity.getDescription());
				ps.setInt(3, entity.getAuthorId());
				ps.setTimestamp(4, entity.getCreated());
				ps.setString(5, entity.getCategory().name());
				ps.setBoolean(6, entity.isVegetarian());
				return ps;
			}
		}, keyHolder);
		entity.setId(keyHolder.getKey().intValue());

		return entity;
	}


	@Override
	public void update(Recipe recipe) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"update recipe set " + " name=?,description=?,created=?,category=?,vegetarian=? where id=?");
				ps.setString(1, recipe.getName());
				ps.setString(2, recipe.getDescription());
				ps.setTimestamp(3, recipe.getCreated());
				ps.setString(4, recipe.getCategory().name());
				ps.setBoolean(5, recipe.isVegetarian());
				ps.setInt(6, recipe.getId());
				return ps;
			}
		});
	}

	@Override
	public List<Recipe> search(RecipeFilter recipeFilter) {

		StringBuilder sqlQuery = new StringBuilder(
				"select r.id, r.name, r.description, r.author_id, r.created, r.category, r.vegetarian from recipe r");
		List<Object> paramsList = new ArrayList<Object>();

		if (recipeFilter.getIngredientId() != null)
			sqlQuery.append(" left join recipe_details d on d.recipe_id=r.id");
		
		if (!recipeFilter.isEmpty()) {
			sqlQuery.append(" WHERE true");
		}
		if (recipeFilter.getIngredientId() != null) {
			sqlQuery.append(" AND d.ingredient_id=?");
			paramsList.add(recipeFilter.getIngredientId());
		}
		if (recipeFilter.getAuthorId() != null) {
			sqlQuery.append(" AND r.author_id=?");
			paramsList.add(recipeFilter.getAuthorId());
		}
		if (recipeFilter.getCategory() != null) {
			sqlQuery.append(" AND r.category=?");
			paramsList.add(recipeFilter.getCategory());
		}
		if (recipeFilter.getVegetarian() != null) {
			sqlQuery.append(" AND r.vegetarian=?");
			paramsList.add(recipeFilter.getVegetarian());
		}

		String fullSql = sqlQuery.toString();
		System.out.println(fullSql);

		List<Recipe> rs = jdbcTemplate.query(fullSql, paramsList.toArray(),
				new BeanPropertyRowMapper<Recipe>(Recipe.class));

		return rs;

	}

}
