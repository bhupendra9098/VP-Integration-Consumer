package com.moglix.consumer;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.moglix.constants.ApplicationConstants;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Service
public class MessageService {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageService.class);
	
	@Value("${rabbitmq.publish}")
	private boolean RABBITMQ_PUBLISH_ENABLED;
	
	@Autowired
	private ConnectionFactory connectionFactory;
	private Channel channel;
	private int prefetchCount = ApplicationConstants.PREFETCH_COUNT;
	private int deliveryMode = ApplicationConstants.DELIVERY_MODE;
	private Connection connection;
	
	@PostConstruct
	public void init() throws IOException, TimeoutException {
		if(RABBITMQ_PUBLISH_ENABLED) {
			connection = connectionFactory.newConnection();
			channel = connection.createChannel();
			channel.basicQos(prefetchCount);
			channel.basicRecover(true);
		}
	}
	
	public BaseReceiver subscribe(String exchange, String queue, List<String> routingKey, boolean autoAck, boolean durable, String consumerTag, QueueReceiver queueReceiver, Channel channel, Map<String, Object> queueProperties) throws IOException {
		channel.queueDeclare(queue, durable, false, false, queueProperties);
		for(String bindingKey : routingKey) {
			channel.queueBind(queue, exchange, bindingKey);
		}
		//channel.queueBind(queue, exchange, routingKey);
		BaseReceiver receiver = new BaseReceiver(channel, queueReceiver);
		channel.basicConsume(queue, autoAck, consumerTag, receiver);
		return receiver;
	}
	
	public void publishMessage(String body, String exchange, String routingKey) throws IOException {
		//channel.exchangeDeclare(exchange, BuiltinExchangeType.FANOUT, true, false, new HashMap<String, Object>());
		try {
			BasicProperties properties = new BasicProperties().builder().deliveryMode(deliveryMode).expiration("999999999").messageId(UUID.randomUUID().toString()).build();
			channel.basicPublish(exchange, routingKey, properties, body.getBytes());
		} catch(IOException ex) {
			logger.error(ex.getMessage(), ex);
			throw ex;
		}
	}
	
	@PreDestroy
	public void destroy() {
		try {
			channel.close();
			connection.close();
			logger.info("Queue Connection/Channel Closed for publisher");
		} catch(IOException ex) {
			logger.error(ex.getMessage(), ex);
		} catch(TimeoutException ex) {
			logger.error(ex.getMessage(), ex);
		}
	}
}
