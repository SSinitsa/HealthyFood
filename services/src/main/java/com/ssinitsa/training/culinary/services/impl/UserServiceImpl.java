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
			LOGGER.info("Insert user : "+user.toString());
			return;
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
	public Integer getByLogin(String login) {
		return userDao.getByLogin(login);
		
	}

	@Override
	public User authentification(String login, String password) {
		LOGGER.info("Authentication attempt from User:"+login);
		System.out.println(login);
		System.out.println(password);
		if (login==null||password==null){
			LOGGER.info("Login or password is missing");
			return null;
		}
		User authUser;
		authUser = userDao.getUserData(login, password);
		
		return authUser;
	}

	@Override
	public void registration(User user) {
		LOGGER.info("Registration attempt from new User");
		if (user==null){
			LOGGER.info("User data for registration is missing");
			return;
		}
		userDao.insert(user);
		LOGGER.info("User:"+user.getLogin()+" registrated successfully");
	}


}
