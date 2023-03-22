package com.senla.weather.domain;

import com.senla.weather.service.exception.WeatherNotKnownException;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AverageWeatherFactoryTest {

    @Test
    void createAverageWeather() {
        Weather expectedAverageWeather = new Weather(2,2,3,3,"test", "test", null);
        List<Weather> weather = List.of(
                new Weather(1,1,1,1,"test", "test", new Date()),
                new Weather(2,2,2,2,"test", "test", new Date()),
                new Weather(3,4,5,5,"test", "test", new Date())
        );

        Weather actualAverageWeather = AverageWeatherFactory.createAverageWeather(weather);

        assertEquals(expectedAverageWeather, actualAverageWeather);
    }

    @Test
    void forecastsIsNotKnown() {
        List<Weather> weather = List.of();

        assertThrows(WeatherNotKnownException.class, ()->AverageWeatherFactory.createAverageWeather(weather));
    }
}