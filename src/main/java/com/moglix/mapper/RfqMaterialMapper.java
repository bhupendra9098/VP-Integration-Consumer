package com.moglix.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moglix.models.RfqMaterials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RfqMaterialMapper {
    private static final Logger logger = LoggerFactory.getLogger(TripOutMapper.class);

    private static ObjectMapper mapper = new ObjectMapper();

    public static RfqMaterials getMaterial(String request){
        RfqMaterials material = new RfqMaterials();
        try {
            JsonNode root = mapper.readTree(request);
            if(root.has("materialCode")) material.setMaterialCode(root.get("materialCode").asText().trim());
            if(root.has("materialName")) material.setMaterialName(root.get("materialName").asText().trim());
            if(root.has("uom")) material.setUom(root.get("uom").asText().trim());
        } catch(Exception e) {
            logger.debug("exception occured at while mapping json "+e.getMessage());
        }
        return material;
    }
}
