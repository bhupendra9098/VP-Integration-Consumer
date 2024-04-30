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
import com.moglix.models.Material;
import com.moglix.mongo.models.TransactionStatus;
import com.moglix.response.ServiceResponse;
import com.moglix.services.IHttpUtilService;
import com.moglix.services.ITransactionStatusService;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;

@Component
public class MaterialReceiver extends QueueReceiver {

    private static final Logger logger = LoggerFactory.getLogger(MaterialReceiver.class);

    @Value("${jvp.material.update}")
    private String JVP_MATERIAL_CREATE;

    @Autowired
    ITransactionStatusService transactionService;

    @Autowired
    IHttpUtilService<Material> httpUtilService;

    @PostConstruct
    public void init() throws IOException, TimeoutException {
        exchange = ExchangeManager.EXCHANGE_MATERIAL;
        queue = exchange + "-"+ApplicationConstants.QUEUE_MATERIAL_CREATE;
        routingKey = Arrays.asList(ApplicationConstants.TOPIC_MATERIAL_CREATE);
        consumerTag = ApplicationConstants.TAG_MATERIAL_CREATE;
        super.init();
    }

    @Override
    public void processMessage(String body, Long deliveryTag, BasicProperties properties, Channel channel) {
        try {
            logger.info("Message recieved at the receiver is : "+body);
            ObjectMapper mapper = new ObjectMapper();
            Material material= (Material) mapper.readValue(body, Material.class);
            try {
                logger.info("update url on vp-api-> "+ JVP_MATERIAL_CREATE);
                ServiceResponse response = httpUtilService.httpPutCall(material, JVP_MATERIAL_CREATE);
                if(response.getStatusCode().equals(1)) {}
                else {
/*
                    TransactionStatus transactionStatus = new TransactionStatus(ApplicationConstants.STATUS_FAILED, ApplicationConstants.DATA_TYPE_MATERIAL, ApplicationConstants.NA, body);
                    transactionService.save(transactionStatus);
*/
                }
            } catch (Exception e) {
/*
                TransactionStatus transactionStatus = new TransactionStatus(ApplicationConstants.STATUS_FAILED, ApplicationConstants.DATA_TYPE_TRIP, e.getMessage(), body);
                transactionService.save(transactionStatus);
*/
                logger.info("Exception Occured at HTTP PUT Call, Message is: "+e.getMessage());
            }
            channel.basicAck(deliveryTag, false);
        } catch(IOException ex) {
            logger.error("Exception: " + ex.getMessage(), ex);
        }
    }

}
