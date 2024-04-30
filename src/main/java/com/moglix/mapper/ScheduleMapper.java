package com.moglix.mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.moglix.models.Schedule;
import com.moglix.models.SchedulingList;
import com.moglix.utils.DateUtils;
import com.moglix.utils.NumberValidationUtils;

public class ScheduleMapper {
	
	static ObjectMapper mapper = new ObjectMapper();
	
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(ScheduleMapper.class);

	public static JsonNode fromSchObjectToVpSchList(Schedule scheduleBody){
		ArrayNode schedulingListJson = mapper.createArrayNode();
		if(scheduleBody.getSchedulingList()!=null){
			Iterator<SchedulingList> it = scheduleBody.getSchedulingList().iterator();
			while(it.hasNext()){
				schedulingListJson.add(createSchedulingListJson(it.next()));
			}
		}
		return schedulingListJson;
	}
	
	private static JsonNode createSchedulingListJson(SchedulingList schedulingList){
		JsonNode schedulingListJsonNode = mapper.valueToTree(schedulingList);
		ObjectNode schedulingListJsonObject = mapper.createObjectNode();
		Iterator<Entry<String, JsonNode>> it = schedulingListJsonNode.fields();
		while(it.hasNext()){
			Entry<String, JsonNode> entry = it.next();
			if(entry.getKey().equals("scheduledQuantity") || entry.getKey().equals("price")){
				if(!entry.getValue().isNull()) schedulingListJsonObject.put(entry.getKey(), entry.getValue().asDouble());
			} else if(entry.getKey().equals("scheduledDate")) {
				if(!entry.getValue().isNull()) schedulingListJsonObject.put(entry.getKey(),DateUtils.convertDateToTimestamp(entry.getValue().asText()));
			} else {
				if(!entry.getValue().isNull()) schedulingListJsonObject.put(entry.getKey(), entry.getValue().asText());
			}
		}
		ObjectNode vendorObject = mapper.createObjectNode(), companyObject = mapper.createObjectNode(), plantObject = mapper.createObjectNode(), priceObject = mapper.createObjectNode(), grnQuantity = mapper.createObjectNode();
		if(schedulingListJsonObject.has("vendorCode")) vendorObject.put("code", schedulingListJsonObject.get("vendorCode").asText());
		else vendorObject.put("code", "");
		if(schedulingListJsonObject.has("vendorName")) vendorObject.put("name", schedulingListJsonObject.get("vendorName").asText());
		else vendorObject.put("name", "");
		if(schedulingListJsonObject.has("companyCode")) companyObject.put("code", schedulingListJsonObject.get("companyCode").asText());
		else companyObject.put("code", "");
		if(schedulingListJsonObject.has("companyName")) companyObject.put("name", schedulingListJsonObject.get("companyName").asText());
		else companyObject.put("name", "");
		if(schedulingListJsonObject.has("plantCode")) plantObject.put("code", schedulingListJsonObject.get("plantCode").asText());
		else plantObject.put("code", "");
		if(schedulingListJsonObject.has("plantName")) plantObject.put("name", schedulingListJsonObject.get("plantName").asText());
		else plantObject.put("name", "");
		if(schedulingListJsonObject.has("price")) priceObject.put("price", Double.parseDouble(schedulingListJsonObject.get("price").asText()));
		else priceObject.put("price", "");
		if(schedulingListJsonObject.has("currency")) priceObject.put("currency", schedulingListJsonObject.get("currency").asText());
		else priceObject.put("currency", "");
		if(schedulingListJsonObject.has("receivedQuantity")) {
			grnQuantity.put("receivedQuantity", Double.parseDouble(schedulingListJsonObject.get("receivedQuantity").asText()));
			grnQuantity.put("sapReceivedQuantity", Double.parseDouble(schedulingListJsonObject.get("receivedQuantity").asText()));
		}
		schedulingListJsonObject.remove("vendorCode");
		schedulingListJsonObject.remove("vendorName");
		schedulingListJsonObject.remove("companyCode");
		schedulingListJsonObject.remove("companyName");
		schedulingListJsonObject.remove("plantCode");
		schedulingListJsonObject.remove("plantName");
		schedulingListJsonObject.remove("price");
		schedulingListJsonObject.remove("currency");
		schedulingListJsonObject.remove("receivedQuantity");
		schedulingListJsonObject.putPOJO("vendor", vendorObject);
		schedulingListJsonObject.putPOJO("company", companyObject);
		schedulingListJsonObject.putPOJO("plant", plantObject);
		schedulingListJsonObject.putPOJO("price", priceObject);
		schedulingListJsonObject.putPOJO("grnQuantity", grnQuantity);
		return schedulingListJsonObject;
	}
	
	public static Schedule makeScheduleObjectFromJsonNode(String request){
		Schedule schedule = new Schedule();
		try {
			JsonNode root = mapper.readTree(request);
			if(root.has("agreementNo") && !root.path("agreementNo").asText().isEmpty()) schedule.setAgreementNo(root.path("agreementNo").asText().trim());
			if(root.has("schedulingList")){
				List<SchedulingList> SchedulingList = new ArrayList<>();
				if(root.path("schedulingList").isArray()){
					for(JsonNode scheduleListJsonNode: root.get("schedulingList")){
						SchedulingList.add(makeSchedulingListData(scheduleListJsonNode));
					}
				} else {
					JsonNode scheduleListJsonNode = root.get("schedulingList");
					SchedulingList.add(makeSchedulingListData(scheduleListJsonNode));
				}
				schedule.setSchedulingList(SchedulingList);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return schedule;
	}
	
	private static SchedulingList makeSchedulingListData(JsonNode schedulingListJsonNode){
		SchedulingList SchedulingList = new SchedulingList();
		if(schedulingListJsonNode.has("agreementNo") && !schedulingListJsonNode.path("agreementNo").asText().trim().isEmpty()) SchedulingList.setAgreementNo(schedulingListJsonNode.path("agreementNo").asText().trim());
		if(schedulingListJsonNode.has("materialCode") && !schedulingListJsonNode.path("materialCode").asText().trim().isEmpty()) SchedulingList.setMaterialCode(schedulingListJsonNode.path("materialCode").asText().trim());
		if(schedulingListJsonNode.has("materialName") && !schedulingListJsonNode.path("materialName").asText().trim().isEmpty()) SchedulingList.setMaterialName(schedulingListJsonNode.path("materialName").asText().trim());
		if(schedulingListJsonNode.has("hsnCode") && !schedulingListJsonNode.path("hsnCode").asText().trim().isEmpty()) SchedulingList.setHsnCode(schedulingListJsonNode.path("hsnCode").asText().trim());
		if(schedulingListJsonNode.has("scheduledDate") && !schedulingListJsonNode.path("scheduledDate").asText().trim().isEmpty() && NumberValidationUtils.isDouble(schedulingListJsonNode.path("scheduledDate").asText().trim())) 
			SchedulingList.setScheduledDate(schedulingListJsonNode.path("scheduledDate").asText().trim());
		if(schedulingListJsonNode.has("scheduledQuantity") && !schedulingListJsonNode.path("scheduledQuantity").asText().trim().isEmpty() && NumberValidationUtils.isDouble(schedulingListJsonNode.path("scheduledQuantity").asText().trim())) 
			SchedulingList.setScheduledQuantity(Double.parseDouble(schedulingListJsonNode.path("scheduledQuantity").asText().trim()));
		if(schedulingListJsonNode.has("receivedQuantity") && !schedulingListJsonNode.path("receivedQuantity").asText().trim().isEmpty() && NumberValidationUtils.isDouble(schedulingListJsonNode.path("receivedQuantity").asText().trim()))
			SchedulingList.setReceivedQuantity(Double.parseDouble(schedulingListJsonNode.path("receivedQuantity").asText().trim()));
		if(schedulingListJsonNode.has("vendorCode") && !schedulingListJsonNode.path("vendorCode").asText().trim().isEmpty()) SchedulingList.setVendorCode(schedulingListJsonNode.path("vendorCode").asText().trim());
		if(schedulingListJsonNode.has("vendorName") && !schedulingListJsonNode.path("vendorName").asText().trim().isEmpty()) SchedulingList.setVendorName(schedulingListJsonNode.path("vendorName").asText().trim());
		if(schedulingListJsonNode.has("plantCode") && !schedulingListJsonNode.path("plantCode").asText().trim().isEmpty()) SchedulingList.setPlantCode(schedulingListJsonNode.path("plantCode").asText().trim());
		if(schedulingListJsonNode.has("plantName") && !schedulingListJsonNode.path("plantName").asText().trim().isEmpty()) SchedulingList.setPlantName(schedulingListJsonNode.path("plantName").asText().trim());
		if(schedulingListJsonNode.has("companyCode") && !schedulingListJsonNode.path("companyCode").asText().trim().isEmpty()) SchedulingList.setCompanyCode(schedulingListJsonNode.path("companyCode").asText().trim());
		if(schedulingListJsonNode.has("companyName") && !schedulingListJsonNode.path("companyName").asText().trim().isEmpty()) SchedulingList.setCompanyName(schedulingListJsonNode.path("companyName").asText().trim());
		if(schedulingListJsonNode.has("lineItemNumber") && !schedulingListJsonNode.path("lineItemNumber").asText().trim().isEmpty()) SchedulingList.setLineItemNumber(schedulingListJsonNode.path("lineItemNumber").asText().trim());
		if(schedulingListJsonNode.has("scheduleNumber") && !schedulingListJsonNode.path("scheduleNumber").asText().trim().isEmpty()) SchedulingList.setScheduleNumber(schedulingListJsonNode.path("scheduleNumber").asText().trim());
		if(schedulingListJsonNode.has("price") && !schedulingListJsonNode.path("price").asText().trim().isEmpty() && NumberValidationUtils.isDouble(schedulingListJsonNode.path("price").asText().trim())) 
			SchedulingList.setPrice(Double.parseDouble(schedulingListJsonNode.path("price").asText().trim()));
		if(schedulingListJsonNode.has("uom") && !schedulingListJsonNode.path("uom").asText().trim().isEmpty()) SchedulingList.setUom(schedulingListJsonNode.path("uom").asText().trim());
		if(schedulingListJsonNode.has("currency") && !schedulingListJsonNode.path("currency").asText().trim().isEmpty()) SchedulingList.setCurrency(schedulingListJsonNode.path("currency").asText().trim());
		return SchedulingList;
	}
	
}
