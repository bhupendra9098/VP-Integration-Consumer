package com.moglix.consumer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.moglix.application.ExchangeManager;
import com.moglix.constants.ApplicationConstants;
import com.moglix.models.StorageLocation;
import com.moglix.request.VendorRatingRequest;
import com.moglix.response.ServiceResponse;
import com.moglix.services.IHttpUtilJsonService;
import com.moglix.services.IHttpUtilService;
import com.moglix.services.ITransactionStatusService;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

@Component
public class VendorRatingReceiver extends QueueReceiver{

    private static final Logger logger = LoggerFactory.getLogger(VendorRatingReceiver.class);

    @Value("${jvp.vr.create}")
    private String JVP_VR_CREATE;

    @Autowired
    ITransactionStatusService transactionService;

    @Autowired
    IHttpUtilService<VendorRatingRequest> httpUtilService;

    @Autowired
    IHttpUtilJsonService httpUtilJsonService;

    @PostConstruct
    public void init() throws IOException, TimeoutException {
        exchange = ExchangeManager.EXCHANGE_VENDOR_RATING;
        queue = exchange + "-"+ ApplicationConstants.QUEUE_VENDOR_RATING_CREATE;
        routingKey = Arrays.asList(ApplicationConstants.TOPIC_VENDOR_RATING_CREATE);
        consumerTag = ApplicationConstants.TAG_VENDOR_RATING_CREATE;
        super.init();
    }

    @Override
    public void processMessage(String body, Long deliveryTag, AMQP.BasicProperties properties, Channel channel) {
        try {
            logger.info("Message recieved at the receiver is : "+body);
            ObjectMapper mapper = new ObjectMapper();
//            JsonNode sl = mapper.readTree(body);
            VendorRatingRequest sl = new VendorRatingRequest();
            sl = mapper.readValue(body, VendorRatingRequest.class);
            logger.info("reqeust to pass : "+sl);
            try {
                ServiceResponse response = httpUtilService.httpPostCall(sl, JVP_VR_CREATE);
                if(response.getStatusCode().equals(1)) {
//					TransactionStatus transactionStatus = new TransactionStatus(ApplicationConstants.STATUS_PASSED, ApplicationConstants.DATA_TYPE_TRIP, ApplicationConstants.NA, body);
//					transactionService.save(transactionStatus);
                }
                else {
/*
                    TransactionStatus transactionStatus = new TransactionStatus(ApplicationConstants.STATUS_FAILED, ApplicationConstants.DATA_TYPE_STORAGE_LOCATION, ApplicationConstants.NA, body);
                    transactionService.save(transactionStatus);
*/
                }
            } catch (Exception e) {
  /*              TransactionStatus transactionStatus = new TransactionStatus(ApplicationConstants.STATUS_FAILED, ApplicationConstants.DATA_TYPE_STORAGE_LOCATION, e.getMessage(), body);
                transactionService.save(transactionStatus);
  */              logger.info("Exception Occured at HTTP POST Call, Message is: "+e.getMessage());
            }
            channel.basicAck(deliveryTag, false);
        } catch(IOException ex) {
            logger.error("Exception: " + ex.getMessage(), ex);
        }
    }

}