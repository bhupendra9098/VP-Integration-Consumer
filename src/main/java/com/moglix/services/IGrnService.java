package com.moglix.services;

import com.moglix.models.Grn;
import com.moglix.response.ServiceResponse;

/**
 * Created by nitesh on 10/11/17.
 */
public interface IGrnService {
    
	ServiceResponse upsert(Grn request);
	
	ServiceResponse convertAndUpsert(String request);
	
}
