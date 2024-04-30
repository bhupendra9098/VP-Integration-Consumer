package com.moglix.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moglix.application.ExchangeManager;
import com.moglix.constants.ApplicationConstants;
import com.moglix.models.Payment;
import com.moglix.mongo.models.TransactionStatus;
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

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

/**
 * Created by nitesh on 14/11/17.
 */

@Component
public class PaymentReceiver extends QueueReceiver {
    private static final Logger logger = LoggerFactory.getLogger(PaymentReceiver.class);

    @Value("${jvp.payment.create}")
    private String JVP_PAYMENT_CREATE;

    @Autowired
    ITransactionStatusService transactionService;

    @Autowired
    IHttpUtilService<Payment> httpUtilService;

    @PostConstruct
    public void init() throws IOException, TimeoutException {
        exchange = ExchangeManager.EXCHANGE_PAYMENT;
        queue =   ApplicationConstants.QUEUE_PAYMENT_CREATE;
        routingKey = Arrays.asList(ApplicationConstants.TOPIC_PAYMENT_CREATE);
        consumerTag = ApplicationConstants.TAG_PAYMENT_CREATE;
        super.init();
    }

    @Override
    public void processMessage(String body, Long deliveryTag, AMQP.BasicProperties properties, Channel channel) {
        try {
            logger.info("Message recieved at the receiver is : "+body);
            Payment payment = new ObjectMapper().readValue(body, Payment.class);
            try {
                ServiceResponse response = httpUtilService.httpPutCall(payment, JVP_PAYMENT_CREATE);
                if(response.getStatusCode().equals(1)) {
//                    TransactionStatus transactionStatus = new TransactionStatus(ApplicationConstants.STATUS_PASSED, ApplicationConstants.DATA_TYPE_PAYMENT, ApplicationConstants.NA);
//                    transactionService.save(transactionStatus);
                }
                else {
/*
                    TransactionStatus transactionStatus = new TransactionStatus(ApplicationConstants.STATUS_FAILED, ApplicationConstants.DATA_TYPE_PAYMENT, ApplicationConstants.NA, body);
                    transactionService.save(transactionStatus);
*/
                }
            } catch (Exception e) {
/*
                TransactionStatus transactionStatus = new TransactionStatus(ApplicationConstants.STATUS_FAILED, ApplicationConstants.DATA_TYPE_PAYMENT, e.getMessage(), body);
                transactionService.save(transactionStatus);
*/
                logger.info("Exception Occured at HTTP POST Call, Message is: "+e.getMessage());
            }
            channel.basicAck(deliveryTag, false);
        } catch(IOException ex) {
            logger.error("Exception: " + ex.getMessage(), ex);
        }
    }

}
