package com.senla.weather.service.validator.impl;

import com.senla.weather.service.dto.PeriodRequestDTO;
import com.senla.weather.service.exception.DateFormatInvalidException;
import com.senla.weather.service.validator.Validator;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class PeriodValidator implements Validator<PeriodRequestDTO> {

    @Override
    public boolean isValid(PeriodRequestDTO periodRequestDTO){
        if (periodRequestDTO==null){
            return false;
        }
        Date startDate = periodRequestDTO.getFrom();
        Date endDate = periodRequestDTO.getTo();

        if (startDate==null||endDate==null){
           return false;
        }else if (endDate.before(startDate)){
            throw new DateFormatInvalidException("End date = " + endDate + " goes before start date = " + startDate);
        }
        return true;
    }
}
