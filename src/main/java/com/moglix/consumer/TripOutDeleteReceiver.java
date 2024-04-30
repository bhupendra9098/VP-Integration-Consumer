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
public class TripOutDeleteReceiver extends QueueReceiver {
	
	private static final Logger logger = LoggerFactory.getLogger(TripOutDeleteReceiver.class);
	
	@Value("${jvp.trip.delete}")
	private String JVP_TRIP_DELETE;
	
	@Autowired
	ITransactionStatusService transactionService;

	@Autowired
	IHttpUtilService<TripOut> httpUtilService;

	@PostConstruct
	public void init() throws IOException, TimeoutException {
		exchange = ExchangeManager.EXCHANGE_TRIPOUT;
		queue = exchange + "-"+ApplicationConstants.QUEUE_TRIPOUT_DELETE;
		routingKey = Arrays.asList(ApplicationConstants.TOPIC_TRIPOUT_DELETE);
		consumerTag = ApplicationConstants.TAG_TRIPOUT_DELETE;
		super.init();
	}
	
	@Override
	public void processMessage(String body, Long deliveryTag, BasicProperties properties, Channel channel) {
		try {
			logger.info("Message recieved at the receiver is : "+body);
			try {
				String deleteUrl = JVP_TRIP_DELETE+"/"+body;
				ServiceResponse response = httpUtilService.httpDeleteCall(deleteUrl);
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
