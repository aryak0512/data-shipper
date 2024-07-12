package com.connector.db.props;

import com.connector.db.exceptions.ConfigFileNotFoundException;
import com.connector.db.exceptions.PropertiesNotLoadedException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author aryak
 *
 * @implNote Bean responsible for loading & caching of the input properties
 *
 * @apiNote This is a spring managed bean created by using @Bean instead of @Component
 * The custom way is adhered to because we have a call to loadProperties within constructor
 */
@Slf4j
public class ConfigurationLoader {

    private final ObjectMapper objectMapper;

    private Map<String, Object> configMap;

    public ConfigurationLoader(ObjectMapper objectMapper, String propertyFilePath) {
        this.objectMapper = objectMapper;
        loadProperties(propertyFilePath);
    }

    /**
     * loads the properties from file & populates the config map
     *
     * @param propertyFilePath path to property file
     */
    public void loadProperties(String propertyFilePath) {

        File file = new File(propertyFilePath);

        if ( !file.exists() ) {
            throw new ConfigFileNotFoundException("Config path :" + propertyFilePath + " is not valid!");
        }

        Map<String, Object> map = new HashMap<>();

        try {
            map = objectMapper.readValue(file, new TypeReference<>() {
            });
        } catch (IOException e) {
            log.error("Exception occurred while reading config properties file : {}", e.getMessage(), e);
        }

        if ( map != null ) {
            try {
                log.info("Config read as : {}", objectMapper
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(map));
            } catch (JsonProcessingException e) {
                log.error("Exception occurred while converting config file : {}", e.getMessage(), e);
            }
        } else {
            throw new PropertiesNotLoadedException("Property map not initialised at startup!");
        }

        setConfigMap(map);
    }

    public Map<String, Object> getConfigMap() {
        return configMap;
    }

    public void setConfigMap(Map<String, Object> configMap) {
        this.configMap = configMap;
    }
}
