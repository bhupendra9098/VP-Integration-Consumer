//package com.moglix.controllers;
//
//import java.io.IOException;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.moglix.exception.MoglixUserException;
//import com.moglix.mongo.models.TransactionStatus;
//import com.moglix.services.ITransactionStatusService;
//
//@RestController
//@RequestMapping("integrator/mongo/*")
//public class MongoController {
//
//	@Autowired
//	ITransactionStatusService service;
//
//	@RequestMapping("ping")
//	public String getModuleName(){
//		return "Mongo Controller looks fine.";
//	}
//
//	@RequestMapping(value = "save", method = RequestMethod.POST)
//	public void createTransaction(@RequestBody TransactionStatus request) throws MoglixUserException, IOException {
//		service.save(request);
//	}
//
//	@RequestMapping(value = "getByTransactionId/{transactionId}", method = RequestMethod.GET)
//	public TransactionStatus getTransactionById(@PathVariable String transactionId) throws MoglixUserException {
//		return service.findByTransactionId(transactionId);
//	}
//
//	@RequestMapping(value = "getByStatus/{status}", method = RequestMethod.GET)
//	public List<TransactionStatus> getTransactionByStatus(@PathVariable String status) throws MoglixUserException {
//		return service.getAll(status);
//	}
//
//}
