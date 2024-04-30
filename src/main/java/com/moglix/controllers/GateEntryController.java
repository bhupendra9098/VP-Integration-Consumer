//package com.moglix.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.moglix.response.ServiceResponse;
//import com.moglix.services.IGateEntryService;
//
//@RestController
//@RequestMapping("integrator/gateEntry/*")
//public class GateEntryController {
//
//	@Autowired
//	IGateEntryService service;
//
//	@RequestMapping(value = "ping", method = RequestMethod.GET)
//	public String getModuleName(){
//		return "Gate Entry module is working fine.";
//	}
//
//	@RequestMapping(value = "create", method = RequestMethod.POST)
//	public ServiceResponse createGateEntry(){
//		return service.createGateEntry();
//	}
//
//}
