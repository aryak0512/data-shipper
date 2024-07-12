package com.connector.db.config;

import com.connector.db.exceptions.PropertyNotFoundException;
import com.connector.db.props.ConfigurationLoader;
import com.connector.db.utils.JsonUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author aryak
 *
 * @apiNote bean managing reading of all properties in the JSON config
 */
@Component
public class PropertyManager {

    private final ConfigurationLoader configurationLoader;

    public PropertyManager(ConfigurationLoader configurationLoader) {
        this.configurationLoader = configurationLoader;
    }

    public Object getProperty(String nested) {

        Map<String, Object> map = configurationLoader.getConfigMap();
        var value = JsonUtils.getNestedValue(map, nested.split("\\."));

        if ( value == null ) {
            throw new PropertyNotFoundException("Property : " + nested + " does not exist in props file !");
        }
        return value;
    }
}
