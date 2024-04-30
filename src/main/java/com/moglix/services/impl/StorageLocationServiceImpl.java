package com.moglix.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.moglix.application.ExchangeManager;
import com.moglix.constants.ApplicationConstants;
import com.moglix.exception.MoglixUserException;
import com.moglix.mapper.StorageLocationMapper;
import com.moglix.models.StorageLocation;
import com.moglix.response.ServiceResponse;
import com.moglix.services.IMessageBrokerService;
import com.moglix.services.IStorageLocationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class StorageLocationServiceImpl implements IStorageLocationService {

    private static final Logger logger = LoggerFactory.getLogger(StorageLocationServiceImpl.class);

    private static ObjectMapper mapper = new ObjectMapper();

    @Autowired
    IMessageBrokerService messageBroker;

    @Override
    @Transactional
    public ServiceResponse upsert(StorageLocation request) throws MoglixUserException, IOException {
        logger.info("Request body to create Storage Location is : "+request);
        return messageBroker.createMessage(new Gson().toJson(request), ExchangeManager.EXCHANGE_STORAGE_LOCATION, ApplicationConstants.TOPIC_STORAGE_LOCATION_CREATE);
    }


    @Override
    public ServiceResponse upsertStringRequest(String request) throws MoglixUserException, IOException {
        return this.upsert(StorageLocationMapper.getSLobject(request));
    }

}
