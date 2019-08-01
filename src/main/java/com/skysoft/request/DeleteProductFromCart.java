package com.skysoft.request;

public class DeleteProductFromCart {
	private String zoneLetter;
	private int tableNumber;
	private String productName;
	private boolean orderSent;

	public DeleteProductFromCart() {

	}

	public DeleteProductFromCart(String zoneLetter, int tableNumber, String productName, boolean orderSent) {
		super();
		this.zoneLetter = zoneLetter;
		this.tableNumber = tableNumber;
		this.productName = productName;
		this.orderSent = orderSent;
	}

	public String getZoneLetter() {
		return zoneLetter;
	}

	public void setZoneLetter(String zoneLetter) {
		this.zoneLetter = zoneLetter;
	}

	public int getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(int tableNumber) {
		this.tableNumber = tableNumber;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public boolean isOrderSent() {
		return orderSent;
	}

	public void setOrderSent(boolean orderSent) {
		this.orderSent = orderSent;
	}

}
