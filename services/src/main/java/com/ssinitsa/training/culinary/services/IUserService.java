package com.ssinitsa.training.culinary.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.ssinitsa.training.culinary.datamodel.User;

public interface IUserService {

	User get(Integer id);
	
	User getByLogin(String login);

	@Transactional
	void save(User user);

	List<User> getAll();

	@Transactional
	void delete(Integer id);
}
