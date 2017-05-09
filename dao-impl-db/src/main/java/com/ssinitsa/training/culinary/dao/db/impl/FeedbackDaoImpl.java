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

import com.ssinitsa.training.culinary.dao.api.IFeedbackDao;
import com.ssinitsa.training.culinary.dao.db.mapper.RecipeWithFeedbackMapper;
import com.ssinitsa.training.culinary.datamodel.Feedback;
import com.ssinitsa.training.culinary.datamodel.RecipeWithFeedback;

@Repository
public class FeedbackDaoImpl implements IFeedbackDao {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(FeedbackDaoImpl.class);
	
	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public Feedback get(Integer id) {
		try {
			return jdbcTemplate.queryForObject("select * from feedback where id = ? ", new Object[] { id },
					new BeanPropertyRowMapper<Feedback>(Feedback.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public Feedback insert(Feedback entity) {
		final String INSERT_SQL = "insert into feedback (text, user_id, recipe_id, created) values(?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setString(1, entity.getText());
				ps.setInt(2, entity.getUserId());
				ps.setInt(3, entity.getRecipeId());
				ps.setTimestamp(4, entity.getCreated());
				return ps;
			}
		}, keyHolder);
		entity.setId(keyHolder.getKey().intValue());

		return entity;
	}

	@Override
	public void delete(Integer id) {
		jdbcTemplate.update("delete from feedback where id=" + id);

	}

	@Override
	public List<Feedback> getAll() {
		List<Feedback> rs = jdbcTemplate.query("select * from feedback ",
				new BeanPropertyRowMapper<Feedback>(Feedback.class));
		return rs;
	}

	@Override
	public void update(Feedback feedback) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement("update feedback set " + " text=? where id=?");
				ps.setString(1, feedback.getText());
				ps.setInt(2, feedback.getId());
				return ps;
			}

		});

	}

	
	@Override
	public List<RecipeWithFeedback> getRecipeWithFeedback(Integer id) {
		try {
			return (List<RecipeWithFeedback>) jdbcTemplate.query("select * from feedback f " + "left join recipe r on f.recipe_id=r.id "
					+ "left join \"user\" u on f.user_id=u.id " + "where r.id=?", new Object[] { id },
					new RecipeWithFeedbackMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
}
