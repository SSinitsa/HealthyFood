package com.ssinitsa.training.culinary.services.user;

import java.sql.Timestamp;
import java.util.Date;

import com.ssinitsa.training.culinary.datamodel.User;
import com.ssinitsa.training.culinary.datamodel.UserRole;

public class BeforeUserServiceTest {

	public User createUser() {
		User user = new User();
		user.setLogin("new login");
		user.setPassword("new password");
		user.setRegistrated(new Timestamp(new Date().getTime()));
		user.setFirstName("new FN");
		user.setLastName("new LN");
		user.setEmail("new e-mail");
		user.setRole(UserRole.user);
		System.out.println(user.toString());
		

		return user;

	}
}
