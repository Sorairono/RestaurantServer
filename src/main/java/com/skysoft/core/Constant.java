package com.skysoft.core;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Constant {
	public static final String collection_tables = "tables";
	public static final String collection_products = "products";
	public static final String collection_categories = "categories";
	public static final String collection_zones = "zones";
	public static final String collection_kitchen = "kitchen";

	public static String getCurrentTimeAsString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime now = LocalTime.now();
		return now.format(dtf);
	}
}
