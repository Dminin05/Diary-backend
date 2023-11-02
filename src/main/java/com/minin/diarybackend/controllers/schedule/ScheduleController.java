package com.minin.diarybackend.controllers.schedule;

import com.minin.diarybackend.controllers.schedule.requests.ScheduleCreateRequest;
import com.minin.diarybackend.dtos.ScheduleDto;
import com.minin.diarybackend.services.schedule.IScheduleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("{id}")
    public ScheduleDto findById(@PathVariable UUID id) {
        return scheduleService.getBaseInfoById(id);
    }

}
