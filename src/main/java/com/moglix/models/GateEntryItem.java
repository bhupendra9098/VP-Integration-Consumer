package com.moglix.models;

public class GateEntryItem {

	private String materialCode;
	private String materialName;
	private String standardPack;
	private String pendingQuantity;
	private String shippedQuantity;

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

	public String getStandardPack() {
		return standardPack;
	}

	public void setStandardPack(String standardPack) {
		this.standardPack = standardPack;
	}

	public String getPendingQuantity() {
		return pendingQuantity;
	}

	public void setPendingQuantity(String pendingQuantity) {
		this.pendingQuantity = pendingQuantity;
	}

	public String getShippedQuantity() {
		return shippedQuantity;
	}

	public void setShippedQuantity(String shippedQuantity) {
		this.shippedQuantity = shippedQuantity;
	}

}
