package com.ssinitsa.training.culinary.dao.db.cache;

public interface ICache <T>{
	
	T get(String key);
	
	void set(String key, T value);

}
