package com.moglix.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moglix.models.StorageLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StorageLocationMapper {

    private static final Logger logger = LoggerFactory.getLogger(StorageLocationMapper.class);

    private static ObjectMapper mapper = new ObjectMapper();

    public static StorageLocation getSLobject(String request){
        StorageLocation storageLocation = new StorageLocation();
        try {
            JsonNode root = mapper.readTree(request);
            if(root.has("plantCode")) storageLocation.setPlantCode(root.get("plantCode").asText().trim());
            if(root.has("slCode")) storageLocation.setSlCode(root.get("slCode").asText().trim());
            if(root.has("description")) storageLocation.setDescription(root.get("description").asText().trim());
            if(root.has("material")) storageLocation.setMaterial(root.get("material").asText().trim());
            if(root.has("materialName")) storageLocation.setMaterialName(root.get("materialName").asText().trim());
            
        } catch(Exception e) {
            logger.debug("exception occured at while mapping json "+e.getMessage());
        }
        return storageLocation;
    }

}
