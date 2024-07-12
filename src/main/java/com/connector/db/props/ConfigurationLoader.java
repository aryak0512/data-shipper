package com.connector.db.props;

import com.connector.db.exceptions.ConfigFileNotFoundException;
import com.connector.db.exceptions.PropertiesNotLoadedException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author aryak
 * Bean responsible for read configurations supplied by property file.
 * The file path is provided via environment variable <code>-config.path</code>
 */
@Component
@Slf4j
public class ConfigurationLoader {

    private final ObjectMapper objectMapper;

    @Setter
    @Getter
    private Map<String, Object> configMap;

    public ConfigurationLoader(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * loads the properties from file & populates the config map
     *
     * @param propertyFilePath path to property path
     */
    public void loadProperties(String propertyFilePath) {

        log.info("Properties file path : {}", propertyFilePath);
        File file = new File(propertyFilePath);

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
                log.info("Config read as : {}", objectMapper
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(configMap));
            } catch (JsonProcessingException e) {
                log.error("Exception occurred while converting config file : {}", e.getMessage(), e);
            }
        } else {
            throw new PropertiesNotLoadedException("Property map not initialised at startup!");
        }
    }
}
