package com.moglix.services.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.moglix.application.ExchangeManager;
import com.moglix.constants.ApplicationConstants;
import com.moglix.exception.MoglixUserException;
import com.moglix.request.EmployeeDataRequest;
import com.moglix.request.EmployeeRequest;
import com.moglix.response.ServiceResponse;
import com.moglix.services.IEmployeeService;
import com.moglix.services.IHttpUtilService;
import com.moglix.services.IMessageBrokerService;
import com.moglix.utils.ValidationUtil;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(HttpUtilServiceImpl.class);
	
	@SuppressWarnings("unused")
	@Autowired
	private ValidationUtil<Object> validationUtil;
	
	@Autowired
	IMessageBrokerService messageBroker;
	
	@Autowired
	IHttpUtilService<EmployeeDataRequest> httpUtilService;
	
	@Value("${um.employee.upsert}")
	private String UM_EMPLOYEE_CREATE;
	
	@Override
	@Transactional
	public ServiceResponse upsert(EmployeeRequest request) throws MoglixUserException, IOException {
		logger.info("Employe data request is "+new Gson().toJson(request));
		return messageBroker.createMessage(new Gson().toJson(request), ExchangeManager.EXCHANGE_EMPLOYEE, ApplicationConstants.TOPIC_EMPLOYEE_CREATE);
	}
	
	/**
	 * Calls user management to push employee data
	 */
	@Override
	public ServiceResponse httpcall(EmployeeDataRequest request, String url) throws MoglixUserException, IOException {
		return httpUtilService.httpPutCall(request, UM_EMPLOYEE_CREATE);
	}
	
}
