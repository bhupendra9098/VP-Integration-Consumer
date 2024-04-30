package com.moglix.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class BaseReceiver extends DefaultConsumer {

	private static final Logger logger = LoggerFactory.getLogger(BaseReceiver.class);
	private Channel channel;
	private QueueReceiver queueReceiver;
	
	public BaseReceiver(Channel channel, QueueReceiver queueReceiver) {
		super(channel);
		this.channel = channel;
		this.queueReceiver = queueReceiver;
	}

	@Override
	public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body){
		String message = new String(body);
		logger.info("Received Queue[" + consumerTag + "] Message: " + message);
		queueReceiver.processMessage(message, envelope.getDeliveryTag(), properties, channel);
	}

}
