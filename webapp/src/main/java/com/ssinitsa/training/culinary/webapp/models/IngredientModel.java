package com.ssinitsa.training.culinary.webapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssinitsa.training.culinary.datamodel.IngredientCategory;

public class IngredientModel {
	
	private Integer id;
	private String name;
	private Integer calories;
	private Double fats;
	private Double proteins;
	private Double carbohydrates;
	private String category;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCalories() {
		return calories;
	}

	
	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	public Double getFats() {
		return fats;
	}

	public void setFats(Double fats) {
		this.fats = fats;
	}

	public Double getProteins() {
		return proteins;
	}

	public void setProteins(Double proteins) {
		this.proteins = proteins;
	}

	public Double getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(Double carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
