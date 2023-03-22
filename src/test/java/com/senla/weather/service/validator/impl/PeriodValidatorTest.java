package com.senla.weather.service.validator.impl;

import com.senla.weather.service.dto.PeriodRequestDTO;
import com.senla.weather.service.exception.DateFormatInvalidException;
import com.senla.weather.service.validator.Validator;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.DataFormatException;

import static org.junit.jupiter.api.Assertions.*;

class PeriodValidatorTest {

    @Test
    void PeriodStartDateBeforeEndDate() throws ParseException {
        PeriodRequestDTO periodRequestDTO = new PeriodRequestDTO();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date from = formatter.parse("18-03-2023");
        Date to = formatter.parse("20-03-2023");
        periodRequestDTO.setFrom(new java.sql.Date(from.getTime()));
        periodRequestDTO.setTo(new java.sql.Date(to.getTime()));
        Validator<PeriodRequestDTO> periodValidator = new PeriodValidator();

        boolean actual = periodValidator.isValid(periodRequestDTO);

        assertTrue(actual);
    }

    @Test
    void PeriodEndDateEqualsStartDate() throws ParseException {
        PeriodRequestDTO periodRequestDTO = new PeriodRequestDTO();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date from = formatter.parse("20-03-2023");
        Date to = formatter.parse("20-03-2023");
        periodRequestDTO.setFrom(new java.sql.Date(from.getTime()));
        periodRequestDTO.setTo(new java.sql.Date(to.getTime()));
        Validator<PeriodRequestDTO> periodValidator = new PeriodValidator();

        boolean actual = periodValidator.isValid(periodRequestDTO);

        assertTrue(actual);
    }

    @Test
    void PeriodEndDateBeforeStartDate() throws ParseException {
        PeriodRequestDTO periodRequestDTO = new PeriodRequestDTO();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date from = formatter.parse("20-03-2023");
        Date to = formatter.parse("18-03-2023");
        periodRequestDTO.setFrom(new java.sql.Date(from.getTime()));
        periodRequestDTO.setTo(new java.sql.Date(to.getTime()));
        Validator<PeriodRequestDTO> periodValidator = new PeriodValidator();

        assertThrows(DateFormatInvalidException.class, () -> periodValidator.isValid(periodRequestDTO));
    }
    @Test
    void PeriodIsUninitialized() {
        PeriodRequestDTO periodRequestDTO = new PeriodRequestDTO();
        Validator<PeriodRequestDTO> periodValidator = new PeriodValidator();

        boolean actual = periodValidator.isValid(periodRequestDTO);

        assertFalse(actual);
    }
}