package com.ssinitsa.training.culinary.dao.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ssinitsa.training.culinary.dao.api.IUserCache;
import com.ssinitsa.training.culinary.dao.api.IUserDao;
import com.ssinitsa.training.culinary.dao.db.cache.UserCache;
import com.ssinitsa.training.culinary.dao.db.cache.UserCache;
import com.ssinitsa.training.culinary.datamodel.User;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements IUserDao {
	
	@Inject
	private JdbcTemplate jdbcTemplate;
	
	/*@Inject
	private UserCache userCache;*/

	@Override
	public Integer getByLogin(String login){
		try {
			return jdbcTemplate.queryForObject("select \"id\" from \"user\" where \"login\" = ? ", new Object[] { login },
					new BeanPropertyRowMapper<User>(User.class)).getId();
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public User insert(User entity) {
		final String INSERT_SQL = "insert into \"user\" (\"login\", \"password\", \"registrated\", \"first_name\", \"last_name\", \"email\", \"role\") values(?,?,?,?,?,?,?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setString(1, entity.getLogin());
				ps.setString(2, entity.getPassword());
				ps.setTimestamp(3, entity.getRegistrated());
				ps.setString(4, entity.getFirstName());
				ps.setString(5, entity.getLastName());
				ps.setString(6, entity.getEmail());
				ps.setString(7, entity.getRole().name());
				return ps;
			}
		}, keyHolder);
		entity.setId(keyHolder.getKey().intValue());

		return entity;
	}


	@Override
	public void update(User user) {
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement("update \"user\" set "
						+ "\"login\"=?, \"password\"=?, \"registrated\"=?, \"first_name\"=?, \"last_name\"=?, \"email\"=?, \"role\"=? where \"id\"=?");
				ps.setString(1, user.getLogin());
				ps.setString(2, user.getPassword());
				ps.setTimestamp(3, user.getRegistrated());
				ps.setString(4, user.getFirstName());
				ps.setString(5, user.getLastName());
				ps.setString(6, user.getEmail());
				ps.setString(7, user.getRole().name());
				ps.setInt(8, user.getId());
				return ps;
			}
		});

	}

	@Override
	public User getUserData(String login, String password) {
		
		/*User userFromCache = userCache.get(login);
		
		if (userFromCache!=null&&userFromCache.getPassword().equals(password)){
			return userFromCache;
		}
		if (userFromCache!=null&&!userFromCache.getPassword().equals(password)){
			return null;
		}*/
		try {
			User user = jdbcTemplate.queryForObject("SELECT * FROM \"user\" WHERE \"login\" = ? AND \"password\" = ?", new Object[] { login, password },
					new BeanPropertyRowMapper<User>(User.class));
			System.out.println(user.toString());
			/*userCache.set(user.getLogin(), user);*/
			return user;
		} catch (EmptyResultDataAccessException e) {
			//LOGGER.error("Exception thrown! ", e);
			return null;
		}
	}

}
