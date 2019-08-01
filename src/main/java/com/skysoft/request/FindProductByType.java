package com.skysoft.request;

public class FindProductByType {
	private String category;
	private String type;

	public FindProductByType() {

	}

	public FindProductByType(String category, String type) {
		super();
		this.category = category;
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
