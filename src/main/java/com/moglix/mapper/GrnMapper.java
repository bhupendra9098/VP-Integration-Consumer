package com.moglix.mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moglix.models.Grn;
import com.moglix.models.GrnItem;
import com.moglix.utils.DateUtils;
import com.moglix.utils.NumberValidationUtils;

public class GrnMapper {

	private static ObjectMapper mapper = new ObjectMapper();
	
	private static final Logger logger = LoggerFactory.getLogger(GrnMapper.class);
	
	@JsonIgnoreProperties(ignoreUnknown = true)
	public static Grn getGrnObject(String request){
		
		JsonNode root;
		try {
			root = mapper.readTree(request);
			Grn grn = new Grn();
			if(root.has("docNo") && !root.get("docNo").asText().trim().isEmpty()) grn.setGrnNo(root.get("docNo").asText().trim());
			if(root.has("grnYear") && !root.get("grnYear").asText().trim().isEmpty()) grn.setGrnYear(root.get("grnYear").asText().trim());
			if(root.has("geNo") && !root.get("geNo").asText().trim().isEmpty()) grn.setGeNo(root.get("geNo").asText().trim());
			if(root.has("purchasingGroup") && !root.get("purchasingGroup").asText().trim().isEmpty()) grn.setPurchasingGroup(root.get("purchasingGroup").asText().trim());
			if(root.has("companyCode") && !root.get("companyCode").asText().trim().isEmpty()) grn.setCompanyCode(root.get("companyCode").asText().trim());
			if(root.has("documentDate") && !root.get("documentDate").asText().trim().isEmpty() && NumberValidationUtils.isDouble(root.get("documentDate").asText().trim()) && DateUtils.convertDateToTimestamp(root.get("documentDate").asText().trim())!=-1) 
				grn.setDocumentDate(DateUtils.convertDateToTimestamp(root.get("documentDate").asText().trim()));
			if(root.has("postingDate") && !root.get("postingDate").asText().trim().isEmpty() && NumberValidationUtils.isDouble(root.get("postingDate").asText().trim()) && DateUtils.convertDateToTimestamp(root.get("postingDate").asText().trim())!=-1) 
				grn.setPostingDate(DateUtils.convertDateToTimestamp(root.get("postingDate").asText().trim()));
			if(root.has("vendorCode") && !root.get("vendorCode").asText().trim().isEmpty()) grn.setVendorCode(root.get("vendorCode").asText().trim());
			if(root.has("invoiceNo") && !root.get("invoiceNo").asText().trim().isEmpty()) grn.setInvoiceNo(root.get("invoiceNo").asText().trim());
			if(root.has("invoiceDate") && !root.get("invoiceDate").asText().trim().isEmpty() && NumberValidationUtils.isDouble(root.get("invoiceDate").asText().trim()) && DateUtils.convertDateToTimestamp(root.get("invoiceDate").asText().trim())!=-1) 
				grn.setInvoiceDate(DateUtils.convertDateToTimestamp(root.get("invoiceDate").asText().trim()));
			if(root.has("invoiceValue") && !root.get("invoiceValue").asText().trim().isEmpty() && NumberValidationUtils.isDouble(root.get("invoiceValue").asText().trim())) 
				grn.setInvoiceValue(Double.parseDouble(root.get("invoiceValue").asText().trim()));
			if(root.has("deliveryNote") && !root.get("deliveryNote").asText().trim().isEmpty()) grn.setDeliveryNote(root.get("deliveryNote").asText().trim());
			if(root.has("billOfLading") && !root.get("billOfLading").asText().trim().isEmpty()) grn.setBillOfLading(root.get("billOfLading").asText().trim());
			if(root.has("text") && !root.get("text").asText().trim().isEmpty()) grn.setText(root.get("text").asText().trim());
			if(root.has("deletionIndicator") && !root.get("deletionIndicator").asText().trim().isEmpty()) grn.setDeletionIndicator(root.get("deletionIndicator").asText().trim());
			if(root.has("docNo") && !root.get("docNo").asText().trim().isEmpty()) grn.setDocNo(root.get("docNo").asText().trim());
			if(root.has("oldDocNo") && !root.get("oldDocNo").asText().trim().isEmpty()) grn.setOldDocNo(root.get("oldDocNo").asText().trim());
			if(root.has("oldPostingDate") && !root.get("oldPostingDate").asText().trim().isEmpty() && NumberValidationUtils.isDouble(root.get("oldPostingDate").asText().trim()) && DateUtils.convertDateToTimestamp(root.get("oldPostingDate").asText().trim())!=-1) 
				grn.setOldPostingDate(DateUtils.convertDateToTimestamp(root.get("oldPostingDate").asText().trim()));
			if(root.has("movementType") && !root.get("movementType").asText().trim().isEmpty()) grn.setMovementType(root.get("movementType").asText().trim());

			if(root.has("documentTime") && !root.get("documentTime").asText().trim().isEmpty()
					&& NumberValidationUtils.isDouble(root.get("documentTime").asText().trim()))
				grn.setDocumentDate(DateUtils.convertDateToTimestamp(root.get("documentDate").asText().trim()));

			if(root.has("documentTime") && root.has("postingDate")){
				String postingDate = root.get("postingDate").asText().trim();
				String documentTime = root.get("documentTime").asText().trim();
				if(!documentTime.isEmpty() && NumberValidationUtils.isDouble(documentTime)
						&& !postingDate.isEmpty() && NumberValidationUtils.isDouble(postingDate)){
					grn.setPostingDate(DateUtils.mergeDateAndTimeAndConvertToTimestamp(postingDate,documentTime));
				}
			}


			List<GrnItem> grnItemList = new ArrayList<GrnItem>();;
			if(root.has("items")){
				if(root.get("items").isArray()){
					Iterator<JsonNode> iteratorForGrnItems =root.get("items").iterator();
					while(iteratorForGrnItems.hasNext()){
						grnItemList.add(getGrnItemObject(iteratorForGrnItems.next()));
					}
				} else {
					grnItemList.add(getGrnItemObject(root.get("items")));
				}
			}
			grn.setItems(grnItemList);
			return grn;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			logger.debug("Exception occured while parsing string to Json, "+e1.getMessage());
			return null;
		}
	}
	
	public static GrnItem getGrnItemObject(JsonNode grnItemJson){
		logger.debug("in grn items method ....... "+grnItemJson);
		GrnItem grnItem = new GrnItem();
		if(grnItemJson.has("itemNo") && !grnItemJson.get("itemNo").asText().trim().isEmpty()) grnItem.setItemNo(grnItemJson.get("itemNo").asText().trim());
		if(grnItemJson.has("materialCode") && !grnItemJson.get("materialCode").asText().trim().isEmpty()) grnItem.setMaterialCode(grnItemJson.get("materialCode").asText().trim());
		if(grnItemJson.has("materialName") && !grnItemJson.get("materialName").asText().trim().isEmpty()) grnItem.setMaterialName(grnItemJson.get("materialName").asText().trim());
		if(grnItemJson.has("plantCode") && !grnItemJson.get("plantCode").asText().trim().isEmpty()) grnItem.setPlantCode(grnItemJson.get("plantCode").asText().trim());
		if(grnItemJson.has("plantName") && !grnItemJson.get("plantName").asText().trim().isEmpty()) grnItem.setPlantName(grnItemJson.get("plantName").asText().trim());
		if(grnItemJson.has("quantity") && !grnItemJson.get("quantity").asText().trim().isEmpty() && NumberValidationUtils.isDouble(grnItemJson.get("quantity").asText().trim())) grnItem.setQuantity(Double.parseDouble(grnItemJson.get("quantity").asText().trim()));
		if(grnItemJson.has("uom") && !grnItemJson.get("uom").asText().trim().isEmpty()) grnItem.setUom(grnItemJson.get("uom").asText().trim());
		if(grnItemJson.has("poNo") && !grnItemJson.get("poNo").asText().trim().isEmpty()) grnItem.setPoNo(grnItemJson.get("poNo").asText().trim());
		if(grnItemJson.has("poItemNo") && !grnItemJson.get("poItemNo").asText().trim().isEmpty()) grnItem.setPoItemNo(grnItemJson.get("poItemNo").asText().trim());
		if(grnItemJson.has("storageLocation") && !grnItemJson.get("storageLocation").asText().trim().isEmpty()) grnItem.setStorageLocation(grnItemJson.get("storageLocation").asText().trim());
		return grnItem;
	}
	
}
