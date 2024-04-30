package com.moglix.models;

import java.util.List;

public class AgreementItem {

	private String itemNo; // done
	private String materialCode; // d
	private String materialName; // d
	private String category; // optional, can be sent if required
	private Double quantity; // done -- double
	private Double price; // per item value
	private Double netValue; // done
	private String uom;// done
	private String storageLocation;// done
	private Double standardPackSize;// optinal, no development from sap side
	private String plantCode; // at item level , can be different for each item
								// - to be confirmed
	private String plantName; // done
	private Double tolerance;
	private String shippingPlantCode;
	private String shippingPlantName;
	private String deliveryDate;
	private String hsnCode;
	private String netWeight;
	private List<Conditions> conditions;
	private String deliveryCompletionCheck;

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getNetValue() {
		return netValue;
	}

	public void setNetValue(Double netValue) {
		this.netValue = netValue;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getStorageLocation() {
		return storageLocation;
	}

	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}

	public Double getStandardPackSize() {
		return standardPackSize;
	}

	public void setStandardPackSize(Double standardPackSize) {
		this.standardPackSize = standardPackSize;
	}

	public String getPlantCode() {
		return plantCode;
	}

	public void setPlantCode(String plantCode) {
		this.plantCode = plantCode;
	}

	public String getPlantName() {
		return plantName;
	}

	public void setPlantName(String plantName) {
		this.plantName = plantName;
	}

	public String getShippingPlantCode() {
		return shippingPlantCode;
	}

	public void setShippingPlantCode(String shippingPlantCode) {
		this.shippingPlantCode = shippingPlantCode;
	}

	public String getShippingPlantName() {
		return shippingPlantName;
	}

	public Double getTolerance() {
		return tolerance;
	}

	public void setTolerance(Double tolerance) {
		this.tolerance = tolerance;
	}

	public void setShippingPlantName(String shippingPlantName) {
		this.shippingPlantName = shippingPlantName;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public String getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(String netWeight) {
		this.netWeight = netWeight;
	}

	public List<Conditions> getConditions() {
		return conditions;
	}

	public void setConditions(List<Conditions> conditions) {
		this.conditions = conditions;
	}

	public String getDeliveryCompletionCheck() { return deliveryCompletionCheck; }

	public void setDeliveryCompletionCheck(String deliveryCompletionCheck) { this.deliveryCompletionCheck = deliveryCompletionCheck; }
}
