package com.ssinitsa.training.culinary.services.user;

import java.sql.Timestamp;
import java.util.Date;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Test;
import org.springframework.util.Assert;

import com.ssinitsa.training.culinary.datamodel.User;
import com.ssinitsa.training.culinary.datamodel.UserRole;
import com.ssinitsa.training.culinary.services.AbstractTest;
import com.ssinitsa.training.culinary.services.IUserService;

public class CreateUserServiceTest extends AbstractTest {

	@Inject
	private IUserService service;
	
	private Integer savedUserId;
	

	@Test
	public void createTest() {
		User user = new User();
		user.setLogin("n");
		user.setPassword("new password");
		user.setRegistrated(new Timestamp(new Date().getTime()));
		user.setFirstName("new FN");
		user.setLastName("new LN");
		user.setEmail ("new e-mail");
		user.setRole(UserRole.user);
		
		service.save(user);

		savedUserId = user.getId();
		User userFromDb = service.get(savedUserId);
		
		System.out.println (userFromDb.toString());

	}
	
	

}
