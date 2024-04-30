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
import com.moglix.request.EmployeeRequest;
import com.moglix.response.ServiceResponse;
import com.moglix.services.IHttpUtilService;
import com.moglix.services.ITransactionStatusService;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;

@Component
public class EmployeeReceiver extends QueueReceiver {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeReceiver.class);
	
	@Value("${um.employee.upsert}")
	private String JVP_EMPLOYEE_CREATE_URL;
	
	@Autowired
	IHttpUtilService<EmployeeRequest> httpUtilService;
	
	@Autowired
	ITransactionStatusService transactionService;

	@PostConstruct
	public void init() throws IOException, TimeoutException {
		exchange = ExchangeManager.EXCHANGE_EMPLOYEE;
		queue = exchange + "-"+ApplicationConstants.QUEUE_EMPLOYEE_CREATE;
		routingKey = Arrays.asList(ApplicationConstants.TOPIC_EMPLOYEE_CREATE);
		consumerTag = ApplicationConstants.TAG_EMPLOYEE_CREATE;
		super.init();
	}
	
	@Override
	public void processMessage(String body, Long deliveryTag, BasicProperties properties, Channel channel) {
		try {
			logger.info("Message recieved at the receiver is : "+body);
			EmployeeRequest employeeRequest = new ObjectMapper().readValue(body, EmployeeRequest.class);
			try {
				ServiceResponse response = httpUtilService.httpPostCall(employeeRequest, JVP_EMPLOYEE_CREATE_URL);
				if(response.getStatusCode().equals(1)) {
//					TransactionStatus transactionStatus = new TransactionStatus(ApplicationConstants.STATUS_PASSED, ApplicationConstants.DATA_TYPE_EMPLOYEE, ApplicationConstants.NA);
//					transactionService.save(transactionStatus);
				}
				else {
/*
					TransactionStatus transactionStatus = new TransactionStatus(ApplicationConstants.STATUS_FAILED, ApplicationConstants.DATA_TYPE_EMPLOYEE, ApplicationConstants.NA, body);
					transactionService.save(transactionStatus);
*/
				}
			} catch (MoglixUserException e) {
/*
				TransactionStatus transactionStatus = new TransactionStatus(ApplicationConstants.STATUS_FAILED, ApplicationConstants.DATA_TYPE_EMPLOYEE, e.getMessage(), body);
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
