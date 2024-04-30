//package com.moglix.controllers;
//import com.moglix.exception.MoglixUserException;
//import com.moglix.response.ServiceResponse;
//import com.moglix.services.IGrnService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//
///**
// * Created by nitesh on 10/11/17.
// */
//@RestController
//@RequestMapping("integrator/grn/*")
//public class GrnController {
//    @Autowired
//    IGrnService service;
//
//    private static final Logger logger = LoggerFactory.getLogger(GrnController.class);
//
//    @RequestMapping (value="ping", method= RequestMethod.GET)
//    public String getModuleName(){
//        return "GRN module is working fine.";
//    }
//
//    @RequestMapping(value = "upsert", method = RequestMethod.POST)
//    public ServiceResponse create(@RequestBody String request)  throws MoglixUserException, IOException {
//        logger.debug("The GRN Request is : "+request);
//        return service.convertAndUpsert(request);
//    }
//}
