package com.senla.weather.service.validator;

public interface Validator<T> {
    boolean isValid(T t);
}
