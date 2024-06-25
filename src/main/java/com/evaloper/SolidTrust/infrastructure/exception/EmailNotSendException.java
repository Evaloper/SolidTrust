package com.evaloper.SolidTrust.infrastructure.exception;

public class EmailNotSendException extends RuntimeException {
    public EmailNotSendException(String message) {
        super(message);
    }
}

