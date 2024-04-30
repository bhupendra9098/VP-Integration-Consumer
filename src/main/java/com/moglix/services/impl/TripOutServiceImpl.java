package com.moglix.services.impl;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.moglix.application.ExchangeManager;
import com.moglix.constants.ApplicationConstants;
import com.moglix.exception.MoglixUserException;
import com.moglix.mapper.TripOutMapper;
import com.moglix.models.TripOut;
import com.moglix.response.ServiceResponse;
import com.moglix.services.IMessageBrokerService;
import com.moglix.services.ITripOutService;

@Service
public class TripOutServiceImpl implements ITripOutService {

	private static final Logger logger = LoggerFactory.getLogger(TripOutServiceImpl.class);
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	IMessageBrokerService messageBroker;
	
	@Override
	@Transactional
	public ServiceResponse upsert(TripOut request) throws MoglixUserException, IOException {
		logger.info("Request body to create Trip is : "+request);
		return messageBroker.createMessage(new Gson().toJson(request), ExchangeManager.EXCHANGE_TRIPOUT, ApplicationConstants.TOPIC_TRIPOUT_CREATE);
	}
	
	@Override
	@Transactional
	public ServiceResponse delete(String tripNumber) throws MoglixUserException, IOException {
		logger.info("Trip number to delete is : "+tripNumber);
		return messageBroker.createMessage(tripNumber, ExchangeManager.EXCHANGE_TRIPOUT, ApplicationConstants.TOPIC_TRIPOUT_DELETE);
	}

	@Override
	public ServiceResponse upsertStringRequest(String request) throws MoglixUserException, IOException {
		JsonNode root = mapper.readTree(request);
		if(root.has("task") && root.get("task").asText().trim().equals("delete")) 
			return this.delete(root.get("tripNumber").asText().trim());
		else
			return this.upsert(TripOutMapper.getTripOutObject(request));
	}
	
}
