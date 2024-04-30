package com.moglix.services;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.moglix.exception.MoglixUserException;
import com.moglix.response.ServiceResponse;

public interface IAgreementService {
	
	ServiceResponse upsertStringRequest(String request) throws MoglixUserException, IOException;

	ServiceResponse upsert(JsonNode request) throws MoglixUserException, IOException;
	
}
