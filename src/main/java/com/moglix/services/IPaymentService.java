package com.moglix.services;

import com.moglix.exception.MoglixUserException;
import com.moglix.models.Payment;
import com.moglix.response.ServiceResponse;

import java.io.IOException;

/**
 * Created by nitesh on 14/11/17.
 */
public interface IPaymentService {
    
    ServiceResponse upsert(Payment request) throws MoglixUserException, IOException;

    ServiceResponse httpCall(Payment request, String url) throws MoglixUserException, IOException;
}
