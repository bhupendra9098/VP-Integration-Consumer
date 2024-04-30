package com.moglix.services;

import java.io.IOException;

import com.moglix.exception.MoglixUserException;
import com.moglix.request.VendorRatingRequest;
import com.moglix.response.ServiceResponse;

public interface IVendorRatingService {

    ServiceResponse upsert(VendorRatingRequest request) throws MoglixUserException, IOException;

}
