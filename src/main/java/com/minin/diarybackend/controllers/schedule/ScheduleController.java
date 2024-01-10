package com.minin.diarybackend.controllers.schedule;

import com.minin.diarybackend.controllers.schedule.requests.ScheduleCreateRequest;
import com.minin.diarybackend.controllers.schedule.requests.ScheduleInfoRequest;
import com.minin.diarybackend.dtos.ScheduleDto;
import com.minin.diarybackend.services.schedule.IScheduleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Schedule")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/schedule")
public class ScheduleController {

    private final IScheduleService scheduleService;

    @PostMapping("new")
    public void create(@RequestBody ScheduleCreateRequest scheduleCreateRequest) {
        scheduleService.create(scheduleCreateRequest);
    }

    @GetMapping("{groupId}")
    public List<ScheduleDto> findAllSchedulesByDateAndGroupId(
            @RequestBody ScheduleInfoRequest scheduleInfoRequest,
            @PathVariable UUID groupId
    ) {
        return scheduleService.findAllSchedulesByDateAndGroupId(
                scheduleInfoRequest.getDateFrom(),
                scheduleInfoRequest.getDateTo(),
                groupId
        );
    }

}
