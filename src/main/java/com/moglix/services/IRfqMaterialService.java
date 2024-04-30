package com.moglix.services;

import com.moglix.exception.MoglixUserException;
import com.moglix.models.RfqMaterials;
import com.moglix.response.ServiceResponse;

import java.io.IOException;

public interface IRfqMaterialService {

    ServiceResponse upsertStringRequest(String request) throws MoglixUserException, IOException;

    ServiceResponse upsert(RfqMaterials request) throws MoglixUserException, IOException;

    ServiceResponse delete(String request) throws MoglixUserException, IOException;

}
