package com.payments.veritrans;

public class Commodity {
	private String
		commodityID,
		commodityUnitPrice,
		commodityQuantity,
		commodityName,
		commodityNameENG;

	public String getCommodityID() {
		return commodityID;
	}

	public void setCommodityID(String commodityID) {
		this.commodityID = commodityID;
	}

	public String getCommodityUnitPrice() {
		return commodityUnitPrice;
	}

	public void setCommodityUnitPrice(String commodityUnitPrice) {
		this.commodityUnitPrice = commodityUnitPrice;
	}

	public String getCommodityQuantity() {
		return commodityQuantity;
	}

	public void setCommodityQuantity(String commodityQuantity) {
		this.commodityQuantity = commodityQuantity;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getCommodityNameENG() {
		return commodityNameENG;
	}

	public void setCommodityNameENG(String commodityNameENG) {
		this.commodityNameENG = commodityNameENG;
	}
}
