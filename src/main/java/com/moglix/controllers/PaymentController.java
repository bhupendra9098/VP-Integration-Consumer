//package com.moglix.controllers;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.Gson;
//import com.moglix.exception.MoglixUserException;
//import com.moglix.models.Payment;
//import com.moglix.response.ServiceResponse;
//import com.moglix.services.IPaymentService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by nitesh on 14/11/17.
// */
//
//@RestController
//@RequestMapping("integrator/payment/*")
//public class PaymentController {
//
//    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);
//
//    @Autowired
//    IPaymentService service;
//
//    @RequestMapping("ping")
//    public String getModuleName(){
//        return "Payment Controller looks fine.";
//    }
//
//    @RequestMapping(value = "upsert", method = RequestMethod.POST)
//    public ServiceResponse createPayment(@RequestBody Payment request) throws MoglixUserException, IOException {
//        logger.debug("The Payment is "+new Gson().toJson(request));
//        return service.upsert(request);
//    }
//}
