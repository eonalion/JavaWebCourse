package com.suboch.task2.exception;

/**
 *
 */
public class SingletonException extends Exception {
    public SingletonException() {
    }

    public SingletonException(String message) {
        super(message);
    }

    public SingletonException(String message, Throwable cause) {
        super(message, cause);
    }

    public SingletonException(Throwable cause) {
        super(cause);
    }
}
