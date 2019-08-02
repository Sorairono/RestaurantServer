package com.skysoft.service;

import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.skysoft.core.Constant;
import com.skysoft.core.MongoDB;
import com.skysoft.database.ProductDatabase;
import com.skysoft.model.ProductModel;
import com.skysoft.request.FindProductByType;

public class ProductService {
	ProductDatabase productDatabase;

	public ProductService(MongoCollection<Document> mongoCollection) {
		productDatabase = new ProductDatabase(mongoCollection);
	}

	@SuppressWarnings("unused")
	private ProductService() {

	}

	private static ProductService instance;

	public static ProductService getInstance() {
		if (instance == null) {
			synchronized (ProductService.class) {
				if (instance == null) {
					MongoCollection<Document> mongoCollection = MongoDB.getDB()
							.getCollection(Constant.collection_products);
					instance = new ProductService(mongoCollection);
				}
			}
		}
		return instance;
	}

	public List<ProductModel> getProductsList() {
		return productDatabase.getProductsList();
	}
	
	public List<ProductModel> getProductsListByType(FindProductByType model) {
		return productDatabase.getProductsListByType(model);
	}
	
//	public boolean addProduct() {
//		return productDatabase.addProduct();
//	}
//	
//	public void initializeProducts() {
//		productDatabase.initializeProducts();
//	}
}
