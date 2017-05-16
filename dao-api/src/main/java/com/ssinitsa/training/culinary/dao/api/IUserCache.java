package com.ssinitsa.training.culinary.dao.api;

import com.ssinitsa.training.culinary.datamodel.User;

public interface IUserCache{
	
	User get(String key);
	
	void set(String key, User value);
	
	String getCacheName();
	
	Integer getLifeTime();
	
	
	

}
