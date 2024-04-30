package com.moglix.services;

import java.io.IOException;

import com.moglix.exception.MoglixUserException;
import com.moglix.response.ServiceResponse;

public interface IHttpUtilService<T> {
	
	ServiceResponse httpPostCall(T request, String url) throws MoglixUserException, IOException;
	
	ServiceResponse httpDeleteCall(String url) throws MoglixUserException, IOException;
	
	ServiceResponse httpPutCall(T request, String url) throws MoglixUserException, IOException;
	
}
