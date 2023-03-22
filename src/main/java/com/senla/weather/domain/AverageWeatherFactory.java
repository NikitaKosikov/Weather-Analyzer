package com.senla.weather.domain;

import com.senla.weather.service.exception.WeatherNotKnownException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AverageWeatherFactory {

    public static Weather createAverageWeather(List<Weather> weather) {
        if (weather==null || weather.isEmpty()){
            throw new WeatherNotKnownException("Weather is not known");
        }

        Weather total = weather.stream().reduce(new Weather(), Weather::accumulator);
        float numberForecast = weather.size();

        Integer avgTemperature = Math.round(total.getTemperature()/numberForecast);
        Integer avgWindSpeed = Math.round(total.getWindSpeed()/numberForecast);
        Integer avgPressure = Math.round(total.getAtmosphericPressure()/numberForecast);
        Integer avgHumidity = Math.round(total.getHumidity()/numberForecast);
        String frequentlyCondition = findFrequentlyWeatherCondition(weather);

        Weather averageWeather = new Weather();
        averageWeather.setTemperature(avgTemperature);
        averageWeather.setWindSpeed(avgWindSpeed);
        averageWeather.setAtmosphericPressure(avgPressure);
        averageWeather.setHumidity(avgHumidity);
        averageWeather.setWeatherCondition(frequentlyCondition);
        averageWeather.setLocation(weather.get(0).getLocation());

        return averageWeather;
    }
    private static String findFrequentlyWeatherCondition(List<Weather> weather){
        Map<String,Long> conditionsGroup = weather.stream()
                .collect(Collectors.groupingBy(Weather::getWeatherCondition, Collectors.counting()));

        Map.Entry<String, Long>  frequentlyCondition = conditionsGroup.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(1)
                .findAny()
                .orElseThrow(() -> new RuntimeException("Find frequently weather condition is invalid"));

        return frequentlyCondition.getKey();
    }
}
