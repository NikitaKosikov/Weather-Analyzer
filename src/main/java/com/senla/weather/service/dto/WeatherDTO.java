package com.senla.weather.service.dto;

import lombok.Data;
@Data
public class WeatherDTO {
    private Integer temperature;
    private Integer windSpeed;
    private Integer atmosphericPressure;
    private Integer humidity;
    private String weatherCondition;
    private String location;

}
