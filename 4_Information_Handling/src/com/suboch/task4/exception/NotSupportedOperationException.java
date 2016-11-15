package com.suboch.task4.exception;

/**
 *
 */
public class NotSupportedOperationException extends Exception {
    public NotSupportedOperationException() {
        super();
    }

    public NotSupportedOperationException(String message) {
        super(message);
    }

    public NotSupportedOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotSupportedOperationException(Throwable cause) {
        super(cause);
    }
}
