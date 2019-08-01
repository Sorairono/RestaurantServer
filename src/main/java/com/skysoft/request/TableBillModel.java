package com.skysoft.request;

public class TableBillModel {
	private String zoneLetter;
	private int tableNumber;
	private double subTotal;
	private double tax;
	private double total;
	private double discount;
	private double balance;

	public TableBillModel() {

	}

	public TableBillModel(String zoneLetter, int tableNumber, double subTotal, double tax, double total,
			double discount, double balance) {
		super();
		this.zoneLetter = zoneLetter;
		this.tableNumber = tableNumber;
		this.subTotal = subTotal;
		this.tax = tax;
		this.total = total;
		this.discount = discount;
		this.balance = balance;
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

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}
