package com.senla.weather.service.exception;

public class DateFormatInvalidException extends RuntimeException {
    public DateFormatInvalidException() {
    }

    public DateFormatInvalidException(String message) {
        super(message);
    }

    public DateFormatInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public DateFormatInvalidException(Throwable cause) {
        super(cause);
    }
}
