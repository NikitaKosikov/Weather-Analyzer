package com.senla.weather.web.scheduler;


import com.senla.weather.service.WeatherService;
import jakarta.annotation.PostConstruct;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

@Component
public class SchedulerSaveWeather {
    @Value(value = "${api.weather.api-end-point}")
    private String apiEndPoint;
    @Value(value = "${api.weather.location}")
    private String location;
    @Value(value = "${api.weather.api-key}")
    private String apiKey;
    @Value(value = "${api.weather.unit-group}")
    private String unitGroup;
    @Value(value = "${api.weather.include}")
    private String include;
    @Value(value = "${api.weather.content-type}")
    private String contentType;
    @Value(value = "${api.weather.period}")
    private String period;
    private final WeatherService weatherService;

    public SchedulerSaveWeather(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Scheduled(cron = "${api.weather.retry}")
    public void loadWeather() throws Exception {
        CloseableHttpResponse response = getHttpResponse();
        String forecasts = buildString(response);
        weatherService.load(forecasts);
    }


    private CloseableHttpResponse getHttpResponse() throws IOException, URISyntaxException {
        URIBuilder builder = new URIBuilder(apiEndPoint + location + "/" + period);
        builder.setParameter("unitGroup", unitGroup)
                .setParameter("key", apiKey)
                .setParameter("include",include)
                .setParameter("contentType", contentType);

        HttpGet get = new HttpGet(builder.build());

        CloseableHttpClient httpclient = HttpClients.createDefault();

        return httpclient.execute(get);
    }

    private String buildString(CloseableHttpResponse response) throws IOException {
        String rawResult=null;
        try (response) {
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new RuntimeException("Something went wrong with weather external api, status code:" + response.getStatusLine().getStatusCode());
            }

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                rawResult = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            }

        }
        return rawResult;
    }
}
