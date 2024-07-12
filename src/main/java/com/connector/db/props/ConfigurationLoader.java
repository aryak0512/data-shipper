package com.connector.db.props;

import com.connector.db.exceptions.ConfigFileNotFoundException;
import com.connector.db.utils.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class ConfigurationLoader {

    private ObjectMapper objectMapper;
    private Map<String, Object> configMap;

    public Map<String, Object> getConfigMap() {
        return configMap;
    }

    public void setConfigMap(Map<String, Object> configMap) {
        this.configMap = configMap;
    }

    public ConfigurationLoader(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Object get(String key){
        return configMap.get(key);
    }

    public Object getNested(String nestedKey){
        return JsonUtils.getNestedValue(configMap, nestedKey.split("\\."));
    }

    /**
     * reads the properties and populates a map of config
     *
     * @param propertyFilePath path to property path
     */
    public void loadProperties(String propertyFilePath) {

        log.info("Path : {}", propertyFilePath);
        File file = new File(propertyFilePath);
        Map<String, Object> configMap = new HashMap<>();

        if ( !file.exists() ) {
            throw new ConfigFileNotFoundException("Config path :" + propertyFilePath + " is not valid!");
        }

        try {
            configMap = objectMapper.readValue(file, new TypeReference<>() {
            });
        } catch (IOException e) {
            log.error("Exception occurred while reading config properties file : {}", e.getMessage(), e);
        }

        if ( configMap != null ) {
            try {
                log.info("Config read as : " + objectMapper
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(configMap));
            } catch (JsonProcessingException e) {
                log.error("Exception occurred while converting config file : {}", e.getMessage(), e);
            }
        }
        setConfigMap(configMap);
    }
}
