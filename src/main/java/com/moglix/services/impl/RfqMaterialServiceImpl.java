package com.moglix.services.impl;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.moglix.application.ExchangeManager;
import com.moglix.constants.ApplicationConstants;
import com.moglix.exception.MoglixUserException;
import com.moglix.mapper.RfqMaterialMapper;
import com.moglix.models.RfqMaterials;
import com.moglix.response.ServiceResponse;
import com.moglix.services.IMessageBrokerService;
import com.moglix.services.IRfqMaterialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class RfqMaterialServiceImpl implements IRfqMaterialService {

    private static final Logger logger = LoggerFactory.getLogger(RfqMaterialServiceImpl.class);

    private static ObjectMapper mapper = new ObjectMapper();

    @Autowired
    IMessageBrokerService messageBroker;

    @Override
    @Transactional
    public ServiceResponse upsert(RfqMaterials request) throws MoglixUserException, IOException {
        logger.info("Request body to create Material is : "+request);
        return messageBroker.createMessage(new Gson().toJson(request), ExchangeManager.EXCHANGE_RFQMATERIAL, ApplicationConstants.TOPIC_RFQ_MATERIAL_CREATE);
    }

    @Override
    @Transactional
    public ServiceResponse delete(String materialCode) throws MoglixUserException, IOException {
        logger.info("Material Code to delete is : "+materialCode);
        return messageBroker.createMessage(materialCode, ExchangeManager.EXCHANGE_RFQMATERIAL, ApplicationConstants.TOPIC_RFQ_MATERIAL_DELETE);
    }

    @Override
    @Transactional
    public ServiceResponse upsertStringRequest(String request) throws MoglixUserException, IOException {
        JsonNode root = mapper.readTree(request);
        if(root.has("task") && root.get("task").asText().trim().equals("delete"))
            return this.delete(root.get("materialCode").asText().trim());
        else
            return this.upsert(RfqMaterialMapper.getMaterial(request));
    }

}