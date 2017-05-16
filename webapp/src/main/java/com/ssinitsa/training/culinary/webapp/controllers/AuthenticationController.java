package com.ssinitsa.training.culinary.webapp.controllers;

import java.sql.Timestamp;
import java.util.Date;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssinitsa.training.culinary.datamodel.User;
import com.ssinitsa.training.culinary.datamodel.UserRole;
import com.ssinitsa.training.culinary.services.IUserService;
import com.ssinitsa.training.culinary.webapp.converters.UserModel2User;
import com.ssinitsa.training.culinary.webapp.models.IdModel;
import com.ssinitsa.training.culinary.webapp.models.LoginModel;
import com.ssinitsa.training.culinary.webapp.models.UserModel;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Inject
	private IUserService authService;
	
	@Inject
	private UserModel2User modelConverter;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> authentication(@RequestBody LoginModel loginModel) {
		
		String login = loginModel.getLogin();
		String password = loginModel.getPassword();

		User authUser;
		try {
			authUser = authService.authentification(login, password);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		if (authUser == null) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<User>(authUser, HttpStatus.OK);
	}
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody UserModel userModel) {

		if (userModel == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		User newUser = modelConverter.userModel2user(userModel);
		newUser.setRegistrated(new Timestamp(new Date().getTime()));
		newUser.setRole(UserRole.user);
		try {
			authService.registration(newUser);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		}

		return new ResponseEntity<IdModel>(new IdModel(newUser.getId()), HttpStatus.CREATED);
	}
	

}
