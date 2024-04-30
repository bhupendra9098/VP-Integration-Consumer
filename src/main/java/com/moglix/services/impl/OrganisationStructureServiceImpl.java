package com.moglix.services.impl;

import com.google.gson.Gson;
import com.moglix.application.ExchangeManager;
import com.moglix.constants.ApplicationConstants;
import com.moglix.exception.MoglixUserException;
import com.moglix.models.OrganisationStructure;
import com.moglix.models.Payment;
import com.moglix.response.ServiceResponse;
import com.moglix.services.IHttpUtilService;
import com.moglix.services.IMessageBrokerService;
import com.moglix.services.IOrganisationStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * Created by nitesh on 14/11/17.
 */

@Service
public class OrganisationStructureServiceImpl implements IOrganisationStructureService{

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Autowired
    IHttpUtilService<OrganisationStructure> httpUtilService;

    @Autowired
    IMessageBrokerService messageBroker;

    @Value("${jvp.organisation.create}")
    private String JVP_ORGANISATION_STRUCTURE_CREATE;

    @Override
    @Transactional
    public ServiceResponse upsert(OrganisationStructure request) throws MoglixUserException, IOException {
        logger.info("Request body is : "+new Gson().toJson(request));
        return messageBroker.createMessage(new Gson().toJson(request), ExchangeManager.EXCHANGE_ORGANISATION_STRUCTURE, ApplicationConstants.TOPIC_ORGANISATION_STRUCTURE_CREATE);
    }

    /**
     * Calls user JVP-API to push OrganisationStructure data
     */
    @Override
    public ServiceResponse httpCall(OrganisationStructure request, String url) throws MoglixUserException, IOException {
        return httpUtilService.httpPutCall(request, url);
    }
}
