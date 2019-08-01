package com.skysoft.service;

import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.skysoft.core.Constant;
import com.skysoft.core.MongoDB;
import com.skysoft.database.ZoneDatabase;
import com.skysoft.model.ZoneModel;

public class ZoneService {
	ZoneDatabase zoneDatabase;

	public ZoneService(MongoCollection<Document> mongoCollection) {
		zoneDatabase = new ZoneDatabase(mongoCollection);
	}

	private static ZoneService instance;

	@SuppressWarnings("unused")
	private ZoneService() {

	}

	public static ZoneService getInstance() {
		if (instance == null) {
			synchronized (ZoneService.class) {
				if (instance == null) {
					MongoCollection<Document> mongoCollection = MongoDB.getDB()
							.getCollection(Constant.collection_zones);
					instance = new ZoneService(mongoCollection);
				}
			}
		}
		return instance;
	}

	public List<ZoneModel> getZonesList() {
		return zoneDatabase.getZonesList();
	}

}
