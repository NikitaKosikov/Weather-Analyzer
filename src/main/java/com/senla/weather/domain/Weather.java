package com.senla.weather.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Entity
@Data
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "temperature")
    private Integer temperature;
    @Column(name = "wind_speed")
    private Integer windSpeed;
    @Column(name = "atmospheric_pressure")
    private Integer atmosphericPressure;
    private Integer humidity;
    @Column(name = "weather_condition")
    private String weatherCondition;
    @Column(name = "location")
    private String location;
    @Column(name = "date")
    private Date date;

    public Weather() {
    }

    public Weather(Integer temperature, Integer windSpeed, Integer atmosphericPressure, Integer humidity, String weatherCondition, String location, Date date) {
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.atmosphericPressure = atmosphericPressure;
        this.humidity = humidity;
        this.weatherCondition = weatherCondition;
        this.location = location;
        this.date = date;
    }

    public Weather accumulator(Weather that) {
        if (isUninitialized()){
            return that;
        }
        this.setTemperature(this.getTemperature() + that.getTemperature());
        this.setWindSpeed(this.getWindSpeed() + that.getWindSpeed());
        this.setAtmosphericPressure(this.getAtmosphericPressure() + that.getAtmosphericPressure());
        this.setHumidity(this.getHumidity() + that.getHumidity());
        return this;
    }

    public boolean isUninitialized(){
        return this.equals(new Weather());
    }
}
