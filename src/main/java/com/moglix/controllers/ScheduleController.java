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
//import com.moglix.services.IScheduleService;
//
//@RestController
//@RequestMapping("integrator/schedule/*")
//public class ScheduleController {
//
//	private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);
//
//	@Autowired
//	IScheduleService service;
//
//	@RequestMapping("ping")
//	public String getModuleName(){
//		return "Schedule Controller looks fine.";
//	}
//
//	@RequestMapping(value = "upsert", method = RequestMethod.POST)
//	public ServiceResponse createSchedule(@RequestBody String request) throws MoglixUserException, IOException {
//		logger.debug("Request to create Schedule is : "+request);
//		return service.upsertStringRequest(request);
//	}
//
//}
