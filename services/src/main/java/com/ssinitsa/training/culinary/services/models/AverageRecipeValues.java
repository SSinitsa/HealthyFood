package com.ssinitsa.training.culinary.services.models;

import java.math.BigDecimal;

public class AverageRecipeValues {

	private Integer calories;

	private BigDecimal fats;

	private BigDecimal proteins;

	private BigDecimal carbohydrates;

	public Integer getCalories() {
		return calories;
	}

	public void setCalories(Integer calories) {
		this.calories = calories;
	}

	public BigDecimal getFats() {
		return fats;
	}

	public void setFats(BigDecimal fats) {
		this.fats = fats;
	}

	public BigDecimal getProteins() {
		return proteins;
	}

	public void setProteins(BigDecimal proteins) {
		this.proteins = proteins;
	}

	public BigDecimal getCarbohydrates() {
		return carbohydrates;
	}

	public void setCarbohydrates(BigDecimal carbohydrates) {
		this.carbohydrates = carbohydrates;
	}

	@Override
	public String toString() {
		return "AverageRecipeValues [calories=" + calories + ", fats=" + fats + ", proteins=" + proteins
				+ ", carbohydrates=" + carbohydrates + "]";
	}

}
