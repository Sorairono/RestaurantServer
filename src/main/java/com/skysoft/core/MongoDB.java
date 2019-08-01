package com.skysoft.core;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDB {
	public static MongoDatabase getDB() {
		MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase mongoDatabase = mongoClient.getDatabase("restaurant");
		return mongoDatabase;
	}
}
