package com.moglix.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.moglix.mongo.models.TransactionStatus;
import com.moglix.mongo.repository.TransactionStatusRepository;
import com.moglix.services.ITransactionStatusService;

@Service
public class TransactionStatusServiceImpl implements ITransactionStatusService {

	@Autowired
	private TransactionStatusRepository repository;

	@Autowired
	MongoTemplate mongoTemplate;

	private static final Logger logger = LoggerFactory.getLogger(TransactionStatusServiceImpl.class);
	
	@Override
    public TransactionStatus findByTransactionId(String transactionId) {
        return repository.findByTransactionId(transactionId);
    }

    @Override
    public void save(TransactionStatus transactionStatus) {
    	transactionStatus.setUpdatedOn(System.currentTimeMillis());
        repository.save(transactionStatus);
    }
    
    @Override
    public List<TransactionStatus> getAll(String status){
    	Query query = new Query(new Criteria());
    	query.addCriteria(Criteria.where("status").in(status));
    	logger.info("Mongo Query : "+query);
    	List<TransactionStatus> docList = mongoTemplate.find(query, TransactionStatus.class);
    	return docList;
    }
	
}
