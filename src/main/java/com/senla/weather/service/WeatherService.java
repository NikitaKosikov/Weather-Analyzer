package com.senla.weather.service;

import com.senla.weather.domain.Weather;
import com.senla.weather.service.dto.PeriodRequestDTO;

import java.text.ParseException;

public interface WeatherService {
    Weather getCurrentWeather();

    Weather getAverageWeather(PeriodRequestDTO periodRequestDTO);

   void load(String forecasts) throws ParseException;
}
