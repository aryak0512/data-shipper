package com.connector.db.config;

import com.connector.db.exceptions.PropertyNotFoundException;
import com.connector.db.props.ConfigurationLoader;
import com.connector.db.utils.JsonUtils;
import org.springframework.stereotype.Component;

@Component
public class PropertyManager {

    private final ConfigurationLoader configurationLoader;

    public PropertyManager(ConfigurationLoader configurationLoader) {
        this.configurationLoader = configurationLoader;
    }

    public Object getNested(String nested){
        var value = JsonUtils.getNestedValue(configurationLoader.getConfigMap(), nested);
        if(value == null ){
            throw new PropertyNotFoundException("Property : " + nested + " does not exist in props file !");
        }
        return value;
    }
}
