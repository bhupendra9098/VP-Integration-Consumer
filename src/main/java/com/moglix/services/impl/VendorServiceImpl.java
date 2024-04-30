package com.moglix.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moglix.request.VendorBankDetails;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.moglix.application.ExchangeManager;
import com.moglix.constants.ApplicationConstants;
import com.moglix.exception.MoglixUserException;
import com.moglix.request.VendorDataRequest;
import com.moglix.request.VendorRequest;
import com.moglix.response.ServiceResponse;
import com.moglix.services.IVendorService;
import com.moglix.services.IHttpUtilService;
import com.moglix.services.IMessageBrokerService;
import com.moglix.utils.ValidationUtil;

@Service
public class VendorServiceImpl implements IVendorService {

	private static final Logger logger = LoggerFactory.getLogger(HttpUtilServiceImpl.class);
	
	HttpClient httpClient = HttpClients.createDefault();
	
	@SuppressWarnings("unused")
	@Autowired
	private ValidationUtil<Object> validationUtil;
	
	@Autowired
	IMessageBrokerService messageBroker;
	
	@Autowired
	IHttpUtilService<VendorDataRequest> httpUtilService;
	
	@Value("${um.vendor.upsert}")
	private String UM_VENDOR_CREATE;
	
	@Override
	@Transactional
	public ServiceResponse upsert(VendorRequest request) throws IOException {
//		request.getBankDetails().setVendorCode(request.getVendorCode());
		if(request.getGstn().length() != 15 && request.getPanNo().length() != 10){
			return new ServiceResponse();
		}
		VendorRequest newRequest = new VendorRequest();
		newRequest = request;

		Object bankDetailsObj = request.getBankDetails();
		if (bankDetailsObj instanceof List <?>) { // type parameter is an unbounded wildcard
			// Do something
			logger.info("it have array");
			logger.info("it have array"+bankDetailsObj);
			List<LinkedHashMap<String,String>> bankDetailsMap = (List<LinkedHashMap<String,String>> ) bankDetailsObj;

			List<VendorBankDetails> bankdetailsArray = new ArrayList<>();
			ObjectMapper mapper = new ObjectMapper();
			for(LinkedHashMap bankDetails:bankDetailsMap){
				bankDetails.put("vendorCode", request.getVendorCode());
				VendorBankDetails myObjects = mapper.readValue(new Gson().toJson(bankDetails), new TypeReference<VendorBankDetails>(){});
				bankdetailsArray.add(myObjects);
			}
			newRequest.setBankDetails(bankdetailsArray);
		} else {
			logger.info("it doesn't have array");
			List<VendorBankDetails> bankdetailsArray = new ArrayList<>();
			logger.info(new Gson().toJson(bankDetailsObj));
			Map<String, String> bankDetailsMap = (LinkedHashMap<String, String>)bankDetailsObj;
			bankDetailsMap.put("vendorCode", request.getVendorCode());
			ObjectMapper mapper = new ObjectMapper();
			VendorBankDetails myObjects = mapper.readValue(new Gson().toJson(bankDetailsMap), new TypeReference<VendorBankDetails>(){});
			bankdetailsArray.add(myObjects);
			newRequest.setBankDetails(bankdetailsArray);
		}

		Object plantObj = request.getMappedPlantDetails();
		if (plantObj instanceof List <?>) { // type parameter is an unbounded wildcard
			// Do something
			logger.info("it have array");
			logger.info("it have array"+plantObj);
			List<LinkedHashMap<String,Integer>> plantMap = (List<LinkedHashMap<String,Integer>> ) plantObj;
			logger.info("it have array"+plantMap.size());

			List<String> plantArray = new ArrayList<>();
			for(LinkedHashMap plant:plantMap){
				logger.info("it have array"+ plant.get("plantCode"));
				plantArray.add(plant.get("plantCode").toString());
			}
			newRequest.setMappedPlantDetails(plantArray);
		} else {
			logger.info("it doesn't have array");
			List<String> plantArray = new ArrayList<>();
			LinkedHashMap<String,String> plantMap = (LinkedHashMap<String,String> ) plantObj;
			if(plantMap != null)
				plantArray.add(plantMap.get("plantCode"));
			newRequest.setMappedPlantDetails(plantArray);
		}
		logger.info("Create vendor request is "+new Gson().toJson(newRequest));
		return messageBroker.createMessage(new Gson().toJson(newRequest), ExchangeManager.EXCHANGE_VENDOR, ApplicationConstants.TOPIC_VENDOR_CREATE);
	}
	
	@Override
	public ServiceResponse httpcall(VendorDataRequest request, String url) throws MoglixUserException, IOException {
		return httpUtilService.httpPutCall(request, UM_VENDOR_CREATE);
	}
	
}
