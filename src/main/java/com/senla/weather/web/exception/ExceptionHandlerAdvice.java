package com.senla.weather.web.exception;

import com.senla.weather.service.exception.DateFormatInvalidException;
import com.senla.weather.service.exception.WeatherNotKnownException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(WeatherNotKnownException.class)
    public ProblemDetail weatherNotKnownException(WeatherNotKnownException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setTitle("Weather is not known");
        problemDetail.setType(URI.create("http://localhost:8080/errors/weather-not-known"));
        return problemDetail;
    }

    @ExceptionHandler(DateFormatInvalidException.class)
    public ProblemDetail dateFormatInvalidException(DateFormatInvalidException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setTitle("Date format is invalid");
        problemDetail.setType(URI.create("http://localhost:8080/errors/date-invalid"));
        return problemDetail;
    }
}
