package com.ssinitsa.training.culinary.dao.api.filter;

public class RecipeFilter {

	private Integer authorId;

	private String category;

	private Boolean vegetarian;

	private Integer ingredientId;
	
	private Integer caloryFrom;
	
	private Integer caloryTo;

	public Integer getCaloryFrom() {
		return caloryFrom;
	}

	public void setCaloryFrom(Integer caloryFrom) {
		this.caloryFrom = caloryFrom;
	}

	public Integer getCaloryTo() {
		return caloryTo;
	}

	public void setCaloryTo(Integer caloryTo) {
		this.caloryTo = caloryTo;
	}

	public Integer getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(Integer ingredientId) {
		this.ingredientId = ingredientId;
	}

	private Integer limit;
	private Integer offset;

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Boolean getVegetarian() {
		return vegetarian;
	}

	public void setVegetarian(Boolean vegetarian) {
		this.vegetarian = vegetarian;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public boolean isEmpty() {
		return authorId == null && category == null && vegetarian == null && ingredientId == null;
	}

	@Override
	public String toString() {
		return "RecipeFilter [authorId=" + authorId + ", category=" + category + ", vegetarian=" + vegetarian
				+ ", ingredientId=" + ingredientId + ", limit=" + limit + ", offset=" + offset + "]";
	}

}
