//package com.moglix.controllers;
//
//import java.io.IOException;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.moglix.exception.MoglixUserException;
//import com.moglix.request.EmployeeRequest;
//import com.moglix.response.ServiceResponse;
//import com.moglix.services.IEmployeeService;
//
//@RestController
//@RequestMapping("integrator/employee/*")
//public class EmployeeController {
//
//	@SuppressWarnings("unused")
//	private static final Logger logger = LoggerFactory.getLogger(Integrator.class);
//
//	@Autowired
//	IEmployeeService service;
//
//	@RequestMapping("ping")
//	public String getModuleName(){
//		return "Employee Controller looks fine.";
//	}
//
//	@RequestMapping(value = "upsert", method = RequestMethod.POST)
//	public ServiceResponse createEmployee(@RequestBody EmployeeRequest request) throws MoglixUserException, IOException {
//		return service.upsert(request);
//	}
//
//}
