package com.ssinitsa.training.culinary.webapp.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssinitsa.training.culinary.datamodel.User;
import com.ssinitsa.training.culinary.datamodel.UserRole;
import com.ssinitsa.training.culinary.services.IUserService;
import com.ssinitsa.training.culinary.webapp.models.IdModel;
import com.ssinitsa.training.culinary.webapp.models.UserModel;

@RestController
@RequestMapping("/users")
public class UserController {

	@Inject
	private IUserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		List<User> allUsers = userService.getAll();
		List<UserModel> convertedUsers = new ArrayList<>();
		for (User user : allUsers) {
			convertedUsers.add(entity2model(user));
		}
		return new ResponseEntity<List<UserModel>>(convertedUsers, HttpStatus.OK);

	}

	private UserModel entity2model(User user) {
		UserModel userModel = new UserModel();
		userModel.setLogin(user.getLogin());
		userModel.setFirstName(user.getFirstName());
		userModel.setLastName(user.getLastName());
		return userModel;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable(value = "id") Integer userIdParam) {
		User user = userService.get(userIdParam);
		UserModel userModel = entity2model(user);
		return new ResponseEntity<UserModel>(userModel, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody UserModel userModel) {
		User user = model2entity(userModel);
		userService.save(user);
		return new ResponseEntity<IdModel>(new IdModel(user.getId()), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@RequestBody UserModel userModel,
			@PathVariable(value = "id") Integer userIdParam) {
		User user = userService.get(userIdParam);
		user.setLogin(userModel.getLogin());
		user.setPassword(userModel.getPassword());
		user.setFirstName(userModel.getFirstName());
		user.setLastName(userModel.getLastName());
		user.setEmail(userModel.getEmail());
		userService.save(user);
		return new ResponseEntity<IdModel>(HttpStatus.OK);
	}

	private User model2entity(UserModel userModel) {
		User user = new User();
		user.setLogin(userModel.getLogin());
		user.setPassword(userModel.getPassword());
		user.setRegistrated(new Timestamp(new Date().getTime()));
		user.setFirstName(userModel.getFirstName());
		user.setLastName(userModel.getLastName());
		user.setEmail(userModel.getEmail());
		user.setRole(UserRole.user);
		return user;
	}
}
