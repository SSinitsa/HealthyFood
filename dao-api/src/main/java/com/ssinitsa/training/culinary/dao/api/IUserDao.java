package com.ssinitsa.training.culinary.dao.api;

import java.util.List;

import com.ssinitsa.training.culinary.datamodel.User;

public interface IUserDao {
	User get(Integer id);

	User insert(User user);

	void update(User user);

	List<User> getAll();

	void delete(Integer id);
	
	Integer getByLogin (String login);

}
