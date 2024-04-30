package com.moglix.services.impl;

import com.google.gson.Gson;
import com.moglix.application.ExchangeManager;
import com.moglix.constants.ApplicationConstants;
import com.moglix.exception.MoglixUserException;
import com.moglix.request.VendorRatingRequest;
import com.moglix.response.ServiceResponse;
import com.moglix.services.IMessageBrokerService;
import com.moglix.services.IVendorRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
public class VendorRatingServiceImpl implements IVendorRatingService {

    @Autowired
    IMessageBrokerService messageBroker;

    @Override
    @Transactional
    public ServiceResponse upsert(VendorRatingRequest request) throws MoglixUserException, IOException {
        return messageBroker.createMessage(new Gson().toJson(request), ExchangeManager.EXCHANGE_VENDOR_RATING, ApplicationConstants.TOPIC_VENDOR_RATING_CREATE);
    }

}
