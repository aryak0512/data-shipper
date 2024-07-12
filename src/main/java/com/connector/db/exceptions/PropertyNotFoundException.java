package com.connector.db.exceptions;

public class PropertyNotFoundException extends RuntimeException{
    final String message;

    public PropertyNotFoundException(String message) {
        this.message = message;
    }
}
