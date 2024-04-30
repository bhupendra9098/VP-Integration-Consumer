package com.moglix.services;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.moglix.exception.MoglixUserException;
import com.moglix.response.ServiceResponse;

public interface IHttpUtilJsonService {
	
	ServiceResponse httpPostCall(JsonNode request, String url) throws MoglixUserException, IOException;
	
	ServiceResponse httpPutCall(JsonNode request, String url) throws MoglixUserException, IOException;
	
}
