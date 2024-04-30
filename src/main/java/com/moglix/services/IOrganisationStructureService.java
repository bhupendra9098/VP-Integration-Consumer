package com.moglix.services;

import com.moglix.exception.MoglixUserException;
import com.moglix.models.OrganisationStructure;
import com.moglix.response.ServiceResponse;

import java.io.IOException;

/**
 * Created by nitesh on 14/11/17.
 */

public interface IOrganisationStructureService {
    ServiceResponse upsert(OrganisationStructure request) throws MoglixUserException, IOException;

    ServiceResponse httpCall(OrganisationStructure request, String url) throws MoglixUserException, IOException;
}
