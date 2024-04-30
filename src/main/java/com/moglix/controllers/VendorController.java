//package com.moglix.controllers;
//
//import java.io.IOException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.moglix.exception.MoglixUserException;
//import com.moglix.request.VendorRequest;
//import com.moglix.response.ServiceResponse;
//import com.moglix.services.IVendorService;
//
//@RestController
//@RequestMapping("integrator/vendor/*")
//public class VendorController {
//
//	@SuppressWarnings("unused")
//	private static final Logger logger = LoggerFactory.getLogger(Integrator.class);
//
//	@Autowired
//	IVendorService service;
//
//	@RequestMapping("ping")
//	public String getModuleName(){
//		return "Vendor Controller looks fine.";
//	}
//
//	@RequestMapping(value = "upsert", method = RequestMethod.POST)
//	public ServiceResponse createVendor(@RequestBody VendorRequest request) throws MoglixUserException, IOException {
//		logger.debug("Request to create Vendor is : "+request);
//		return service.upsert(request);
//	}
//
//}
