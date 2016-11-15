package com.suboch.task1.exception;

/**
 *
 */
public class IllegalPublicationTypeException extends Exception {
    public IllegalPublicationTypeException() {
    }

    public IllegalPublicationTypeException(String message) {
        super(message);
    }

    public IllegalPublicationTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalPublicationTypeException(Throwable cause) {
        super(cause);
    }
}
