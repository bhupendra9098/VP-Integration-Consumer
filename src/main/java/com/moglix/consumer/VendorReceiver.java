package com.moglix.consumer;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moglix.application.ExchangeManager;
import com.moglix.constants.ApplicationConstants;
import com.moglix.exception.MoglixUserException;
import com.moglix.mongo.models.TransactionStatus;
import com.moglix.request.VendorRequest;
import com.moglix.response.ServiceResponse;
import com.moglix.services.IHttpUtilService;
import com.moglix.services.ITransactionStatusService;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;

@Component
public class VendorReceiver extends QueueReceiver {
	
	private static final Logger logger = LoggerFactory.getLogger(VendorReceiver.class);
	
	@Value("${um.vendor.upsert}")
	private String JVP_VENDOR_CREATE_URL;
	
	@Autowired
	IHttpUtilService<VendorRequest> httpUtilService;
	
	@Autowired
	ITransactionStatusService transactionService;

	@PostConstruct
	public void init() throws IOException, TimeoutException {
		exchange = ExchangeManager.EXCHANGE_VENDOR;
		queue = exchange + "-"+ApplicationConstants.QUEUE_VENDOR_CREATE;
		routingKey = Arrays.asList(ApplicationConstants.TOPIC_VENDOR_CREATE);
		consumerTag = ApplicationConstants.TAG_VENDOR_CREATE;
		super.init();
	}
	
	@Override
	public void processMessage(String body, Long deliveryTag, BasicProperties properties, Channel channel) {
		try {
			logger.info("Message recieved at the receiver is : "+body);
			VendorRequest vendorRequest = new ObjectMapper().readValue(body, VendorRequest.class);
			logger.info("inside process message"+vendorRequest.getMappedPlantDetails());
			try {
				ServiceResponse response = httpUtilService.httpPostCall(vendorRequest, JVP_VENDOR_CREATE_URL);
				if(response.getStatusCode().equals(1)) {
//					TransactionStatus transactionStatus = new TransactionStatus(ApplicationConstants.STATUS_PASSED, ApplicationConstants.DATA_TYPE_VENDOR, ApplicationConstants.NA);
//					transactionService.save(transactionStatus);
				}
				else {
/*
					TransactionStatus transactionStatus = new TransactionStatus(ApplicationConstants.STATUS_FAILED, ApplicationConstants.DATA_TYPE_VENDOR, ApplicationConstants.NA, body);
					transactionService.save(transactionStatus);
*/
				}
			} catch (MoglixUserException e) {
/*
				TransactionStatus transactionStatus = new TransactionStatus(ApplicationConstants.STATUS_FAILED, ApplicationConstants.DATA_TYPE_VENDOR, e.getMessage(), body);
				transactionService.save(transactionStatus);
*/
				e.printStackTrace();
			}
			channel.basicAck(deliveryTag, false);
		} catch(IOException ex) {
			logger.error("Exception: " + ex.getMessage(), ex);
		}
	}

}
