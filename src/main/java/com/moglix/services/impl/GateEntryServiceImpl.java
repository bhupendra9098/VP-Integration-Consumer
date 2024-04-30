package com.moglix.services.impl;

import org.springframework.stereotype.Service;

import com.moglix.response.ServiceResponse;
import com.moglix.services.IGateEntryService;

@Service
public class GateEntryServiceImpl implements IGateEntryService {

	public ServiceResponse createGateEntry(){
		ServiceResponse response = new ServiceResponse();
		return response;
	}
	
}
