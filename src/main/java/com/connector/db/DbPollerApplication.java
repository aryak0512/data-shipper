package com.connector.db;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class DbPollerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DbPollerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
