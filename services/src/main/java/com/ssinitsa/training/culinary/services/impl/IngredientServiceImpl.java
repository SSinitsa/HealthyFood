package com.ssinitsa.training.culinary.services.impl;

import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ssinitsa.training.culinary.dao.api.IIngredientDao;
import com.ssinitsa.training.culinary.datamodel.Ingredient;
import com.ssinitsa.training.culinary.services.IIngredientService;

@Service
public class IngredientServiceImpl implements IIngredientService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IngredientServiceImpl.class);

	@Inject
	private IIngredientDao ingredientDao;

	@Override
	public Ingredient get(Integer id) {
		return ingredientDao.get(id);
	}

	@Override
	public List<Ingredient> getAll() {
		return ingredientDao.getAll();
	}

	@Override
	public void save(Ingredient ingredient) {
		if (ingredient.getId() == null) {
			ingredientDao.insert(ingredient);
			LOGGER.info("Insert new Ingredient: "+ingredient.toString());
		} else {
			ingredientDao.update(ingredient);
			LOGGER.info("Update Ingredient: "+ingredient.toString());
		}
	}

	@Override
	public void delete(Integer id) {
		ingredientDao.delete(id);
		LOGGER.info("Delete Ingredient.id="+id);

	}


	@Override
	public List<Ingredient> getByCategory(String category) {
		return ingredientDao.getByCategory(category);
	}

	@Override
	public Comparator<Ingredient> orderBy(String column) {
		Comparator<Ingredient> comparator = new Comparator<Ingredient>() {
			@Override
			public int compare(Ingredient o1, Ingredient o2) {
				if (column.equals("name"))
					return o1.getName().compareTo(o2.getName());
				if (column.equals("calories"))
					return o1.getCalories().compareTo(o2.getCalories());
				if (column.equals("fats"))
					return o1.getFats().compareTo(o2.getFats());
				if (column.equals("proteins"))
					return o1.getProteins().compareTo(o2.getProteins());
				if (column.equals("carbohydrates"))
					return o1.getCarbohydrates().compareTo(o2.getCarbohydrates());
				return 0;
			}
		};
		return comparator;

	}
	
	@Override
	public Comparator<Ingredient> descendingOrderBy(String column) {
		Comparator<Ingredient> comparator = new Comparator<Ingredient>() {
			@Override
			public int compare(Ingredient o1, Ingredient o2) {
				if (column.equals("name"))
					return o2.getName().compareTo(o1.getName());
				if (column.equals("calories"))
					return o2.getCalories().compareTo(o1.getCalories());
				if (column.equals("fats"))
					return o2.getFats().compareTo(o1.getFats());
				if (column.equals("proteins"))
					return o2.getProteins().compareTo(o1.getProteins());
				if (column.equals("carbohydrates"))
					return o2.getCarbohydrates().compareTo(o1.getCarbohydrates());
				return 0;
			}
		};
		return comparator;

	}


}
