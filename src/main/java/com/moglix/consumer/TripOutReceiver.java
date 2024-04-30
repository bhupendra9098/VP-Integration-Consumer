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
import com.moglix.models.TripOut;
import com.moglix.mongo.models.TransactionStatus;
import com.moglix.response.ServiceResponse;
import com.moglix.services.IHttpUtilService;
import com.moglix.services.ITransactionStatusService;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;

@Component
public class TripOutReceiver extends QueueReceiver {
	
	private static final Logger logger = LoggerFactory.getLogger(TripOutReceiver.class);
	
	@Value("${jvp.trip.create}")
	private String JVP_TRIP_CREATE;
	
	@Autowired
	ITransactionStatusService transactionService;

	@Autowired
	IHttpUtilService<TripOut> httpUtilService;

	@PostConstruct
	public void init() throws IOException, TimeoutException {
		exchange = ExchangeManager.EXCHANGE_TRIPOUT;
		queue = exchange + "-"+ApplicationConstants.QUEUE_TRIPOUT_CREATE;
		routingKey = Arrays.asList(ApplicationConstants.TOPIC_TRIPOUT_CREATE);
		consumerTag = ApplicationConstants.TAG_TRIPOUT_CREATE;
		super.init();
	}
	
	@Override
	public void processMessage(String body, Long deliveryTag, BasicProperties properties, Channel channel) {
		try {
			logger.info("Message recieved at the receiver is : "+body);
			ObjectMapper mapper = new ObjectMapper();
			TripOut tripOut = new TripOut();
			tripOut = mapper.readValue(body, TripOut.class);
			try {
				ServiceResponse response = httpUtilService.httpPutCall(tripOut, JVP_TRIP_CREATE);
				if(response.getStatusCode().equals(1)) {
//					TransactionStatus transactionStatus = new TransactionStatus(ApplicationConstants.STATUS_PASSED, ApplicationConstants.DATA_TYPE_TRIP, ApplicationConstants.NA, body);
//					transactionService.save(transactionStatus);
				}
				else {
/*
					TransactionStatus transactionStatus = new TransactionStatus(ApplicationConstants.STATUS_FAILED, ApplicationConstants.DATA_TYPE_TRIP, ApplicationConstants.NA, body);
					transactionService.save(transactionStatus);
*/
				}
			} catch (Exception e) {
/*
				TransactionStatus transactionStatus = new TransactionStatus(ApplicationConstants.STATUS_FAILED, ApplicationConstants.DATA_TYPE_TRIP, e.getMessage(), body);
				transactionService.save(transactionStatus);
*/
				logger.info("Exception Occured at HTTP POST Call, Message is: "+e.getMessage());
			}
			channel.basicAck(deliveryTag, false);
		} catch(IOException ex) {
			logger.error("Exception: " + ex.getMessage(), ex);
		}
	}

}
