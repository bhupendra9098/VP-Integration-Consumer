package com.moglix.mapper;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moglix.models.Material;
import com.moglix.utils.DateUtils;
import com.moglix.utils.NumberValidationUtils;

public class MaterialMapper {

    private static final Logger logger = LoggerFactory.getLogger(TripOutMapper.class);

    private static ObjectMapper mapper = new ObjectMapper();

    public static Material getMaterial(String request){
        Material material = new Material();
        try {
            JsonNode root = mapper.readTree(request);
            if(root.has("materialCode")) material.setMaterialCode(root.get("materialCode").asText().trim());
            if(root.has("materialName")) material.setMaterialName(root.get("materialName").asText().trim());
            if(root.has("materialType")) material.setMaterialType(root.get("materialType").asText().trim());
            if(root.has("hsnCode")) material.setHsnCode(root.get("hsnCode").asText().trim());
            if(root.has("price")) material.setPrice(root.get("price").asText().trim());
            if(root.has("uom")) material.setUom(root.get("uom").asText().trim());
        } catch(Exception e) {
            logger.debug("exception occured at while mapping json "+e.getMessage());
        }
        return material;
    }

}
