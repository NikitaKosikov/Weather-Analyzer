package com.senla.weather.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Data
public class PeriodRequestDTO {
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date from;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date to;
}
