package com.minin.diarybackend.controllers.marks;

import com.minin.diarybackend.config.security.custom.CustomPrincipal;
import com.minin.diarybackend.controllers.marks.requests.MarkCreateRequest;
import com.minin.diarybackend.controllers.marks.requests.MarkUpdateRequest;
import com.minin.diarybackend.dtos.marks.AttendanceDto;
import com.minin.diarybackend.dtos.marks.AvgMark;
import com.minin.diarybackend.dtos.marks.MarkDto;
import com.minin.diarybackend.services.marks.IMarkService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Marks")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/marks")
public class MarkController {

    private final IMarkService markService;

    @PostMapping("new")
    public void createNewMark(CustomPrincipal principal, @RequestBody MarkCreateRequest markCreateRequest) {
        markCreateRequest.setIdentityId(principal.getIdentityId());
        markService.create(markCreateRequest);
    }

    @PostMapping("{markId}")
    public void update(@PathVariable UUID markId, @RequestBody MarkUpdateRequest markUpdateRequest) {
        markService.update(markId, markUpdateRequest);
    }

    @GetMapping("{studentId}")
    public List<MarkDto> findMarksByStudentId(@PathVariable UUID studentId) {
        return markService.findAllMarksByStudentId(studentId);
    }

    @GetMapping("avg/{studentId}")
    public AvgMark findAvgMarkByStudentId(@PathVariable UUID studentId) {
        return markService.findAvgMarkByStudentId(studentId);
    }

    @GetMapping("attendance/{studentId}")
    public List<AttendanceDto> findAttendanceByStudentId(@PathVariable UUID studentId) {
        return markService.findAttendanceByStudentId(studentId);
    }

}
