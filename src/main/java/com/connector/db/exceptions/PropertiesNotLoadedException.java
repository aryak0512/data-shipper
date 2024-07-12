package com.connector.db.exceptions;

public class PropertiesNotLoadedException extends RuntimeException{

    final String message;

    public PropertiesNotLoadedException(String message) {
        this.message = message;
    }
}
