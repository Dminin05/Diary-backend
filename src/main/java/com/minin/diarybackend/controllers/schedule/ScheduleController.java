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

    @GetMapping("{groupTitle}")
    public List<ScheduleDto> findAllSchedulesByDateAndGroupTitle(
            @RequestBody ScheduleInfoRequest scheduleInfoRequest,
            @PathVariable String groupTitle
    ) {
        return scheduleService.findAllSchedulesByDateAndGroupTitle(
                scheduleInfoRequest.getDateFrom(),
                scheduleInfoRequest.getDateTo(),
                groupTitle
        );
    }

}
