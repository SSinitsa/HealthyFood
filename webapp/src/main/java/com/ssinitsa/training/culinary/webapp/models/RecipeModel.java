package com.ssinitsa.training.culinary.webapp.models;

import java.sql.Timestamp;

import com.ssinitsa.training.culinary.datamodel.DishCategory;

public class RecipeModel {

	private Integer id;

	private String name;

	private String description;

	private Integer authorId;

	private String author;

	private Timestamp created;

	private DishCategory category;

	private boolean vegetarian;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public DishCategory getCategory() {
		return category;
	}

	public void setCategory(DishCategory category) {
		this.category = category;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	@Override
	public String toString() {
		return "RecipeModel [id=" + id + ", name=" + name + ", description=" + description + ", authorId=" + authorId
				+ ", author=" + author + ", created=" + created + ", category=" + category + ", vegetarian="
				+ vegetarian + "]";
	}

}
