package com.moglix.application;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbiMQProperties {    
    
    @Value("${rabbitmq.publish}")
	private boolean RABBITMQ_PUBLISH_ENABLED;
	
    public static  boolean QUEUE_ENABLED;
    
    @PostConstruct
    public void init(){	
    	QUEUE_ENABLED = Boolean.valueOf(RABBITMQ_PUBLISH_ENABLED).booleanValue();
    }
}
