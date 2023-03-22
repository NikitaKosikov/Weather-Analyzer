package com.senla.weather.service.impl;

import com.senla.weather.domain.AverageWeatherFactory;
import com.senla.weather.domain.Weather;
import com.senla.weather.repository.WeatherRepository;
import com.senla.weather.service.WeatherService;
import com.senla.weather.service.converter.DateToSQLDateConverter;
import com.senla.weather.service.dto.PeriodRequestDTO;
import com.senla.weather.service.exception.WeatherNotKnownException;
import com.senla.weather.service.parser.WeatherParser;
import com.senla.weather.service.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {
    private final WeatherRepository weatherRepository;
    private final WeatherParser weatherParser;
    private final Validator<PeriodRequestDTO> periodValidator;
    private final DateToSQLDateConverter converter;

    @Autowired
    public WeatherServiceImpl(WeatherRepository weatherRepository, WeatherParser weatherParser, Validator<PeriodRequestDTO> periodValidator, DateToSQLDateConverter converter) {
        this.weatherRepository = weatherRepository;
        this.weatherParser = weatherParser;
        this.periodValidator = periodValidator;
        this.converter = converter;
    }
    @Override
    public Weather getCurrentWeather(){
        Date today = converter.convert(new java.util.Date());
        return weatherRepository.findFirstByDateOrderByIdDesc(today)
                .orElseThrow(() -> new WeatherNotKnownException("Weather is not known on this date: " + today)
        );
    }
    @Override
    public Weather getAverageWeather(PeriodRequestDTO periodRequestDTO){
        List<Weather> weather;
        if (periodValidator.isValid(periodRequestDTO)){
            Date from = converter.convert(periodRequestDTO.getFrom());
            Date to = converter.convert(periodRequestDTO.getTo());
            weather = weatherRepository.findWeatherByDateBetween(from, to);
        }else {
            Date today = converter.convert(new java.util.Date());
            weather = weatherRepository.findByDate(today);
        }
        return AverageWeatherFactory.createAverageWeather(weather);
    }
    @Override
    public void load(String forecasts) throws ParseException {
        List<Weather> weather = weatherParser.parse(forecasts);
        weatherRepository.saveAll(weather);
    }
}
