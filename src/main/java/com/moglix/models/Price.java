package com.moglix.models;

public class Price {

	private String qtyPerPrice;
	private String qtyPerPriceUom;
	private String pricePerQty;
	private String currency;

	public String getQtyPerPrice() {
		return qtyPerPrice;
	}

	public void setQtyPerPrice(String qtyPerPrice) {
		this.qtyPerPrice = qtyPerPrice;
	}

	public String getQtyPerPriceUom() {
		return qtyPerPriceUom;
	}

	public void setQtyPerPriceUom(String qtyPerPriceUom) {
		this.qtyPerPriceUom = qtyPerPriceUom;
	}

	public String getPricePerQty() {
		return pricePerQty;
	}

	public void setPricePerQty(String pricePerQty) {
		this.pricePerQty = pricePerQty;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
}
