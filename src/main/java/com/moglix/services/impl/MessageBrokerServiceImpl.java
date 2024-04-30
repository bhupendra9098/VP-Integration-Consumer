package com.moglix.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moglix.application.RabbiMQProperties;
import com.moglix.consumer.MessageService;
import com.moglix.response.ServiceResponse;
import com.moglix.services.IMessageBrokerService;

@Service
public class MessageBrokerServiceImpl implements IMessageBrokerService {
	private static final Logger logger = LoggerFactory.getLogger(MessageBrokerServiceImpl.class);
	
	@Autowired
   	private MessageService messageBroker;

	@Override
	public ServiceResponse createMessage(String body, String exchange, String routingKey) {
		ServiceResponse response = new ServiceResponse();
		logger.info("IS QUEUE ENABLED : "+RabbiMQProperties.QUEUE_ENABLED);
		if(RabbiMQProperties.QUEUE_ENABLED) {
			try {
				messageBroker.publishMessage(body, exchange, routingKey);
				response.setStatusCode(1);
				response.setErrorMsg("");
			} catch(Exception e) {
				response.setStatusCode(0);
				response.setErrorMsg(e.getMessage());
				logger.error(e.getMessage(), e);
			}
		}
		return response;
	}
}
