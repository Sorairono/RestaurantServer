package com.skysoft.service;

import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.skysoft.core.Constant;
import com.skysoft.core.MongoDB;
import com.skysoft.database.KitchenDatabase;
import com.skysoft.model.KitchenProductModel;

public class KitchenService {
	KitchenDatabase kitchenDatabase;

	public KitchenService(MongoCollection<Document> mongoCollection) {
		kitchenDatabase = new KitchenDatabase(mongoCollection);
	}

	@SuppressWarnings("unused")
	private KitchenService() {

	}

	private static KitchenService instance;

	public static KitchenService getInstance() {
		if (instance == null) {
			synchronized (KitchenService.class) {
				if (instance == null) {
					MongoCollection<Document> mongoCollection = MongoDB.getDB()
							.getCollection(Constant.collection_kitchen);
					instance = new KitchenService(mongoCollection);
				}
			}
		}
		return instance;
	}

	public boolean sendOrderToKitchen(List<KitchenProductModel> model) {
		return kitchenDatabase.sendOrderToKitchen(model);
	}
	
	public List<KitchenProductModel> getKitchenList() {
		return kitchenDatabase.getKitchenList();
	}
	
	public boolean orderReady(KitchenProductModel model) {
		return kitchenDatabase.orderReady(model);
	}
}
