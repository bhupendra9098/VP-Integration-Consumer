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
//import com.moglix.services.IMaterialService;
//
//@RestController
//@RequestMapping("integrator/material/*")
//public class MaterialController {
//
//    private static final Logger logger = LoggerFactory.getLogger(MaterialController.class);
//
//    @Autowired
//    IMaterialService service;
//
//    @RequestMapping("ping")
//    public String getModuleName(){
//        return "Material Controller looks fine.";
//    }
//
//    @RequestMapping(value = "upsert", method = RequestMethod.POST)
//    public ServiceResponse createMaterial(@RequestBody String request) throws MoglixUserException, IOException {
//        logger.debug("Request to create Material is : "+request);
//        return service.upsertStringRequest(request);
//    }
//
//}
