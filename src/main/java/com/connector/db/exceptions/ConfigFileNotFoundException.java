package com.connector.db.exceptions;

public class ConfigFileNotFoundException extends RuntimeException{

    final String message;

    public ConfigFileNotFoundException(String message) {
        this.message = message;
    }
}
