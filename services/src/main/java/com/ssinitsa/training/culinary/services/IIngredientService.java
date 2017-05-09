package com.ssinitsa.training.culinary.services;

import java.util.Comparator;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ssinitsa.training.culinary.datamodel.Ingredient;

public interface IIngredientService {

	Ingredient get(Integer id);

	@Transactional
	void save(Ingredient ingredient);

	List<Ingredient> getAll();

	@Transactional
	void delete(Integer id);
	
	List <Ingredient> getByCategory (String category);
	
	Comparator<Ingredient> orderBy (String column);

	Comparator<Ingredient> descendingOrderBy(String column);
	
}
