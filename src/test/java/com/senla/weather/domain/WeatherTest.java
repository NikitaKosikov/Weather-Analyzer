package com.senla.weather.domain;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class WeatherTest {

    @Test
    void accumulateNumericWeatherData() {
        Weather expected = new Weather(2,2,2,2,"test", "test", new Date());
        Weather initial =
                new Weather(1,1,1,1, "test", "test", new Date());
        Weather accumulated =
                new Weather(1,1,1,1, "test", "test", new Date());

        Weather actualWeather = initial.accumulator(accumulated);

        assertEquals(expected, actualWeather);
    }

    @Test
    void accumulateNumericWeatherDataTheInitialWeatherIsUninitialized() {
        Weather expected = new Weather(1,1,1,1, "test", "test", new Date());
        Weather initial = new Weather();
        Weather accumulated =
                new Weather(1,1,1,1, "test", "test", new Date());

        Weather actualWeather = initial.accumulator(accumulated);

        assertEquals(expected, actualWeather);
    }



    @Test
    void weatherIsUninitialized() {
        Weather weather = new Weather();

        boolean isUninitialized = weather.isUninitialized();

        assertTrue(isUninitialized);
    }

    @Test
    void weatherIsInitialized() {
        Weather weather = new Weather(0,0,0,0,"","",new Date());

        boolean isUninitialized = weather.isUninitialized();

        assertFalse(isUninitialized);
    }
}