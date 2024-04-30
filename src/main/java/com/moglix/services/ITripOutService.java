package com.moglix.services;

import java.io.IOException;

import com.moglix.exception.MoglixUserException;
import com.moglix.models.TripOut;
import com.moglix.response.ServiceResponse;

public interface ITripOutService {
	
	ServiceResponse upsertStringRequest(String request) throws MoglixUserException, IOException;

	ServiceResponse upsert(TripOut request) throws MoglixUserException, IOException;
	
	ServiceResponse delete(String request) throws MoglixUserException, IOException;
	
}
