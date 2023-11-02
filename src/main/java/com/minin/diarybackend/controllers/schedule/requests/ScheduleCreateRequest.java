package com.minin.diarybackend.controllers.schedule.requests;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class ScheduleCreateRequest {
    private UUID teacherId;
    private UUID groupId;
    private UUID subjectId;
    private int pair;
    private Date date;
}
