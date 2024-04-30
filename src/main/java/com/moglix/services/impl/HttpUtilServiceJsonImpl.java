package com.moglix.services.impl;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moglix.constants.ApplicationConstants;
import com.moglix.exception.MoglixUserException;
import com.moglix.response.ServiceResponse;
import com.moglix.services.IHttpUtilJsonService;
import com.moglix.utils.JsonUtils;

@Service
public class HttpUtilServiceJsonImpl implements IHttpUtilJsonService {

	private static final Logger logger = LoggerFactory.getLogger(HttpUtilServiceJsonImpl.class);

	HttpClient httpClient = HttpClients.createDefault();

	@Override
	public ServiceResponse httpPostCall(JsonNode request, String url) throws MoglixUserException, IOException {
		HttpPost putRequest = new HttpPost(url);
		StringEntity stringEntity = new StringEntity(request.toString(), "UTF-8");
		stringEntity.setContentType("application/json");
		putRequest.setHeader("application", "1");
		putRequest.setHeader("token", "504d5bfe-8e7f-4139-86e2-b95d9b69nr2e");
		putRequest.setHeader("idCompany", "1");
		putRequest.setHeader("idBranch", "1");
		putRequest.setEntity(stringEntity);
		ServiceResponse response = new ServiceResponse();
		try{
			HttpResponse httpResponse = httpClient.execute(putRequest);
			String responseData = EntityUtils.toString(httpResponse.getEntity());
			JsonNode responseDataJson = JsonUtils.readTree(responseData);
			Integer responseCode = httpResponse.getStatusLine().getStatusCode();
			if(responseCode>=200 && responseCode<300){
				if(responseDataJson.has("successful") && responseDataJson.get("successful").toString().equals("true")){
					response.setStatusCode(1);
					response.setErrorMsg("");
				} else {
					response.setStatusCode(0);
					response.setErrorMsg(responseDataJson.get("message").toString());
				}
			} else {
				response.setStatusCode(0);
				response.setErrorMsg("Error Occured. Error Code is: "+responseCode);
			}
			logger.info(request.getClass()+" HTTP Response is : "+responseData);
			logger.info(request.getClass()+" HTTP Response Status Code : "+ httpResponse.getStatusLine().getStatusCode());
			logger.info(request.getClass()+" HTTP Response Reason Phrase : "+ httpResponse.getStatusLine().getReasonPhrase());
		} catch(Exception e){
			response.setStatusCode(0);
			response.setErrorMsg(e.getMessage());
			logger.info("Exception Occured at "+request.getClass()+" Exception is : "+e.getMessage());
			throw new MoglixUserException(ApplicationConstants.NO_CONNECTION, ApplicationConstants.NO_CONNECTION_MSG, ApplicationConstants.NO_CONNECTION_DATA);
		} finally {
			putRequest.releaseConnection();
		}
		return response;
	}

	@Override
	public ServiceResponse httpPutCall(JsonNode request, String url) throws MoglixUserException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		HttpPut putRequest = new HttpPut(url);
		StringEntity stringEntity = new StringEntity(request.toString(), "UTF-8");
		stringEntity.setContentType("application/json");
		putRequest.setHeader("application", "1");
		putRequest.setHeader("token", "504d5bfe-8e7f-4139-86e2-b95d9b69nr2e");
		putRequest.setHeader("idCompany", "1");
		putRequest.setHeader("idBranch", "1");
		putRequest.setEntity(stringEntity);
		ServiceResponse response = new ServiceResponse();
		try{
			HttpResponse httpResponse = httpClient.execute(putRequest);
			String responseDataString = EntityUtils.toString(httpResponse.getEntity());
			JsonNode responseJsonNode = mapper.readTree(responseDataString);
			Integer responseCode = httpResponse.getStatusLine().getStatusCode();
			if(responseCode>=200 && responseCode<300){
				response.setStatusCode(responseJsonNode.path("status_code").asInt());
				response.setErrorMsg(responseJsonNode.path("error_msg").asText());
			} else if(responseCode==500 || responseCode==502) {
				response.setStatusCode(0);
				response.setErrorMsg("Internal Server Error. Error code: "+responseCode);
			} else {
				response.setStatusCode(responseJsonNode.path("status_code").asInt());
				response.setErrorMsg(responseJsonNode.path("error_msg").asText());
			}
			logger.info(request.getClass()+" Response JSON : "+responseJsonNode);
		} catch(Exception e){
			response.setStatusCode(0);
			response.setErrorMsg(e.getMessage());
			logger.info(request.getClass()+" Exception is : "+e.getMessage());
			throw new MoglixUserException(ApplicationConstants.NO_CONNECTION, ApplicationConstants.NO_CONNECTION_MSG, ApplicationConstants.NO_CONNECTION_DATA);
		} finally {
			putRequest.releaseConnection();
		}
		return response;
	}

}
