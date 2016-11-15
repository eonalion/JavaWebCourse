package com.suboch.task2.exception;

/**
 *
 */
public class RestaurantServiceException extends Exception{
    public RestaurantServiceException() {
    }

    public RestaurantServiceException(String message) {
        super(message);
    }

    public RestaurantServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestaurantServiceException(Throwable cause) {
        super(cause);
    }
}
