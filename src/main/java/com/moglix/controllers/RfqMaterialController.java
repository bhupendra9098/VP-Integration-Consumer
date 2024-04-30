//package com.moglix.controllers;
//
//import com.moglix.exception.MoglixUserException;
//import com.moglix.response.ServiceResponse;
//import com.moglix.services.IRfqMaterialService;
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
//@RequestMapping("integrator/rfqmaterial/*")
//public class RfqMaterialController {
//
//    private static final Logger logger = LoggerFactory.getLogger(RfqMaterialController.class);
//
//    @Autowired
//    IRfqMaterialService iRfqMaterialService;
//
//    @RequestMapping("ping")
//    public String getModuleName(){
//        return "Rfq Material Controller looks fine.";
//    }
//
//    @RequestMapping(value = "upsert", method = RequestMethod.POST)
//    public ServiceResponse createMaterial(@RequestBody String request) throws MoglixUserException, IOException {
//        logger.debug("Request to create Rfq Material is : "+request);
//        return iRfqMaterialService.upsertStringRequest(request);
//    }
//}
