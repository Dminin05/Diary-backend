package com.minin.diarybackend.services.schedule;

import com.minin.diarybackend.controllers.schedule.requests.ScheduleCreateRequest;
import com.minin.diarybackend.dtos.ScheduleDto;
import com.minin.diarybackend.models.Schedule;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface IScheduleService {

    void create(ScheduleCreateRequest scheduleCreateRequest);

    Schedule findById(UUID id);

    List<ScheduleDto> findAllSchedulesByDateAndGroupId(Date dateFrom, Date dateTo, UUID groupId);

}
