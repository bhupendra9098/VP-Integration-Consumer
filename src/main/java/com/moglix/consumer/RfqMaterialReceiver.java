package com.moglix.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moglix.application.ExchangeManager;
import com.moglix.constants.ApplicationConstants;
import com.moglix.models.RfqMaterials;
import com.moglix.response.ServiceResponse;
import com.moglix.services.IHttpUtilService;
import com.moglix.services.ITransactionStatusService;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.rabbitmq.client.AMQP.BasicProperties;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

@Component
public class RfqMaterialReceiver extends QueueReceiver {

    private static final Logger logger = LoggerFactory.getLogger(RfqMaterialReceiver.class);

    @Value("${jvp.rfqmaterial.upsert}")
    private String JVP_RFQMATERIAL_CREATE;

    @Autowired
    ITransactionStatusService transactionService;

    @Autowired
    IHttpUtilService<RfqMaterials> httpUtilService;

    @PostConstruct
    public void init() throws IOException, TimeoutException {
        exchange = ExchangeManager.EXCHANGE_RFQMATERIAL;
        queue = exchange + "-"+ApplicationConstants.QUEUE_RFQMATERIAL_CREATE;
        routingKey = Arrays.asList(ApplicationConstants.TOPIC_RFQ_MATERIAL_CREATE);
        consumerTag = ApplicationConstants.TAG_RFQMATERIAL_CREATE;
        super.init();
    }

    @Override
    public void processMessage(String body, Long deliveryTag, BasicProperties properties, Channel channel) {
        try {
            logger.info("Message recieved at the receiver is : "+body);
            ObjectMapper mapper = new ObjectMapper();
            RfqMaterials material= (RfqMaterials) mapper.readValue(body, RfqMaterials.class);
            try {
                logger.info("update url on vp-api-> "+ JVP_RFQMATERIAL_CREATE);
                ServiceResponse response = httpUtilService.httpPutCall(material, JVP_RFQMATERIAL_CREATE);
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
