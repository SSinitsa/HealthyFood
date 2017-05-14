package com.ssinitsa.training.culinary.dao.api;

import java.util.List;

import com.ssinitsa.training.culinary.datamodel.Ingredient;

public interface IIngredientDao extends IGenericDao<Ingredient>{

	Ingredient get(Integer id);
	
	Ingredient insert(Ingredient ingredient);

	void update(Ingredient ingredient);

	List<Ingredient> getAll();

	void delete(Integer id);
	
	List<Ingredient> getByCategory (String category);
	
}
