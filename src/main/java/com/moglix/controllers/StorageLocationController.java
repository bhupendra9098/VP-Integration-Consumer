//package com.moglix.controllers;
//
//import com.moglix.exception.MoglixUserException;
//import com.moglix.response.ServiceResponse;
//import com.moglix.services.IStorageLocationService;
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
//@RestController
//@RequestMapping("integrator/sl/*")
//public class StorageLocationController {
//    private static final Logger logger = LoggerFactory.getLogger(StorageLocationController.class);
//
//    @Autowired
//    IStorageLocationService service;
//
//    @RequestMapping("ping")
//    public String getModuleName(){
//        return "Storage Controller looks fine.";
//    }
//
//    @RequestMapping(value = "upsert", method = RequestMethod.POST)
//    public ServiceResponse createStorageLocation(@RequestBody String request) throws MoglixUserException, IOException {
//        logger.debug("Request to create Storage Location is : "+request);
//        return service.upsertStringRequest(request);
//    }
//
//}
