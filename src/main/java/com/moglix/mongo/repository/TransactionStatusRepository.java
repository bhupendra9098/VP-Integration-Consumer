package com.moglix.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.moglix.mongo.models.TransactionStatus;

public interface TransactionStatusRepository extends MongoRepository<TransactionStatus, String> {

	public TransactionStatus findByTransactionId(String transactionId);
	
}
