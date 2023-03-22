package com.senla.weather.service.converter;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class DateToSQLDateConverterTest {

    @Test
    void convertDateToDateSql() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date now = new java.util.Date();
        java.util.Date date = formatter.parse(formatter.format(now));
        Date expectedDate = new Date(date.getTime());
        DateToSQLDateConverter converter = new DateToSQLDateConverter();

        Date actualDate = converter.convert(now);

        assertEquals(expectedDate, actualDate);
    }
}