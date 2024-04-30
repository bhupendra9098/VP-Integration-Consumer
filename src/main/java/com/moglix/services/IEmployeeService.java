package com.moglix.services;

import java.io.IOException;

import com.moglix.exception.MoglixUserException;
import com.moglix.request.EmployeeDataRequest;
import com.moglix.request.EmployeeRequest;
import com.moglix.response.ServiceResponse;

public interface IEmployeeService {

	ServiceResponse upsert(EmployeeRequest request) throws MoglixUserException, IOException;
	
	ServiceResponse httpcall(EmployeeDataRequest request, String url) throws MoglixUserException, IOException;
	
}
