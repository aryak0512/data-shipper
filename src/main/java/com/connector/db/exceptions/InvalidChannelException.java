package com.connector.db.exceptions;

public class InvalidChannelException extends RuntimeException{
    final String message;

    public InvalidChannelException(String message) {
        this.message = message;
    }
}
