package com.moglix.services.impl;

import com.google.gson.Gson;
import com.moglix.application.ExchangeManager;
import com.moglix.constants.ApplicationConstants;
import com.moglix.mapper.GrnMapper;
import com.moglix.models.Agreement;
import com.moglix.models.Grn;
import com.moglix.response.ServiceResponse;
import com.moglix.services.IGrnService;
import com.moglix.services.IHttpUtilService;
import com.moglix.services.IMessageBrokerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by nitesh on 10/11/17.
 */
@Service
public class GrnServiceImpl implements IGrnService{

    private static final Logger logger = LoggerFactory.getLogger(GrnServiceImpl.class);

    @Autowired
    IHttpUtilService<Agreement> httpUtilService;

    @Autowired
    IMessageBrokerService messageBroker;

    @Override
    public ServiceResponse upsert(Grn request) {
        logger.info("Request body is : "+new Gson().toJson(request));
        return messageBroker.createMessage(new Gson().toJson(request), ExchangeManager.EXCHANGE_GRN, ApplicationConstants.TOPIC_GRN_CREATE);
    }

	@Override
	public ServiceResponse convertAndUpsert(String request) {
		Grn grn = GrnMapper.getGrnObject(request);
        logger.debug("The GRN object is "+new Gson().toJson(grn));
		return this.upsert(grn);
	}
    
}
