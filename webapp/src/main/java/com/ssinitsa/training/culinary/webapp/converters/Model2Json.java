package com.ssinitsa.training.culinary.webapp.converters;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Model2Json<T> {

	private final static String baseFile = "storage.json";
	
	ObjectMapper mapper = new ObjectMapper();

	public void model2json(T model) {
		
		try {
			mapper.writeValue(new File(baseFile), model);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
