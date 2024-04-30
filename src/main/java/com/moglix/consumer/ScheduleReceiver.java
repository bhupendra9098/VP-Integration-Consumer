package com.moglix.consumer;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moglix.application.ExchangeManager;
import com.moglix.constants.ApplicationConstants;
import com.moglix.exception.MoglixUserException;
import com.moglix.models.SchedulingList;
import com.moglix.mongo.models.TransactionStatus;
import com.moglix.response.ServiceResponse;
import com.moglix.services.IHttpUtilService;
import com.moglix.services.IHttpUtilJsonService;
import com.moglix.services.ITransactionStatusService;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;

@Component
public class ScheduleReceiver extends QueueReceiver {
	
	private static final Logger logger = LoggerFactory.getLogger(ScheduleReceiver.class);
	
	@Value("${jvp.schedule.create}")
	private String JVP_SCHEDULE_CREATE;
	
	@Autowired
	IHttpUtilService<SchedulingList> httpUtilService;
	
	@Autowired
	IHttpUtilJsonService httpUtilServiceJson;
	
	@Autowired
	ITransactionStatusService transactionService;

	@PostConstruct
	public void init() throws IOException, TimeoutException {
		exchange = ExchangeManager.EXCHANGE_SCHEDULE;
		queue = exchange + "-"+ApplicationConstants.QUEUE_SCHEDULE_CREATE;
		routingKey = Arrays.asList(ApplicationConstants.TOPIC_SCHEDULE_CREATE);
		consumerTag = ApplicationConstants.TAG_SCHEDULE_CREATE;
		super.init();
	}
	
	@Override
	public void processMessage(String body, Long deliveryTag, BasicProperties properties, Channel channel) {
		try {
			logger.info("Message recieved at the receiver is : "+body);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode schedulingListJson = mapper.readTree(body);
			logger.info("ScheduleList : " + schedulingListJson);
			ServiceResponse response=null;
			try {
				response = httpUtilServiceJson.httpPutCall(schedulingListJson, JVP_SCHEDULE_CREATE);
				if(response.getStatusCode().equals(1) || response.getStatusCode().equals(-1)) {
//						TransactionStatus transactionStatus = new TransactionStatus(ApplicationConstants.STATUS_PASSED, ApplicationConstants.DATA_TYPE_SCHEDULE, ApplicationConstants.NA, body);
//						transactionService.save(transactionStatus);
				}
				else {
/*
					TransactionStatus transactionStatus = new TransactionStatus(ApplicationConstants.STATUS_FAILED, ApplicationConstants.DATA_TYPE_SCHEDULE, ApplicationConstants.NA, body);
					transactionService.save(transactionStatus);
*/
				}
			} catch (MoglixUserException | IOException e) {
/*
				TransactionStatus transactionStatus = new TransactionStatus(ApplicationConstants.STATUS_FAILED, ApplicationConstants.DATA_TYPE_SCHEDULE, e.getMessage(), body);
				transactionService.save(transactionStatus);
*/
				e.printStackTrace();
			}
			schedulingListJson.forEach(object->{

			});
			channel.basicAck(deliveryTag, false);
		} catch(IOException ex) {
			logger.error("Exception: " + ex.getMessage(), ex);
		}
	}
	@Configuration
	@EnableRabbit
	public class RabbitMQListenerConfig implements RabbitListenerConfigurer {

		@Autowired
		private ConnectionFactory connectionFactory;

		@Override
		public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
			registrar.setContainerFactory(rabbitListenerContainerFactory());
		}

		@Bean
		public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
			SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
			factory.setConnectionFactory(connectionFactory);
			factory.setConcurrentConsumers(10); // Set the minimum number of consumers
			factory.setMaxConcurrentConsumers(20); // Set the maximum number of consumers
			return factory;
		}
	}

}
