package com.senla.weather.repository;

import com.senla.weather.domain.Weather;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface WeatherRepository extends ListCrudRepository<Weather, Long> {
    Optional<Weather> findFirstByDateOrderByIdDesc(Date date);
    List<Weather> findByDate(Date date);
    List<Weather> findWeatherByDateBetween(Date from, Date to);

}
