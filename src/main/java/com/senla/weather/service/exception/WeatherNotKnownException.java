package com.senla.weather.service.exception;

public class WeatherNotKnownException extends RuntimeException{
    public WeatherNotKnownException() {
    }

    public WeatherNotKnownException(String message) {
        super(message);
    }

    public WeatherNotKnownException(String message, Throwable cause) {
        super(message, cause);
    }

    public WeatherNotKnownException(Throwable cause) {
        super(cause);
    }
}
