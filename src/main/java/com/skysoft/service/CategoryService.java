package com.skysoft.service;

import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.skysoft.core.Constant;
import com.skysoft.core.MongoDB;
import com.skysoft.database.CategoryDatabase;
import com.skysoft.model.CategoryModel;

public class CategoryService {
	CategoryDatabase categoryDatabase;

	public CategoryService(MongoCollection<Document> mongoCollection) {
		categoryDatabase = new CategoryDatabase(mongoCollection);
	}

	@SuppressWarnings("unused")
	private CategoryService() {

	}

	private static CategoryService instance;

	public static CategoryService getInstance() {
		if (instance == null) {
			synchronized (CategoryService.class) {
				if (instance == null) {
					MongoCollection<Document> mongoCollection = MongoDB.getDB()
							.getCollection(Constant.collection_categories);
					instance = new CategoryService(mongoCollection);
				}
			}
		}
		return instance;
	}

	public List<CategoryModel> getCategoriesList() {
		return categoryDatabase.getCategoriesList();
	}
}
