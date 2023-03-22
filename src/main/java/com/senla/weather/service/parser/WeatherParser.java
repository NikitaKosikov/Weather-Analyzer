package com.senla.weather.service.parser;

import com.senla.weather.domain.Weather;

import java.text.ParseException;
import java.util.List;

public interface WeatherParser {
    List<Weather> parse(String forecasts) throws ParseException;
}
