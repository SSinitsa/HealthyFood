package com.ssinitsa.training.culinary.services;

import java.util.List;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.ssinitsa.training.culinary.datamodel.User;
import com.ssinitsa.training.culinary.datamodel.UserRole;

public class UserServiceTest extends AbstractTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);

	@Inject
	private IUserService userService;

	private User newUser;

	private Integer savedUserId;
	
	private Integer savedUserId2;
	
	
	@Before
	public void createEntity(){
		newUser = createEntity(new User());
		userService.save(newUser);
		savedUserId = newUser.getId();
	}
	
	@After
	public void deleteEntity(){
		if (userService.get(savedUserId) != null) {
			userService.delete(savedUserId);
		}
		if (userService.get(savedUserId2) != null) {
			userService.delete(savedUserId2);
		}
	}
	
	@Test
	public void getTest(){
		LOGGER.debug("User getTest");
		User userFromDb = userService.get(savedUserId);
		Assert.notNull(userFromDb, "User must be exist");
		Assert.isTrue(!hasEmptyField(userFromDb), "User should'nt have empty columns");
	}
	
	@Test
	public void getAllTest(){
		LOGGER.debug("User getAllTest");
		User newUser2 = createEntity(new User());
		userService.save(newUser2);
		savedUserId2 = newUser2.getId();
		List<User> users = userService.getAll();
		Assert.notEmpty(users, "List of Users should'nt be empty");
		Assert.isTrue(users.size() > 1, "The number of Users should be more than 1");
	}
	
	@Test
	public void insertTest() {
		LOGGER.debug("User insertTest");
		User newUser2 = createEntity(new User());
		userService.save(newUser2);
		savedUserId2 = newUser2.getId();
		User userFromDb = userService.get(savedUserId2);
		Assert.isTrue(newUser2.equals(userFromDb), "Users should be equals");
		Assert.isTrue(!hasEmptyField(userFromDb), "User should'nt have empty columns");
	}
	
	@Test
	public void updateTest() {
		LOGGER.debug("User updateTest");
		User updatingUser = createEntity(new User());
		updatingUser.setId(savedUserId);
		updatingUser.setRegistrated(newUser.getRegistrated());
		updatingUser.setRole(UserRole.admin);
		userService.save(updatingUser);
		User userFromDb = userService.get(savedUserId);
		Assert.isTrue(newUser.getId().equals(userFromDb.getId()), "ID columns must be equals");
		Assert.isTrue(!newUser.getLogin().equals(userFromDb.getLogin()),"");
		Assert.isTrue(!newUser.getPassword().equals(userFromDb.getPassword()),"");
		Assert.isTrue(newUser.getRegistrated().equals(userFromDb.getRegistrated()),"");
		Assert.isTrue(!newUser.getFirstName().equals(userFromDb.getFirstName()),"");
		Assert.isTrue(!newUser.getLastName().equals(userFromDb.getLastName()),"");
		Assert.isTrue(!newUser.getEmail().equals(userFromDb.getEmail()),"");
		Assert.isTrue(!newUser.getRole().equals(userFromDb.getRole()),"");
	}
	
	@Test
	public void deleteTest(){
		LOGGER.debug("User deleteTest");
		userService.delete(savedUserId);
		User userFromDb = userService.get(savedUserId);
		Assert.isNull(userFromDb, "User must not exist");
	}
	
	@Test
	public void getByLoginTest(){
		
		Integer userId = userService.getByLogin(newUser.getLogin());
		Assert.isTrue(userId.equals(savedUserId),"ID must be equals");
		
	}
	
	/*@Test
	public void getByLogin(){
		
	}*/

}
