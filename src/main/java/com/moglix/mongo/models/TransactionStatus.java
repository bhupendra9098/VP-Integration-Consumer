package com.moglix.mongo.models;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.moglix.constants.ApplicationConstants;

@Document(collection = "transaction_status")
public class TransactionStatus {

	@Id
	private String id;

	@Indexed(unique = true)
	private String transactionId = UUID.randomUUID().toString();
	private String message;
	private String status = ApplicationConstants.STATUS_NEW;
	private String data;
	private String dataType;
	private Long createdOn = System.currentTimeMillis();
    private Long updatedOn = System.currentTimeMillis();
    
    public TransactionStatus(String status, String dataType, String message){
    	this.message=message;
    	this.status=status;
    	this.dataType=dataType;
    	this.data="";
    }
    
    public TransactionStatus(String status, String dataType, String message, String data){
    	this.message=message;
    	this.status=status;
    	this.dataType=dataType;
    	this.data=data;
    }
    
    public TransactionStatus(){
    	
    }
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Long createdOn) {
		this.createdOn = createdOn;
	}

	public Long getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Long updatedOn) {
		this.updatedOn = updatedOn;
	}
		
}
