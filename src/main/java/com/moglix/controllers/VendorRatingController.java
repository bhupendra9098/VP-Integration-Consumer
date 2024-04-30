//package com.moglix.controllers;
//
//import java.io.IOException;
//
//import com.google.gson.Gson;
//import com.moglix.services.IVendorRatingService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.moglix.exception.MoglixUserException;
//import com.moglix.request.VendorRatingRequest;
//import com.moglix.response.ServiceResponse;
//
//@RestController
//@RequestMapping("integrator/vendorrating/*")
//public class VendorRatingController {
//
//    @SuppressWarnings("unused")
//    private static final Logger logger = LoggerFactory.getLogger(Integrator.class);
//
//    @Autowired
//    IVendorRatingService service;
//
//    @RequestMapping("ping")
//    public String getModuleName(){
//        return "Vendor Controller looks fine.";
//    }
//
//    @RequestMapping(value = "upsert", method = RequestMethod.POST)
//    public ServiceResponse createVendor(@RequestBody VendorRatingRequest request) throws MoglixUserException, IOException {
//        logger.info("Request to create Vendor Rating is : "+new Gson().toJson(request));
//        return service.upsert(request);
//    }
//
//}
