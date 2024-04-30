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
//import com.moglix.services.ITripOutService;
//
//@RestController
//@RequestMapping("integrator/trip/*")
//public class TripOutController {
//
//	private static final Logger logger = LoggerFactory.getLogger(TripOutController.class);
//
//	@Autowired
//	ITripOutService service;
//
//	@RequestMapping("ping")
//	public String getModuleName(){
//		return "Trip Controller looks fine.";
//	}
//
//	@RequestMapping(value = "upsert", method = RequestMethod.POST)
//	public ServiceResponse createTripOut(@RequestBody String request) throws MoglixUserException, IOException {
//		logger.debug("Request to create Trip is : "+request);
//		return service.upsertStringRequest(request);
//	}
//
//}
