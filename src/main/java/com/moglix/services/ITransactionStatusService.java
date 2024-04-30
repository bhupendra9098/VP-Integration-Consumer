package com.moglix.services;

import java.util.List;

import com.moglix.mongo.models.TransactionStatus;

public interface ITransactionStatusService {

	TransactionStatus findByTransactionId(String transactionId);

    void save(TransactionStatus transactionStatus);
    
    List<TransactionStatus> getAll(String status);
	
}
