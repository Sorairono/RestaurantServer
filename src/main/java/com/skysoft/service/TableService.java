package com.skysoft.service;

import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.skysoft.core.*;
import com.skysoft.database.*;
import com.skysoft.model.*;
import com.skysoft.request.*;

public class TableService {
	TableDatabase tableDatabase;

	public TableService(MongoCollection<Document> mongoCollection) {
		tableDatabase = new TableDatabase(mongoCollection);
	}

	@SuppressWarnings("unused")
	private TableService() {

	}

	private static TableService instance;

	public static TableService getInstance() {
		if (instance == null) {
			synchronized (TableService.class) {
				if (instance == null) {
					MongoCollection<Document> mongoCollection = MongoDB.getDB()
							.getCollection(Constant.collection_tables);
					instance = new TableService(mongoCollection);
				}
			}
		}
		return instance;
	}
	
	public TableBillModel updateTableBill(TableModel table) {
		double subTotal = 0, tax, total, discount, balance;
		for (int i = 0; i < table.getCustomerProduct().size(); i++) {
			subTotal += table.getCustomerProduct().get(i).getTotalPrice();
		}
		tax = subTotal * 0.0825;
		total = subTotal + tax;
		if (table.getCoupon() != null) {
			if (table.getCoupon().isPercent()) {
				discount = total * table.getCoupon().getDiscountValue();
			} else {
				discount = table.getCoupon().getDiscountValue();
			}
		} else {
			discount = 0;
		}
		balance = total - discount;
		return new TableBillModel(table.getZoneLetter(), table.getTableNumber(), subTotal, tax, total, discount, balance);
	}
	

	public List<TableModel> getTablesList() {
		return tableDatabase.getTablesList();
	}
	
	public List<TableModel> getTablesListInZone(ZoneModel zone) {
		return tableDatabase.getTablesListInZone(zone);
	}
	
	public TableModel getTable(TableNumberModel model) {
		return tableDatabase.getTable(model);
	}
	
	public boolean openTable(TableNumberModel model) {
		return tableDatabase.openTable(model);
	}
	
	public boolean addToTableCart(AddToTableCart model) {
		return tableDatabase.addToTableCart(model);
	}
	
	public boolean changeAmountInCart(ChangeAmountInCart model) {
		return tableDatabase.changeAmountInCart(model);
	}
	
	public boolean deleteProductFromCart(DeleteProductFromCart model) {
		return tableDatabase.deleteProductFromCart(model);
	}
	
	public boolean sendOrder(SendOrderTableModel model) {
		return tableDatabase.sendOrder(model);
	}
	
//	public boolean addTable() {
//		return tableDatabase.addTable();
//	}
}
