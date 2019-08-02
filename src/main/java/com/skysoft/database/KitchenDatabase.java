package com.skysoft.database;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.skysoft.core.Constant;
import com.skysoft.model.KitchenProductModel;

import static com.mongodb.client.model.Filters.*;

public class KitchenDatabase {
	private MongoCollection<Document> kitchen;
	Gson gson = new Gson();

	public KitchenDatabase(MongoCollection<Document> mongoCollection) {
		kitchen = mongoCollection;
	}

	public boolean sendOrderToKitchen(List<KitchenProductModel> model) {
		try {
			List<Document> newProductList = new ArrayList<Document>();
			for (int i = 0; i < model.size(); i++) {
				try {
					KitchenProductModel newKitchenProduct = model.get(i);
					newKitchenProduct.setTimeOrdered(Constant.getCurrentTimeAsString());
					newProductList.add(Document.parse(gson.toJson(newKitchenProduct)));
				} catch (Exception e) {
					// TODO: handle exception
					return false;
				}
			}
			kitchen.insertMany(newProductList);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public List<KitchenProductModel> getKitchenList() {
		try {
			List<KitchenProductModel> kitchenList = new ArrayList<KitchenProductModel>();
			for (Document doc : kitchen.find()) {
				KitchenProductModel newKitchenProduct = gson.fromJson(doc.toJson(), KitchenProductModel.class);
				kitchenList.add(newKitchenProduct);
			}
			return kitchenList;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public boolean orderReady(KitchenProductModel model) {
		try {
			kitchen.deleteOne(and(eq("productName", model.getProductName()),
					eq("timeOrdered", model.getTimeOrdered()), eq("amount", model.getAmount()),
							eq("zoneLetter", model.getZoneLetter()), eq("tableNumber", model.getTableNumber())));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

}
