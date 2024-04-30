package com.moglix.mapper;

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
import com.moglix.models.Agreement;
import com.moglix.models.AgreementItem;
import com.moglix.models.Conditions;
import com.moglix.utils.DateUtils;
import com.moglix.utils.NumberValidationUtils;

public class AgreementMapper {
	
	static ObjectMapper mapper = new ObjectMapper();
	
	private static final Logger logger = LoggerFactory.getLogger(AgreementMapper.class);
	
	public static JsonNode createRequestJsonForVendorPortal(Agreement agreement){
		JsonNode agreementJsonNode = mapper.valueToTree(agreement);
		ObjectNode agreementObject = mapper.createObjectNode();
		logger.debug("Agreement object in mapper class is : "+agreementJsonNode.toString());
		Iterator<Entry<String, JsonNode>> it = agreementJsonNode.fields();
		while(it.hasNext()){
			Entry<String, JsonNode> entry = it.next();
			if(entry.getKey().equals("items")){
				ArrayNode itemsArrayNode = mapper.createArrayNode();
				Iterator<JsonNode> iteratorForItems =entry.getValue().iterator();
				while(iteratorForItems.hasNext()){
					itemsArrayNode.add(createAgreementItemsForVendorPortal(iteratorForItems.next()));
				}
				ObjectNode plantObject = mapper.createObjectNode();
				if(itemsArrayNode.size()>0){
					JsonNode plant = itemsArrayNode.get(0).get("plant");
					agreementObject.putPOJO("plant", plant);
				} else {
					plantObject.put("code", "");
					plantObject.put("name", "");
					agreementObject.putPOJO("plant", plantObject);
				}
				agreementObject.putPOJO(entry.getKey(), itemsArrayNode);
			} else if(entry.getKey().equals("startDate") || entry.getKey().equals("endDate")) {
				if(!entry.getValue().isNull()) agreementObject.put(entry.getKey(), DateUtils.convertDateToTimestamp(entry.getValue().asText()));
			} else {
				if(!entry.getValue().isNull()) agreementObject.put(entry.getKey(), entry.getValue().asText());
			}
		}
		ObjectNode vendorObject = mapper.createObjectNode(), companyObject = mapper.createObjectNode(), shippingPlantObject = mapper.createObjectNode(), pmtTermObject = mapper.createObjectNode();
		if(agreementObject.has("vendorCode")) vendorObject.put("code", agreementObject.get("vendorCode").asText());
		else vendorObject.put("code", "");
		if(agreementObject.has("vendorName")) vendorObject.put("name", agreementObject.get("vendorName").asText());
		else vendorObject.put("name", "");
		if(agreementObject.has("companyCode")) companyObject.put("code", agreementObject.get("companyCode").asText());
		else companyObject.put("code", "");
		if(agreementObject.has("companyName")) companyObject.put("name", agreementObject.get("companyName").asText());
		else companyObject.put("name", "");
		if(agreementObject.has("shippingPlantCode")) shippingPlantObject.put("code", agreementObject.get("shippingPlantCode").asText());
		else shippingPlantObject.put("shippingPlantCode", "");
		if(agreementObject.has("shippingPlantName")) shippingPlantObject.put("name", agreementObject.get("shippingPlantName").asText());
		else shippingPlantObject.put("shippingPlantName", "");
		if(agreementObject.has("pmtTerms")) pmtTermObject.put("term", agreementObject.get("pmtTerms").asText());
		else pmtTermObject.put("term", "");
		if(agreementObject.has("pmtTermDesc")) pmtTermObject.put("desc", agreementObject.get("pmtTermDesc").asText());
		else pmtTermObject.put("desc", "");
		agreementObject.remove("vendorCode");
		agreementObject.remove("vendorName");
		agreementObject.remove("companyCode");
		agreementObject.remove("companyName");
		agreementObject.remove("shippingPlantCode");
		agreementObject.remove("shippingPlantName");
		agreementObject.remove("pmtTerms");
		agreementObject.remove("pmtTermDesc");
		agreementObject.putPOJO("vendor", vendorObject);
		agreementObject.putPOJO("company", companyObject);
		agreementObject.putPOJO("shippingPlant", shippingPlantObject);
		agreementObject.putPOJO("pmtTerm", pmtTermObject);
		return agreementObject;
	}
	
	private static JsonNode createAgreementItemsForVendorPortal(JsonNode agreementItem){
		ObjectNode agreementItemObject = mapper.createObjectNode();
		Iterator<Entry<String, JsonNode>> it = agreementItem.fields();
		while(it.hasNext()){
			Entry<String, JsonNode> entry = it.next();
			if(entry.getKey().equals("quantity") || entry.getKey().equals("price") || entry.getKey().equals("netValue") || entry.getKey().equals("standardPackSize") || entry.getKey().equals("tolerance")){
				if(!entry.getValue().isNull()) agreementItemObject.put(entry.getKey(), entry.getValue().asDouble());
			} else if(entry.getKey().equals("conditions")) {
				ArrayNode conditionsArrayNode = mapper.createArrayNode();
				Iterator<JsonNode> itForConditions = entry.getValue().iterator();
				while(itForConditions.hasNext()){
					conditionsArrayNode.add(createConditionsObjectForVendorPortal(itForConditions.next()));
				}
				agreementItemObject.putPOJO(entry.getKey(), conditionsArrayNode);
			} else if(entry.getKey().equals("deliveryDate")) {
				if(!entry.getValue().isNull()) agreementItemObject.put(entry.getKey(), DateUtils.convertDateToTimestamp(entry.getValue().asText()));
			} else {
				if(!entry.getValue().isNull()) agreementItemObject.put(entry.getKey(), entry.getValue().asText());
			}
		}
		ObjectNode plantObject = mapper.createObjectNode(), priceObject = mapper.createObjectNode();
		if(agreementItemObject.has("plantCode")) plantObject.put("code", agreementItemObject.get("plantCode").asText());
		else plantObject.put("code", "");
		if(agreementItemObject.has("plantName")) plantObject.put("name", agreementItemObject.get("plantName").asText());
		else plantObject.put("name", "");
		if(agreementItemObject.has("price")) priceObject.put("price", Double.parseDouble(agreementItemObject.get("price").asText()));
		else priceObject.put("price", "");
		if(agreementItemObject.has("currency")) priceObject.put("currency", agreementItemObject.get("currency").asText());
		else priceObject.put("currency", "");
		agreementItemObject.remove("plantCode");
		agreementItemObject.remove("plantName");
		agreementItemObject.remove("price");
		agreementItemObject.remove("currency");
		agreementItemObject.putPOJO("plant", plantObject);
		agreementItemObject.putPOJO("price", priceObject);
		return agreementItemObject;
	}
	
	private static JsonNode createConditionsObjectForVendorPortal(JsonNode conditions){
		ObjectNode conditionObject = mapper.createObjectNode();
		Iterator<Entry<String, JsonNode>> it = conditions.fields();
		while(it.hasNext()){
			Entry<String, JsonNode> entry = it.next();
			if(entry.getKey().equals("rate") || entry.getKey().equals("value")){
				if(!entry.getValue().isNull()) conditionObject.put(entry.getKey(), entry.getValue().asDouble());
			}  else {
				if(!entry.getValue().isNull()) conditionObject.put(entry.getKey(), entry.getValue().asText());
			}
		}
		if(conditionObject.has("type")) conditionObject.put("conditionType", conditionObject.get("type").asText());
		else conditionObject.put("conditionType", "");
		conditionObject.remove("type");
		return conditionObject;
	}
	
	public static Agreement createAgreementObjFromJsonNode(String request){
		Agreement agreement = new Agreement();
		try {
			List<AgreementItem> items=null;
			JsonNode root = mapper.readTree(request);
			logger.info("createAgreementObjFromJsonNOde root is "+ root.toString());
			if(root.has("agreementNo") && !root.path("agreementNo").asText().trim().isEmpty()) agreement.setAgreementNo(root.path("agreementNo").asText().trim());
			if(root.has("agreementType") && !root.path("agreementType").asText().trim().isEmpty())  agreement.setAgreementType(root.path("agreementType").asText().trim());
			if(root.has("docType") && !root.path("docType").asText().trim().isEmpty())  agreement.setDocType(root.path("docType").asText().trim());
			if(root.has("purchaseGroup") && !root.path("purchaseGroup").asText().trim().isEmpty())  agreement.setPurchaseGroup(root.path("purchaseGroup").asText().trim());

			if(root.has("pmtTerms") && !root.path("pmtTerms").asText().trim().isEmpty())  agreement.setPmtTerms(root.path("pmtTerms").asText().trim());
			if(root.has("paymentTermDesc") && !root.path("paymentTermDesc").asText().trim().isEmpty())  agreement.setPmtTermDesc(root.path("paymentTermDesc").asText().trim());
			if(root.has("startDate") && !root.path("startDate").asText().trim().isEmpty() && NumberValidationUtils.isDouble(root.path("startDate").asText().trim()))
				agreement.setStartDate(root.path("startDate").asText().trim());
			if(root.has("endDate") && !root.path("endDate").asText().trim().isEmpty() && NumberValidationUtils.isDouble(root.path("endDate").asText().trim())) 
				agreement.setEndDate(root.path("endDate").asText().trim());
			if(root.has("agreementStatus") && !root.path("agreementStatus").asText().trim().isEmpty())  agreement.setAgreementStatus(root.path("agreementStatus").asText().trim());
			if(root.has("vendorCode") && !root.path("vendorCode").asText().trim().isEmpty())  agreement.setVendorCode(root.path("vendorCode").asText().trim());
			if(root.has("vendorName") && !root.path("vendorName").asText().trim().isEmpty())  agreement.setVendorName(root.path("vendorName").asText().trim());
			if(root.has("companyCode") && !root.path("companyCode").asText().trim().isEmpty()) agreement.setCompanyCode(root.path("companyCode").asText().trim());
			if(root.has("companyName") && !root.path("companyName").asText().trim().isEmpty()) agreement.setCompanyName(root.path("companyName").asText().trim());
			if(root.has("shippingPlantCode") && !root.path("shippingPlantCode").asText().trim().isEmpty()) agreement.setShippingPlantCode(root.path("shippingPlantCode").asText().trim());
			if(root.has("shippingPlantName") && !root.path("shippingPlantName").asText().trim().isEmpty()) agreement.setShippingPlantName(root.path("shippingPlantName").asText().trim());
			if(root.has("currency") && !root.path("currency").asText().trim().isEmpty()) agreement.setCurrency(root.path("currency").asText().trim());
			if(root.has("releaseStatus") && !root.path("releaseStatus").asText().trim().isEmpty()) agreement.setReleaseStatus(root.path("releaseStatus").asText().trim());
			if(root.has("items")){
				items=new ArrayList<AgreementItem>();
				if(root.path("items").isArray()){
					logger.info("isArray");
					AgreementItem agreementItem;
					for(JsonNode itemObject: root.get("items")){
						agreementItem=createAgreementItemObject(itemObject);
						if(agreement.getDocType().equals("ZC-ASSETS") && agreementItem.getMaterialCode()==null) agreementItem.setMaterialCode("ASSET PO");
						items.add(agreementItem);
					}
				} else {
					logger.info("not array");
					JsonNode itemObject = root.get("items");
					AgreementItem agreementItem=createAgreementItemObject(itemObject);
					if(agreement.getDocType().equals("ZC-ASSETS") && agreementItem.getMaterialCode()==null) agreementItem.setMaterialCode("ASSET PO");
					items.add(agreementItem);

				}
			}
			agreement.setItems(items);
		} catch (Exception e) {
			logger.debug("exception occured at while mapping json "+e.getMessage());
		}
		return agreement;
	}
	
	private static AgreementItem createAgreementItemObject(JsonNode itemObject){
		logger.info("item object for agreeemtnItemObject "+ itemObject.toString());
		AgreementItem agreementItem=new AgreementItem();
		List<Conditions> conditionsList = null;
		if(itemObject.has("deliveryCompletionCheck") && !itemObject.path("deliveryCompletionCheck").asText().trim().isEmpty()) agreementItem.setDeliveryCompletionCheck(itemObject.path("deliveryCompletionCheck").asText().toLowerCase().trim());
		if(itemObject.has("itemNo") && !itemObject.path("itemNo").asText().trim().isEmpty()) agreementItem.setItemNo(itemObject.path("itemNo").asText().trim());
		if(itemObject.has("materialCode") && !itemObject.path("materialCode").asText().trim().isEmpty()) agreementItem.setMaterialCode(itemObject.path("materialCode").asText().trim());
		if(itemObject.has("materialName") && !itemObject.path("materialName").asText().trim().isEmpty()) agreementItem.setMaterialName(itemObject.path("materialName").asText().trim());
		if(itemObject.has("category") && !itemObject.path("category").asText().trim().isEmpty()) agreementItem.setCategory(itemObject.path("category").asText().trim());
		if(itemObject.has("quantity") && !itemObject.path("quantity").asText().trim().equals("") && NumberValidationUtils.isDouble(itemObject.path("quantity").asText().trim())) 
			agreementItem.setQuantity(Double.parseDouble(itemObject.path("quantity").asText().trim()));
		if(itemObject.has("price") && !itemObject.path("price").asText().trim().equals("") && NumberValidationUtils.isDouble(itemObject.path("price").asText().trim())) 
			agreementItem.setPrice(Double.parseDouble(itemObject.path("price").asText().trim()));
		else agreementItem.setPrice(0.0);
		if(itemObject.has("tolerance") && !itemObject.path("tolerance").asText().trim().equals("") && NumberValidationUtils.isDouble(itemObject.path("tolerance").asText().trim()))
			agreementItem.setTolerance(Double.parseDouble(itemObject.path("tolerance").asText().trim()));
		else agreementItem.setTolerance(0.0);
		if(itemObject.has("netValue") && !itemObject.path("netValue").asText().trim().equals("") && NumberValidationUtils.isDouble(itemObject.path("netValue").asText().trim())) 
			agreementItem.setNetValue(Double.parseDouble(itemObject.path("netValue").asText().trim()));
		else agreementItem.setNetValue(0.0);
		if(itemObject.has("uom") && !itemObject.path("uom").asText().trim().isEmpty()) agreementItem.setUom(itemObject.path("uom").asText().trim());
		if(itemObject.has("storageLocation") && !itemObject.path("storageLocation").asText().trim().isEmpty()) agreementItem.setStorageLocation(itemObject.path("storageLocation").asText().trim());
		if(itemObject.has("standardPackSize") && !itemObject.path("standardPackSize").asText().trim().equals("") && NumberValidationUtils.isDouble(itemObject.path("standardPackSize").asText().trim())) 
			agreementItem.setStandardPackSize(Double.parseDouble(itemObject.path("standardPackSize").asText().trim()));
		if(itemObject.has("plantCode") && !itemObject.path("plantCode").asText().trim().isEmpty()) agreementItem.setPlantCode(itemObject.path("plantCode").asText().trim());
		if(itemObject.has("plantName") && !itemObject.path("plantName").asText().trim().isEmpty()) agreementItem.setPlantName(itemObject.path("plantName").asText().trim());
		if(itemObject.has("deliveryDate") && !itemObject.path("deliveryDate").asText().trim().isEmpty() && NumberValidationUtils.isDouble(itemObject.path("deliveryDate").asText().trim()))
			agreementItem.setDeliveryDate(itemObject.path("deliveryDate").asText().trim());
		if(itemObject.has("hsnCode") && !itemObject.path("hsnCode").asText().trim().isEmpty()) agreementItem.setHsnCode(itemObject.path("hsnCode").asText().trim());
		if(itemObject.has("netWeight") && !itemObject.path("netWeight").asText().trim().isEmpty()) agreementItem.setNetWeight(itemObject.path("netWeight").asText().trim());
		if(itemObject.has("conditions")){
			conditionsList = new ArrayList<Conditions>();
			if(itemObject.get("conditions").isArray()){
				for(JsonNode conditionObj: itemObject.get("conditions")){
					Conditions conditionObject = createConditionsObject(conditionObj);
					conditionsList.add(conditionObject);
				}
			} else {
				JsonNode conditionObj = itemObject.get("conditions");
				Conditions conditionObject = createConditionsObject(conditionObj);
				conditionsList.add(conditionObject);
			}
		}
		agreementItem.setConditions(conditionsList);
		return agreementItem;
	}
	
	private static Conditions createConditionsObject(JsonNode conditionObj){
		Conditions conditionObject = new Conditions();
		if(conditionObj.has("type") && !conditionObj.path("type").asText().trim().isEmpty()) conditionObject.setType(conditionObj.path("type").asText().trim());
		if(conditionObj.has("description") && !conditionObj.path("description").asText().trim().isEmpty()) conditionObject.setDescription(conditionObj.path("description").asText().trim());
		if(conditionObj.has("rate") && !conditionObj.path("rate").asText().trim().equals("") && NumberValidationUtils.isDouble(conditionObj.path("rate").asText().trim()) ) 
			conditionObject.setRate(Double.parseDouble(conditionObj.path("rate").asText().trim()));
		else if(conditionObj.has("rate") && !conditionObj.path("rate").asText().trim().equals("") && conditionObj.path("rate").asText().trim().indexOf("-")>-1 ){
			String rate = conditionObj.path("rate").asText().trim();
			conditionObject.setRate(Double.parseDouble(rate.substring(0,rate.length()-1)));
		}
		if(conditionObj.has("value") && !conditionObj.path("value").asText().trim().equals("") && NumberValidationUtils.isDouble(conditionObj.path("value").asText().trim()))
			conditionObject.setValue(Double.parseDouble(conditionObj.path("value").asText().trim()));
		if(conditionObj.has("currency") && !conditionObj.path("currency").asText().trim().isEmpty()) conditionObject.setCurrency(conditionObj.path("currency").asText().trim());
		return conditionObject;
	}
	
}
