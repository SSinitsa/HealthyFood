package com.ssinitsa.training.culinary.webapp.converters;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ssinitsa.training.culinary.datamodel.User;
import com.ssinitsa.training.culinary.webapp.models.UserModel;

@Component
public class UserModel2User extends Model2Json<UserModel>{
	
	private final static String baseFile = "storage.json";
	private User user;
	
	public User userModel2user(UserModel userModel){
		
		model2json(userModel);
		try {
			user = mapper.readValue(new File(baseFile), User.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

}
