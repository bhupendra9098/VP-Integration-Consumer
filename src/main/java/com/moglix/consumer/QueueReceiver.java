package com.moglix.consumer;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.moglix.constants.ApplicationConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public abstract class QueueReceiver {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageService.class);
	
	@Autowired
	private ConnectionFactory connectionFactory;
	
	@Autowired
	private MessageService messageService;
	private Connection connection;
	
	protected String exchange;
	protected String queue;
	protected List<String> routingKey;
	private boolean autoAck = false;
	private boolean durable = true;
	protected String consumerTag = "Moglix";
	private Channel channel;
	private int prefetchCount = ApplicationConstants.PREFETCH_COUNT;
	protected Map<String, Object> queueProperties = new HashMap<String, Object>(); 
	
	public void init() throws IOException, TimeoutException {
		//System.out.println("Queue Receiver Starts...........................................................................");
		
		connection = connectionFactory.newConnection();
		channel = connection.createChannel();
		channel.basicQos(prefetchCount);
		channel.basicRecover(true);
		messageService.subscribe(exchange, queue, routingKey, autoAck, durable, consumerTag,this,channel,queueProperties);
		//System.out.println("Queue Receiver endssssssssssssssssssss...........................................................................");
	}
	
	public void destroy() {
		try {
			channel.close();
			connection.close();
			logger.info("Queue Connection/Channel Closed for Receiver of" + this.exchange);
		} catch(IOException ex) {
			System.out.println(ex.getMessage());
		} catch(TimeoutException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public abstract void processMessage(String body, Long deliveryTag, AMQP.BasicProperties properties, Channel channel);
}
