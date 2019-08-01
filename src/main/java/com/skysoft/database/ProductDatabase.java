package com.skysoft.database;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.skysoft.model.ProductModel;
import com.skysoft.request.FindProductByType;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.*;

public class ProductDatabase {
	private MongoCollection<Document> products;
	Gson gson = new Gson();

	public ProductDatabase(MongoCollection<Document> mongoCollection) {
		products = mongoCollection;
	}

	public List<ProductModel> getProductsList() {
		List<ProductModel> productsList = new ArrayList<ProductModel>();
		for (Document doc : products.find()) {
			String json = doc.toJson();
			try {
				ProductModel newProduct = gson.fromJson(json, ProductModel.class);
				productsList.add(newProduct);
			} catch (Exception e) {

			}
		}
		return productsList;
	}

	public List<ProductModel> getProductsListByType(FindProductByType model) {
		List<ProductModel> productsListByType = new ArrayList<ProductModel>();
		for (Document doc : products.find(
				combine(eq("category", model.getCategory()), eq("type", model.getType())))) {
			String json = doc.toJson();
			try {
				ProductModel newProduct = gson.fromJson(json, ProductModel.class);
				productsListByType.add(newProduct);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return productsListByType;
	}

//	public boolean addProduct() {
//		try {
//			MongoCollection<Document> categories = MongoDB.getDB().getCollection(Constant.collection_categories);
//			for (Document doc : categories.find()) {
//				CategoryModel category = gson.fromJson(doc.toJson(), CategoryModel.class);
//				for (String type : category.getTypes()) {
//					for (int i = 1; i <= 15; i++) {
//						ProductModel newProduct = new ProductModel(type + " " + category.getName() + " " + i, 9.99, category.getName(), type);
//						products.insertOne(Document.parse(gson.toJson(newProduct)));
//					}
//				}
//			}
//			return true;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		}
//	}

//	public void initializeProducts() {
//		MongoCollection<Document> categories = MongoDB.getDB().getCollection(Constant.collection_categories);
//		Gson gson = new Gson();
//		for (Document doc : categories.find()) {
//			String json = doc.toJson();
//			try {
//				CategoryModel category = gson.fromJson(json, CategoryModel.class);
//				for (String type : category.getTypes()) {
//					List<Document> productList = new ArrayList<Document>();
//					for (int i = 1; i <= 15; i++) {
//						Document newProduct = new Document();
//						newProduct.append("productName", type + " " + category.toString() + " " + i);
//						newProduct.append("price", 5.99);
//						newProduct.append("category", category.toString());
//						newProduct.append("type", type);
//						productList.add(newProduct);
//					}
//					products.insertMany(productList);
//				}
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		}
//	}
}
