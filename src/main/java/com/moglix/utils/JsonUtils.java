package com.moglix.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    public static String readString(JsonNode node, String key, String defaultVal){
        JsonNode child = node.get(key);
        if(child == null){
            return defaultVal;
        }
        return child.asText();
    }

    public static Double readDouble(JsonNode node, String key, Double defaultVal){
        JsonNode child = node.get(key);
        if(child == null){
            return defaultVal;
        }
        return child.asDouble();
    }

    public static String toJson(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    public static int readInt(JsonNode node, String key, int defaultValue) {
        JsonNode child = node.get(key);
        if(child == null){
            return defaultValue;
        }
        return child.asInt();
    }

    public static <T> T readObject(String json, Class<T> classType) {
        T obj = null;
        try {
            obj = objectMapper.readValue(json, classType);
        } catch (IOException e) {
            LOGGER.error("Failed to parse jsonData", e);
            return null;
        }
        return obj;
    }

    public static JsonNode readTree(String json) throws IOException {
        return objectMapper.readTree(json);
    }
    
    public static ArrayNode returnArray(JsonNode node) throws ClassCastException {
    	ArrayNode arrayNode = null;
    	JsonNodeFactory jf = new JsonNodeFactory(false);
    	arrayNode = new ArrayNode(jf);
    	arrayNode.add(node);
    	return arrayNode;
    }
    
    public static Double retDouble(String data) {
    	return Double.parseDouble(data.replace(",",""));
    }

    public static Long readLong(JsonNode node, String name, Long defaultValue) {
        JsonNode child = node.get(name);
        if(child == null){
            return defaultValue;
        }
        return child.asLong();    }
	
}
