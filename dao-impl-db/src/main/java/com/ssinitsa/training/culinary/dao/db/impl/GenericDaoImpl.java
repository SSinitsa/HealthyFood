package com.ssinitsa.training.culinary.dao.db.impl;

import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.swing.text.html.parser.Entity;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ssinitsa.training.culinary.dao.api.IGenericDao;
import com.ssinitsa.training.culinary.datamodel.Ingredient;


public abstract class GenericDaoImpl<T> implements IGenericDao<T> {

	protected Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		ParameterizedType genericSuperClass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperClass.getActualTypeArguments()[0];
	}
	
	@Inject
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public T get(Integer id) {
		try {
			String sql = "SELECT * FROM \"" + getTableName() + "\" WHERE \"id\" = ?";
			return jdbcTemplate.queryForObject(sql, new Object[] { id }, new BeanPropertyRowMapper<T>(entityClass));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<T> getAll() {
		String sql = "SELECT * FROM \"" + getTableName() + "\"";
		List<T> rs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(entityClass));
		return rs;
	}
	
	@Override
	public void delete(Integer id){ 
		String sql = "DELETE FROM \""+getTableName()+"\" WHERE \"id\"="+id;
		jdbcTemplate.update(sql);
	}
	
	public abstract T insert(T object);
		
	public abstract void update(T object);
	
	private String getTableName(){
		return entityClass.getSimpleName().replaceAll("(.)(\\p{Upper})", "$1_$2").toLowerCase();
	}

}
