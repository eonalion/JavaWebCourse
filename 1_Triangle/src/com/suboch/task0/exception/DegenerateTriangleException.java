package com.suboch.task0.exception;


/**
 *
 */
public class DegenerateTriangleException extends Exception {
    public DegenerateTriangleException() {
        super();
    }

    public DegenerateTriangleException(String message, Throwable cause) {
        super(message, cause);
    }

    public DegenerateTriangleException(String message) {
        super(message);
    }

    public DegenerateTriangleException(Throwable cause) {
        super(cause);
    }

    protected DegenerateTriangleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
