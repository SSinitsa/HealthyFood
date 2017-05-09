package com.ssinitsa.training.culinary.datamodel;

import java.sql.Timestamp;

public class RecipeWithFeedback {

	private Recipe recipe;

	private String text;

	private User user;

	private Timestamp created;

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "RecipeWithFeedback [recipe=" + recipe + ", text=" + text + ", user=" + user + ", created=" + created
				+ "]";
	}
	
	

}
