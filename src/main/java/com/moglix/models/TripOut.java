package com.moglix.models;

import java.util.List;

public class TripOut {

	private Plant plant;
	private String tripNumber;
	private Long date;
	private String vehicleNumber;
	private String lorryType;
	private Double weighBridgeWeight;
	private String gateSerialNo;
	private List<TripOutItem> items;

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}

	public String getTripNumber() {
		return tripNumber;
	}

	public void setTripNumber(String tripNumber) {
		this.tripNumber = tripNumber;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getLorryType() {
		return lorryType;
	}

	public void setLorryType(String lorryType) {
		this.lorryType = lorryType;
	}

	public Double getWeighBridgeWeight() {
		return weighBridgeWeight;
	}

	public void setWeighBridgeWeight(Double weighBridgeWeight) {
		this.weighBridgeWeight = weighBridgeWeight;
	}

	public List<TripOutItem> getItems() {
		return items;
	}

	public void setItems(List<TripOutItem> items) {
		this.items = items;
	}

	public String getGateSerialNo() { return gateSerialNo; }

	public void setGateSerialNo(String gateSerialNo) { this.gateSerialNo = gateSerialNo; }
}
