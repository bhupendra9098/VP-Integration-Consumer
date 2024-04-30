package com.moglix.services;

import com.moglix.response.ServiceResponse;

public interface IMessageBrokerService {

	public ServiceResponse createMessage(String body, String exchange, String routingKey);
}
