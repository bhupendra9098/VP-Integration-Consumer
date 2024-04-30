//package com.moglix.controllers;
//
//import com.google.gson.Gson;
//import com.moglix.exception.MoglixUserException;
//import com.moglix.models.OrganisationStructure;
//import com.moglix.response.ServiceResponse;
//import com.moglix.services.IOrganisationStructureService;
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
// * Created by nitesh on 14/11/17.
// */
//
//@RestController
//@RequestMapping("integrator/organisation/*")
//public class OrganisationStructureController {
//    private static final Logger logger = LoggerFactory.getLogger(OrganisationStructureController.class);
//
//    @Autowired
//    IOrganisationStructureService service;
//
//    @RequestMapping("ping")
//    public String getModuleName(){
//        return "OrganisationStructure Controller looks fine.";
//    }
//
//    @RequestMapping(value = "upsert", method = RequestMethod.POST)
//    public ServiceResponse createOrganisationStructure(@RequestBody OrganisationStructure request) throws MoglixUserException, IOException {
//        logger.debug("The OrganisationStructure is "+new Gson().toJson(request));
//        return service.upsert(request);
//    }
//}
