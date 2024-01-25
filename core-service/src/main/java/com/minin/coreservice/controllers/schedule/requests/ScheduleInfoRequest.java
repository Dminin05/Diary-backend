package com.minin.coreservice.controllers.schedule.requests;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ScheduleInfoRequest {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateFrom;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateTo;
}
