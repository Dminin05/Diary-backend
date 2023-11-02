package com.minin.diarybackend.services.schedule;

import com.minin.diarybackend.controllers.schedule.requests.ScheduleCreateRequest;
import com.minin.diarybackend.dtos.ScheduleDto;
import com.minin.diarybackend.models.Schedule;

import java.util.UUID;

public interface IScheduleService {

    void create(ScheduleCreateRequest scheduleCreateRequest);

    Schedule findById(UUID id);

    ScheduleDto getBaseInfoById(UUID id);

}
