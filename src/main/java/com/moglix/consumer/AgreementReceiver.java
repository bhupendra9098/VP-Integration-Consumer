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
import com.moglix.models.Agreement;
import com.moglix.mongo.models.TransactionStatus;
import com.moglix.response.ServiceResponse;
import com.moglix.services.IHttpUtilService;
import com.moglix.services.IHttpUtilJsonService;
import com.moglix.services.ITransactionStatusService;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;

@Component
public class AgreementReceiver extends QueueReceiver {
	
	private static final Logger logger = LoggerFactory.getLogger(AgreementReceiver.class);
	
	@Value("${jvp.agreement.create}")
	private String JVP_AGREEMENT_CREATE;
	
	@Autowired
	ITransactionStatusService transactionService;

	@Autowired
	IHttpUtilService<Agreement> httpUtilService;
	
	@Autowired
	IHttpUtilJsonService httpUtilJsonService;

	@PostConstruct
	public void init() throws IOException, TimeoutException {
		exchange = ExchangeManager.EXCHANGE_AGREEMENT;
		queue = exchange + "-"+ApplicationConstants.QUEUE_AGREEMENT_CREATE;
		routingKey = Arrays.asList(ApplicationConstants.TOPIC_AGREEMENT_CREATE);
		consumerTag = ApplicationConstants.TAG_AGREEMENT_CREATE;
		super.init();
	}
	
	@Override
	public void processMessage(String body, Long deliveryTag, BasicProperties properties, Channel channel) {
		try {
			logger.info("Message recieved at the receiver is : "+body);
			ObjectMapper mapper = new ObjectMapper();
			JsonNode agreementJson = mapper.readTree(body);
			try {
				ServiceResponse response = httpUtilJsonService.httpPutCall(agreementJson, JVP_AGREEMENT_CREATE);
				if(response.getStatusCode().equals(1)) {
//					TransactionStatus transactionStatus = new TransactionStatus(ApplicationConstants.STATUS_PASSED, ApplicationConstants.DATA_TYPE_AGREEMENT, ApplicationConstants.NA, body);
//					transactionService.save(transactionStatus);
				}
				else {
/*
					TransactionStatus transactionStatus = new TransactionStatus(ApplicationConstants.STATUS_FAILED, ApplicationConstants.DATA_TYPE_AGREEMENT, ApplicationConstants.NA, body);
					transactionService.save(transactionStatus);
*/
				}
			} catch (Exception e) {
/*
				TransactionStatus transactionStatus = new TransactionStatus(ApplicationConstants.STATUS_FAILED, ApplicationConstants.DATA_TYPE_AGREEMENT, e.getMessage(), body);
				transactionService.save(transactionStatus);
*/
				logger.info("Exception Occured at HTTP POST Call, Message is: "+e.getMessage());
			}
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
			factory.setConcurrentConsumers(5); // Set the minimum number of consumers
			factory.setMaxConcurrentConsumers(10); // Set the maximum number of consumers
			return factory;
		}
	}

}
