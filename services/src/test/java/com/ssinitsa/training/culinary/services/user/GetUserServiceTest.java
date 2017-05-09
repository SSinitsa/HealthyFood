package com.ssinitsa.training.culinary.services.user;

import javax.inject.Inject;

import org.junit.Test;

import com.ssinitsa.training.culinary.datamodel.User;
import com.ssinitsa.training.culinary.services.AbstractTest;
import com.ssinitsa.training.culinary.services.IUserService;

public class GetUserServiceTest extends AbstractTest {
	
	@Inject
	private IUserService service;
	
	@Test
	public void getTest(){
	User user =	service.getByLogin("login1");
	System.out.println(user.toString());
	}

}
