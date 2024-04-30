package com.moglix.services;

import java.io.IOException;

import com.moglix.exception.MoglixUserException;
import com.moglix.models.Material;
import com.moglix.response.ServiceResponse;

public interface IMaterialService {

    ServiceResponse upsertStringRequest(String request) throws MoglixUserException, IOException;

    ServiceResponse upsert(Material request) throws MoglixUserException, IOException;

    ServiceResponse delete(String request) throws MoglixUserException, IOException;

}
