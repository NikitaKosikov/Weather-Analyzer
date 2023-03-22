package com.senla.weather.web.controller;

import com.senla.weather.domain.Weather;
import com.senla.weather.service.WeatherService;
import com.senla.weather.service.dto.PeriodRequestDTO;
import com.senla.weather.service.dto.WeatherDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/weather")
public class WeatherController {
    private final WeatherService weatherService;
    private final ModelMapper modelMapper;

    public WeatherController(WeatherService weatherService, ModelMapper modelMapper) {
        this.weatherService = weatherService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/now")
    public ResponseEntity<WeatherDTO> getCurrentWeather(){
        Weather weather = weatherService.getCurrentWeather();
        WeatherDTO weatherDTO = modelMapper.map(weather, WeatherDTO.class);
        return ResponseEntity.ok(weatherDTO);
    }

    @GetMapping("/average")
    public ResponseEntity<WeatherDTO> getAverageWeather(PeriodRequestDTO periodRequestDTO){
        Weather weather = weatherService.getAverageWeather(periodRequestDTO);
        WeatherDTO averageWeatherDTO = modelMapper.map(weather, WeatherDTO.class);
        return ResponseEntity.ok(averageWeatherDTO);
    }
}
