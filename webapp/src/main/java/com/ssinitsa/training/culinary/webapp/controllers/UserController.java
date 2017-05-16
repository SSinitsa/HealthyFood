package com.ssinitsa.training.culinary.webapp.controllers;

import java.util.ArrayList;
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
import com.ssinitsa.training.culinary.services.IUserService;
import com.ssinitsa.training.culinary.webapp.converters.User2UserModel;
import com.ssinitsa.training.culinary.webapp.models.IdModel;
import com.ssinitsa.training.culinary.webapp.models.UserModel;

@RestController
@RequestMapping("/users")
public class UserController {

	@Inject
	private IUserService userService;
	
	@Inject
	private User2UserModel entityConverter;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		List<User> allUsers = userService.getAll();
		List<UserModel> convertedUsers = new ArrayList<>();
		for (User user : allUsers) {
			user.setPassword("hidden");
			convertedUsers.add(entityConverter.user2userModel(user));
		}
		return new ResponseEntity<List<UserModel>>(convertedUsers, HttpStatus.OK);

	}


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable(value = "id") Integer userIdParam) {
		User user = userService.get(userIdParam);
		UserModel userModel = entityConverter.user2userModel(user);
		userModel.setPassword("hidden");
		return new ResponseEntity<UserModel>(userModel, HttpStatus.OK);
	}

	/*@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody UserModel userModel) {
		User user = model2entity(userModel);
		userService.save(user);
		return new ResponseEntity<IdModel>(new IdModel(user.getId()), HttpStatus.CREATED);
	}*/

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

	
}
