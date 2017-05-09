package com.ssinitsa.training.culinary.services.user;

import java.sql.Timestamp;
import java.util.Date;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import com.ssinitsa.training.culinary.datamodel.User;
import com.ssinitsa.training.culinary.datamodel.UserRole;
import com.ssinitsa.training.culinary.services.AbstractTest;
import com.ssinitsa.training.culinary.services.IUserService;

public class UpdateUserServiceTest extends AbstractTest {

	@Inject
	private IUserService service;

	private Integer savedUserId;
	private User newUser;

	@Before
	public void createUserTest() {
		BeforeUserServiceTest beforeService = new BeforeUserServiceTest();
		newUser = beforeService.createUser();
		service.save(newUser);
		savedUserId = newUser.getId();
		System.out.println(newUser.toString());
	}

	@After
	public void deleteCreatedIngredient() {
		//service.delete(savedUserId);

	}

	@Test
	public void updateTest() {
		User user2 = new User();
		user2.setLogin("2new login");
		user2.setPassword("2new password");
		user2.setRegistrated(new Timestamp(new Date().getTime()));
		user2.setFirstName("2new FN");
		user2.setLastName("2new LN");
		user2.setEmail("2new e-mail");
		user2.setRole(UserRole.user);
		user2.setId(savedUserId);
		service.save(user2);
		User updatedUser = service.get(savedUserId);
		System.out.println(updatedUser.toString());

		Assert.notNull(updatedUser, "user must be saved and updated");

		Assert.isTrue(!newUser.equals(updatedUser), "users must be not equals");

	}

}
