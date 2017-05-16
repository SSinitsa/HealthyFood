package com.ssinitsa.training.culinary.datamodel;

public class Ingredient {
	
	private Integer id;
	private String name;
	private Integer calories;
	private Double fats;
	private Double proteins;
	private Double carbohydrates;
	private IngredientCategory category;
	

	public Ingredient(Integer id, String name, Integer calories, Double fats, Double proteins, Double carbohydrates,
			IngredientCategory category) {
		this.id = id;
		this.name = name;
		this.calories = calories;
		this.fats = fats;
		this.proteins = proteins;
		this.carbohydrates = carbohydrates;
		this.category = category;
	}

	public Ingredient() {
		// TODO Auto-generated constructor stub
	}

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

	public IngredientCategory getCategory() {
		return category;
	}

	public void setCategory(IngredientCategory category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", name=" + name + ", calories=" + calories + ", fats=" + fats + ", proteins="
				+ proteins + ", carbohydrates=" + carbohydrates + ", category=" + category + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((calories == null) ? 0 : calories.hashCode());
		result = prime * result + ((carbohydrates == null) ? 0 : carbohydrates.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((fats == null) ? 0 : fats.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((proteins == null) ? 0 : proteins.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingredient other = (Ingredient) obj;
		if (calories == null) {
			if (other.calories != null)
				return false;
		} else if (!calories.equals(other.calories))
			return false;
		if (carbohydrates == null) {
			if (other.carbohydrates != null)
				return false;
		} else if (!carbohydrates.equals(other.carbohydrates))
			return false;
		if (category != other.category)
			return false;
		if (fats == null) {
			if (other.fats != null)
				return false;
		} else if (!fats.equals(other.fats))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (proteins == null) {
			if (other.proteins != null)
				return false;
		} else if (!proteins.equals(other.proteins))
			return false;
		return true;
	}

	
}
