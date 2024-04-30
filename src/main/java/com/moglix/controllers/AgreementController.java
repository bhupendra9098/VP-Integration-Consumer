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
//import com.moglix.response.ServiceResponse;
//import com.moglix.services.IAgreementService;
//
//@RestController
//@RequestMapping("integrator/agreement/*")
//public class AgreementController {
//
//	private static final Logger logger = LoggerFactory.getLogger(AgreementController.class);
//
//	@Autowired
//	IAgreementService service;
//
//	@RequestMapping("ping")
//	public String getModuleName(){
//		return "Agreement Controller looks fine.";
//	}
//
//	@RequestMapping(value = "upsert", method = RequestMethod.POST)
//	public ServiceResponse createAgreement(@RequestBody String request) throws MoglixUserException, IOException {
//		logger.info("Request to create Agreement is : "+request);
//		ServiceResponse response = service.upsertStringRequest(request);
//		logger.info("resonse on create agreement is :" + response);
//		return response;
//	}
//
//}
