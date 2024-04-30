package com.moglix.services;

import java.io.IOException;

import com.moglix.exception.MoglixUserException;
import com.moglix.request.VendorDataRequest;
import com.moglix.request.VendorRequest;
import com.moglix.response.ServiceResponse;

public interface IVendorService {
	
	ServiceResponse upsert(VendorRequest request) throws MoglixUserException, IOException;
	
	ServiceResponse httpcall(VendorDataRequest request, String url) throws MoglixUserException, IOException;

}
