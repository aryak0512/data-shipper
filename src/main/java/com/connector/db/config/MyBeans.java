package com.connector.db.config;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBeans {

    @Bean(value = "objectMapper")
    public ObjectMapper objectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDefaultPrettyPrinter(new DefaultPrettyPrinter());
        return mapper;
    }
}
