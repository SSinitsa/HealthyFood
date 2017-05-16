package com.ssinitsa.training.culinary.dao.db.cache;

import org.springframework.stereotype.Component;

import com.ssinitsa.training.culinary.datamodel.User;

@Component
public class UserCache extends Cache<User>{

	@Override
	protected String getCacheName() {
		return "user:";
	}

	@Override
	protected Integer getLifeTime() {
		return 240;
	}

	
}
