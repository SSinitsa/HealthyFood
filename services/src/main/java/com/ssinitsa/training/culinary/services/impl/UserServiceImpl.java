package com.ssinitsa.training.culinary.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ssinitsa.training.culinary.dao.api.IUserDao;
import com.ssinitsa.training.culinary.datamodel.User;
import com.ssinitsa.training.culinary.services.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Inject
	private IUserDao userDao;

	@Override
	public User get(Integer id) {
		return userDao.get(id);
	}

	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}

	@Override
	public void save(User user) {
		if (user.getId() == null) {
			userDao.insert(user);
			LOGGER.info("Insert new user : "+user.toString());
		} else {
			userDao.update(user);
			LOGGER.info("Update user : "+user.toString());
		}
	}

	@Override
	public void delete(Integer id) {
		userDao.delete(id);
		LOGGER.info("Delete user.id="+id);

	}

	@Override
	public User getByLogin(String login) {
		return userDao.getByLogin(login);
		
	}

}
