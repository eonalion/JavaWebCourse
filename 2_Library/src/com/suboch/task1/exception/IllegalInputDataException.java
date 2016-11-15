package com.suboch.task1.exception;

/**
 *
 */
public class IllegalInputDataException extends Exception {
    public IllegalInputDataException() {
    }

    public IllegalInputDataException(String message) {
        super(message);
    }

    public IllegalInputDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalInputDataException(Throwable cause) {
        super(cause);
    }
}
