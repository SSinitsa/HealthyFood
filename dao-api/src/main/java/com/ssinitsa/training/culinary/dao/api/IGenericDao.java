package com.ssinitsa.training.culinary.dao.api;

import java.util.List;

public interface IGenericDao<T> {

	T get(Integer id);

	T insert(T object);

	void update(T object);

	List<T> getAll();

	void delete(Integer id);


}
