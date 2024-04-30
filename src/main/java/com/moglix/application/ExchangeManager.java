package com.moglix.application;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Component
public class ExchangeManager {    
	
	private static final Logger logger = LoggerFactory.getLogger(ExchangeManager.class);
	
	@Autowired
	private ConnectionFactory connectionFactory;
	
	@Value("${spring.rabbitmq.username}")
	private String RABBITMQ_USERNAME;
	
	@Value("${spring.rabbitmq.password}")
	private String RABBITMQ_PASSWORD;
	
	@Value("${spring.rabbitmq.host}")
	private String RABBITMQ_HOST;
	
	@Value("${spring.rabbitmq.port}")
	private Integer RABBITMQ_PORT;

	public static final String DEFAULT_ROUTING_KEY = "SapIntegrator";
	
	public static String EXCHANGE_AGREEMENT = "Agreement";
	public static String EXCHANGE_SCHEDULE = "Schedule";
	public static String EXCHANGE_GRN = "Grn";
	public static String EXCHANGE_PAYMENT = "Payment";
	public static String EXCHANGE_ORGANISATION_STRUCTURE = "Organisation-Structure";
	public static String EXCHANGE_EMPLOYEE = "Employee";
	public static String EXCHANGE_VENDOR = "Vendor";
	public static String EXCHANGE_VENDOR_PLANT_MAPPING = "Vendor-Plant-Mapping";
	public static String EXCHANGE_TRIPOUT = "Tripout";
	public static String EXCHANGE_MATERIAL= "Material";
	public static String EXCHANGE_STORAGE_LOCATION= "SL";
	public static String EXCHANGE_VENDOR_RATING = "VR";
	public static String EXCHANGE_RFQMATERIAL= "Rfq-Material";

	@PostConstruct
    public void init(){	
    	try {
    		connectionFactory.setHost(RABBITMQ_HOST);
    		connectionFactory.setUsername(RABBITMQ_USERNAME);
    		connectionFactory.setPassword(RABBITMQ_PASSWORD);
    		connectionFactory.setPort(RABBITMQ_PORT);
	    	Connection connection = connectionFactory.newConnection();
			Channel channel = connection.createChannel();
	    	channel.exchangeDeclare(EXCHANGE_AGREEMENT, BuiltinExchangeType.TOPIC, true, false, new HashMap<String, Object>());
	    	channel.exchangeDeclare(EXCHANGE_SCHEDULE, BuiltinExchangeType.TOPIC, true, false, new HashMap<String, Object>());
	    	channel.exchangeDeclare(EXCHANGE_GRN, BuiltinExchangeType.TOPIC, true, false, new HashMap<String, Object>());
	    	channel.exchangeDeclare(EXCHANGE_PAYMENT, BuiltinExchangeType.TOPIC, true, false, new HashMap<String, Object>());
	    	channel.exchangeDeclare(EXCHANGE_ORGANISATION_STRUCTURE, BuiltinExchangeType.TOPIC, true, false, new HashMap<String, Object>());
	    	channel.exchangeDeclare(EXCHANGE_EMPLOYEE, BuiltinExchangeType.TOPIC, true, false, new HashMap<String, Object>());
	    	channel.exchangeDeclare(EXCHANGE_VENDOR, BuiltinExchangeType.TOPIC, true, false, new HashMap<String, Object>());
	    	channel.exchangeDeclare(EXCHANGE_VENDOR_PLANT_MAPPING, BuiltinExchangeType.TOPIC, true, false, new HashMap<String, Object>());
	    	channel.exchangeDeclare(EXCHANGE_TRIPOUT, BuiltinExchangeType.TOPIC, true, false, new HashMap<String, Object>());
			channel.exchangeDeclare(EXCHANGE_MATERIAL, BuiltinExchangeType.TOPIC, true, false, new HashMap<String, Object>());
			channel.exchangeDeclare(EXCHANGE_STORAGE_LOCATION, BuiltinExchangeType.TOPIC, true, false, new HashMap<String, Object>());
			channel.exchangeDeclare(EXCHANGE_VENDOR_RATING, BuiltinExchangeType.TOPIC, true, false, new HashMap<String,Object>());
			channel.exchangeDeclare(EXCHANGE_RFQMATERIAL, BuiltinExchangeType.TOPIC, true, false, new HashMap<String, Object>());
		} catch(Exception e) {
    		logger.error(e.getMessage(), e);
    	}
    }
}
