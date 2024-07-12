package com.connector.db;

import com.connector.db.exceptions.ConfigFileNotFoundException;
import com.connector.db.props.ConfigurationLoader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class DbPollerApplication implements CommandLineRunner {

    private ConfigurationLoader loader;

    public DbPollerApplication(ConfigurationLoader loader) {
        this.loader = loader;
    }

    public static void main(String[] args) {
        SpringApplication.run(DbPollerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String propertyFilePath = System.getProperty("config.path");
        loader.loadProperties(propertyFilePath);
    }
}
