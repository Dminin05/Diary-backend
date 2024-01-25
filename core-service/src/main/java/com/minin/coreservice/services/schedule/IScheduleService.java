package com.minin.coreservice.services.schedule;


import com.minin.coreservice.controllers.schedule.requests.ScheduleCreateRequest;
import com.minin.dtos.ScheduleDto;
import com.minin.coreservice.models.Schedule;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface IScheduleService {

    void create(ScheduleCreateRequest scheduleCreateRequest);

    Schedule findById(UUID id);

    List<ScheduleDto> findAllSchedulesByDateAndGroupTitle(Date dateFrom, Date dateTo, String groupTitle);

}
