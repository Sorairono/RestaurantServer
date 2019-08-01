package com.skysoft.database;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.skysoft.model.ZoneModel;

public class ZoneDatabase {
	private MongoCollection<Document> zones;
	
	public ZoneDatabase(MongoCollection<Document> mongoCollection) {
		zones = mongoCollection;
	}
	
	public List<ZoneModel> getZonesList() {
		List<ZoneModel> zonesList = new ArrayList<ZoneModel>();
		Gson gson = new Gson();
		for (Document doc : zones.find()) {
			String json = doc.toJson();
			try {
				ZoneModel newZone = gson.fromJson(json, ZoneModel.class);
				zonesList.add(newZone);
			} catch (Exception e) {

			}
		}
		return zonesList;
	}
}
