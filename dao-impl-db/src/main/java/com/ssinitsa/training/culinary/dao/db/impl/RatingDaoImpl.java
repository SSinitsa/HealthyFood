package com.ssinitsa.training.culinary.dao.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ssinitsa.training.culinary.dao.api.IRatingDao;
import com.ssinitsa.training.culinary.dao.db.mapper.RecipeWithRatingMapper;
import com.ssinitsa.training.culinary.datamodel.Rating;
import com.ssinitsa.training.culinary.datamodel.RecipeWithRating;

@Repository
public class RatingDaoImpl extends GenericDaoImpl<Rating> implements IRatingDao {

	private final static Logger LOGGER = LoggerFactory.getLogger(RatingDaoImpl.class);

	@Inject
	private JdbcTemplate jdbcTemplate;


	@Override
	public Rating insert(Rating entity) {
		final String INSERT_SQL = "insert into rating (user_id, recipe_id, vote) values(?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setInt(1, entity.getUserId());
				ps.setInt(2, entity.getRecipeId());
				ps.setInt(3, entity.getVote());
				return ps;
			}
		}, keyHolder);
		entity.setId(keyHolder.getKey().intValue());

		return entity;
	}


	@Override
	public void update(Rating rating) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement("update rating set " + " vote=? where id=?");
				ps.setInt(1, rating.getVote());
				ps.setInt(2, rating.getId());
				return ps;
			}
		});
	}

	@Override
	public List<RecipeWithRating> getRecipeWithRating(Integer id) {
		try {
			return (List<RecipeWithRating>) jdbcTemplate.query(
					"select * from rating v " + "left join recipe r on v.recipe_id=r.id "
							+ "left join \"user\" u on v.user_id=u.id " + "where r.id=?",
					new Object[] { id }, new RecipeWithRatingMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

}
