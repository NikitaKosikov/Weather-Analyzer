package com.senla.weather.service.parser.impl;

import com.senla.weather.domain.Weather;
import com.senla.weather.service.parser.WeatherParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.format.Parser;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WeatherJsonParserImpl implements WeatherParser {

    private static final String DAYS_FIELD_JSON = "days";
    private static final String ADDRESS_FIELD_JSON = "address";
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String TEMPERATURE_FIELD = "temp";
    private static final String WIND_SPEED_FIELD = "windspeed";
    private static final String HUMIDITY_FIELD = "humidity";
    private static final String PRESSURE_FIELD = "pressure";
    private static final String CONDITIONS_FIELD = "conditions";
    private static final String DATE_TIME_FIELD = "datetime";
    private static final Integer METERS_IN_KM = 1000;

    @Override
    public List<Weather> parse(String forecasts) throws ParseException {
        if (forecasts==null || forecasts.isEmpty()) {
            return List.of();
        }
        JSONObject forecastJson = new JSONObject(forecasts);
        return parse(forecastJson);
    }

    private List<Weather> parse(JSONObject forecastJson) throws ParseException {
        List<Weather> weather = new ArrayList<>();
        JSONArray days = forecastJson.getJSONArray(DAYS_FIELD_JSON);
        String address = forecastJson.getString(ADDRESS_FIELD_JSON);
        for (int i = 0; i < days.length(); i++) {
            JSONObject dayValue = days.getJSONObject(i);
            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

            Integer tempCelsius=dayValue.getInt(TEMPERATURE_FIELD);
            int windKph=dayValue.getInt(WIND_SPEED_FIELD);
            Integer humidityPercent=dayValue.getInt(HUMIDITY_FIELD);
            Integer atmosphericPressureMillibar = dayValue.getInt(PRESSURE_FIELD);
            String weatherCondition = dayValue.getString(CONDITIONS_FIELD);
            Date date = formatter.parse(dayValue.getString(DATE_TIME_FIELD));

            Integer windMetersPerHour = windKph * METERS_IN_KM;
            weather.add(new Weather(tempCelsius, windMetersPerHour ,atmosphericPressureMillibar, humidityPercent, weatherCondition, address, date));
        }
        return weather;
    }
}
