package com.moglix.services;

import com.moglix.exception.MoglixUserException;
import com.moglix.models.StorageLocation;
import com.moglix.response.ServiceResponse;

import java.io.IOException;

public interface IStorageLocationService {

    ServiceResponse upsertStringRequest(String request) throws MoglixUserException, IOException;

    ServiceResponse upsert(StorageLocation request) throws MoglixUserException, IOException;

}
