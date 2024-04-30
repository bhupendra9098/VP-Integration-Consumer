package com.moglix.services.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.moglix.application.ExchangeManager;
import com.moglix.constants.ApplicationConstants;
import com.moglix.exception.MoglixUserException;
import com.moglix.mapper.AgreementMapper;
import com.moglix.models.Agreement;
import com.moglix.response.ServiceResponse;
import com.moglix.services.IAgreementService;
import com.moglix.services.IMessageBrokerService;

@Service
public class AgreementServiceImpl implements IAgreementService {

	private static final Logger logger = LoggerFactory.getLogger(AgreementServiceImpl.class);
	
	@Autowired
	IMessageBrokerService messageBroker;
	
	@Value("${jvp.agreement.create}")
	private String JVP_AGREEMENT_CREATE;
	
	@Override
	@Transactional
	public ServiceResponse upsert(JsonNode request) throws MoglixUserException, IOException {
		logger.info("Request body to create Agreement is : "+request);
		return messageBroker.createMessage(request.toString(), ExchangeManager.EXCHANGE_AGREEMENT, ApplicationConstants.TOPIC_AGREEMENT_CREATE);
	}

	@Override
	@Transactional
	public ServiceResponse upsertStringRequest(String request) throws MoglixUserException, IOException {
		Agreement agreement = AgreementMapper.createAgreementObjFromJsonNode(request);
		JsonNode agreementJson;
		try {
			agreementJson = AgreementMapper.createRequestJsonForVendorPortal(agreement);
			return this.upsert(agreementJson);
		} catch(Exception e) {
			ServiceResponse response = new ServiceResponse();
			response.setStatusCode(0);
			response.setErrorMsg(e.getMessage());
			return response;
		}
	}
	
}
