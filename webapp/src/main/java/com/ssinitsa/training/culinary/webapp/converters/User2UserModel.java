package com.ssinitsa.training.culinary.webapp.converters;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ssinitsa.training.culinary.datamodel.User;
import com.ssinitsa.training.culinary.webapp.models.UserModel;

@Component
public class User2UserModel extends Model2Json<User>{
	
	private final static String baseFile = "storage.json";
	private UserModel userModel;
	
	public UserModel user2userModel(User user){
		System.out.println(user);
		model2json(user);
		try {
			userModel = mapper.readValue(new File(baseFile), UserModel.class);
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
		System.out.println(userModel);
		return userModel;
	}

}
