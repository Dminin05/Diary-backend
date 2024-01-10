package com.minin.diarybackend.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ScheduleDto {
    private String subject;
    private int pair;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Moscow")
    private Date date;
}
