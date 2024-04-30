package com.moglix.mapper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moglix.models.Plant;
import com.moglix.models.TripOut;
import com.moglix.models.TripOutItem;
import com.moglix.utils.DateUtils;
import com.moglix.utils.NumberValidationUtils;

public class TripOutMapper {
	
	private static final Logger logger = LoggerFactory.getLogger(TripOutMapper.class);
	
	private static ObjectMapper mapper = new ObjectMapper();

	public static TripOut getTripOutObject(String request){
		TripOut tripOut = new TripOut();
		try {
			JsonNode root = mapper.readTree(request);
			Plant plant = new Plant();
			if(root.has("plantCode")) plant.setCode(root.get("plantCode").asText().trim());
			if(root.has("plantName")) plant.setName(root.get("plantName").asText().trim());
			tripOut.setPlant(plant);
			if(root.has("tripNumber")) tripOut.setTripNumber(root.get("tripNumber").asText().trim());
			if(root.has("date") && NumberValidationUtils.isDouble(root.get("date").asText().trim()) ) tripOut.setDate(DateUtils.convertDateToTimestamp(root.get("date").asText().trim()));
			if(root.has("vehicleNumber")) tripOut.setVehicleNumber(root.get("vehicleNumber").asText().trim());
			if(root.has("gateSerialNo")) tripOut.setGateSerialNo(root.get("gateSerialNo").asText().trim());
			if(root.has("lorryType")) tripOut.setLorryType(root.get("lorryType").asText().trim());
			if(root.has("weighBridgeWeight") && NumberValidationUtils.isDouble(root.get("weighBridgeWeight").asText().trim())) tripOut.setWeighBridgeWeight(Double.parseDouble(root.get("weighBridgeWeight").asText().trim()));
			List<TripOutItem> tripOutItems = null;
			if(root.has("items")){
				tripOutItems = new ArrayList<TripOutItem>();
				JsonNode items = root.get("items");
				if(items.isArray()){
					for(JsonNode itemJsonNode: items){
						tripOutItems.add(getTripOutItemObject(itemJsonNode));
					}
				} else {
					tripOutItems.add(getTripOutItemObject(items));
				}
			}
			tripOut.setItems(tripOutItems);
		} catch(Exception e) {
			logger.debug("exception occured at while mapping json "+e.getMessage());
		}
		return tripOut;
	}
	
	public static TripOutItem getTripOutItemObject(JsonNode tripOutItemNode){
		TripOutItem tripOutItem = new TripOutItem();
		if(tripOutItemNode.has("documentNumber")) tripOutItem.setDocumentNumber(tripOutItemNode.get("documentNumber").asText().trim());
		if(tripOutItemNode.has("materialCode")) tripOutItem.setMaterialCode(tripOutItemNode.get("materialCode").asText().trim());
		if(tripOutItemNode.has("materialName")) tripOutItem.setMaterialName(tripOutItemNode.get("materialName").asText().trim());
		return tripOutItem;
	}
	
}
