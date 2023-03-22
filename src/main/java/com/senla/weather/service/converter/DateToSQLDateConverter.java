package com.senla.weather.service.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DateToSQLDateConverter implements Converter<Date, java.sql.Date> {
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public java.sql.Date convert(Date source) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        try {
            source = formatter.parse(formatter.format(source));
        } catch (ParseException e) {
            throw new RuntimeException("Can't convert date to dateSql");
        }
        return new java.sql.Date(source.getTime());
    }
}
