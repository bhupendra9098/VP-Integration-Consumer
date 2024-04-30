package com.moglix.services.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.moglix.application.ExchangeManager;
import com.moglix.constants.ApplicationConstants;
import com.moglix.exception.MoglixUserException;
import com.moglix.mapper.ScheduleMapper;
import com.moglix.models.Schedule;
import com.moglix.response.ServiceResponse;
import com.moglix.services.IMessageBrokerService;
import com.moglix.services.IScheduleService;

@Service
public class ScheduleServiceImpl implements IScheduleService {

	private static final Logger logger = LoggerFactory.getLogger(HttpUtilServiceImpl.class);
	
	@Autowired
	IMessageBrokerService messageBroker;
	
	@Value("${jvp.schedule.create}")
	private String JVP_SCHEDULE_CREATE;
	
	@Override
	@Transactional
	public ServiceResponse upsert(JsonNode request) throws MoglixUserException, IOException {
		logger.info("Request body to create Schedule is : "+request);
		return messageBroker.createMessage(request.toString(), ExchangeManager.EXCHANGE_SCHEDULE, ApplicationConstants.TOPIC_SCHEDULE_CREATE);
	}

	@Override
	@Transactional
	public ServiceResponse upsertStringRequest(String request) throws MoglixUserException, IOException {
		Schedule schedule = ScheduleMapper.makeScheduleObjectFromJsonNode(request);
		JsonNode scheduleJson;
		try {
			scheduleJson = ScheduleMapper.fromSchObjectToVpSchList(schedule);
			return this.upsert(scheduleJson);
		} catch(Exception e) {
			ServiceResponse response = new ServiceResponse();
			response.setStatusCode(0);
			response.setErrorMsg(e.getMessage());
			return response;
		}
	}
	
}
