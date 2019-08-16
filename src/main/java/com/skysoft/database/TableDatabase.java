package com.skysoft.database;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.google.gson.Gson;
import com.skysoft.core.Constant;
import com.skysoft.model.CartProductModel;
import com.skysoft.model.TableModel;
import com.skysoft.model.ZoneModel;
import com.skysoft.request.AddToTableCart;
import com.skysoft.request.ChangeAmountInCart;
import com.skysoft.request.DeleteProductFromCart;
import com.skysoft.request.SendOrderTableModel;
import com.skysoft.request.TableBillModel;
import com.skysoft.request.TableNumberModel;
import com.skysoft.service.TableService;

import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

public class TableDatabase {
	private MongoCollection<Document> tables;
	Gson gson = new Gson();

	public TableDatabase(MongoCollection<Document> mongoCollection) {
		tables = mongoCollection;
	}

	public List<TableModel> getTablesList() {
		List<TableModel> tablesList = new ArrayList<TableModel>();
		for (Document doc : tables.find()) {
			String json = doc.toJson();
			try {
				TableModel newTable = gson.fromJson(json, TableModel.class);
				tablesList.add(newTable);
			} catch (Exception e) {

			}
		}
		return tablesList;
	}

	public List<TableModel> getTablesListInZone(ZoneModel zone) {
		List<TableModel> tablesListInZone = new ArrayList<TableModel>();
		for (Document doc : tables.find()) {
			String json = doc.toJson();
			try {
				TableModel newTable = gson.fromJson(json, TableModel.class);
				if (newTable.getZoneLetter().compareTo(zone.getZoneLetter()) == 0) {
					tablesListInZone.add(newTable);
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return tablesListInZone;
	}

	public TableModel getTable(TableNumberModel model) {
		Document doc = tables
				.find(and(eq("zoneLetter", model.getZoneLetter()), eq("tableNumber", model.getTableNumber()))).first();
		Gson gson = new Gson();
		String json = doc.toJson();
		try {
			TableModel table = gson.fromJson(json, TableModel.class);
			return table;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public boolean openTable(TableNumberModel model) {
		try {
			tables.updateOne(and(eq("zoneLetter", model.getZoneLetter()), eq("tableNumber", model.getTableNumber())),
					set("occupied", true));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public boolean addToTableCart(AddToTableCart model) {
		CartProductModel newCartProduct = new CartProductModel(model.getProduct().getProductName(),
				model.getProduct().getPrice(), null, null, 1, model.getProduct().getPrice(), model.getTableString(),
				false, false, false, "");
		try {
			tables.updateOne(and(eq("zoneLetter", model.getZoneLetter()), eq("tableNumber", model.getTableNumber())),
					push("customerProduct", Document.parse(gson.toJson(newCartProduct))));
			boolean updated = updateTableBill(TableService.getInstance().updateTableBill(gson.fromJson(tables
					.find(and(eq("zoneLetter", model.getZoneLetter()), eq("tableNumber", model.getTableNumber())))
					.first().toJson(), TableModel.class)));
			if (!updated) {
				return false;
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public boolean changeAmountInCart(ChangeAmountInCart model) {
		try {
			tables.updateOne(and(eq("zoneLetter", model.getZoneLetter()), eq("tableNumber", model.getTableNumber())),
					combine(set("customerProduct." + model.getProductIndex() + ".amount", model.getNewAmount()), set(
							"customerProduct." + model.getProductIndex() + ".totalPrice", model.getNewTotalPrice())));
			boolean updated = updateTableBill(TableService.getInstance().updateTableBill(gson.fromJson(tables
					.find(combine(eq("zoneLetter", model.getZoneLetter()), eq("tableNumber", model.getTableNumber())))
					.first().toJson(), TableModel.class)));
			if (!updated) {
				return false;
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public boolean deleteProductFromCart(DeleteProductFromCart model) {
		try {
			tables.updateOne(and(eq("zoneLetter", model.getZoneLetter()), eq("tableNumber", model.getTableNumber())),
					pull("customerProduct",
							new Document("productName", model.getProductName()).append("orderSent", false)));
			boolean updated = updateTableBill(TableService.getInstance().updateTableBill(gson.fromJson(tables
					.find(combine(eq("zoneLetter", model.getZoneLetter()), eq("tableNumber", model.getTableNumber())))
					.first().toJson(), TableModel.class)));
			if (!updated) {
				return false;
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public boolean sendOrder(SendOrderTableModel model) {
		try {
			List<Bson> updates = new ArrayList<Bson>();
			for (int i = model.getLowerBound(); i <= model.getUpperBound(); i++) {
				updates.add(combine(set("customerProduct." + i + ".orderSent", true),
						set("customerProduct." + i + ".status", "Waiting"),
						set("customerProduct." + i + ".timeOrdered", Constant.getCurrentTimeAsString())));
			}
			tables.updateOne(
					and(eq("zoneLetter", model.getZoneLetter()), eq("tableNumber", model.getTableNumber())),
					combine(updates));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	private boolean updateTableBill(TableBillModel model) {
		try {
			tables.updateOne(
					and(eq("zoneLetter", model.getZoneLetter()), eq("tableNumber", model.getTableNumber())),
					combine(set("subTotal", model.getSubTotal()), set("tax", model.getTax()),
							set("total", model.getTotal()), set("discount", model.getDiscount()),
							set("balance", model.getBalance())));
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

//	public boolean addTable() {
//		try {
//			for (int i = 1; i <= 15; i++) {
//				TableModel newTable = new TableModel("A", i, 4, false, new ArrayList<CartProductModel>(), null, 0, 0, 0, 0, 0, false, false, false, "");
//				tables.insertOne(Document.parse(gson.toJson(newTable)));
//			}
//			for (int i = 1; i <= 25; i++) {
//				TableModel newTable = new TableModel("B", i, 6, false, new ArrayList<CartProductModel>(), null, 0, 0, 0, 0, 0, false, false, false, "");
//				tables.insertOne(Document.parse(gson.toJson(newTable)));
//			}
//			for (int i = 1; i <= 20; i++) {
//				TableModel newTable = new TableModel("C", i, 8, true, new ArrayList<CartProductModel>(), null, 0, 0, 0, 0, 0, false, false, false, "");
//				tables.insertOne(Document.parse(gson.toJson(newTable)));
//			}
//			for (int i = 1; i <= 10; i++) {
//				TableModel newTable = new TableModel("Z", i, 4, true, new ArrayList<CartProductModel>(), null, 0, 0, 0, 0, 0, false, false, false, "");
//				tables.insertOne(Document.parse(gson.toJson(newTable)));
//			}
//			return true;
//		} catch (Exception e) {
//			// TODO: handle exception
//			return false;
//		}
//	}

}
