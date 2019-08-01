package com.skysoft.database;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.skysoft.model.CategoryModel;

public class CategoryDatabase {
	private MongoCollection<Document> categories;
	
	public CategoryDatabase(MongoCollection<Document> mongoCollection) {
		categories = mongoCollection;
	}
	
	public List<CategoryModel> getCategoriesList() {
		List<CategoryModel> categoriesList = new ArrayList<CategoryModel>();
		Gson gson = new Gson();
		for (Document doc : categories.find()) {
			String json = doc.toJson();
			try {
				CategoryModel newCategory = gson.fromJson(json, CategoryModel.class);
				categoriesList.add(newCategory);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return categoriesList;
	}
}
