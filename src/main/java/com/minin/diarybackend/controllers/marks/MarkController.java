package com.minin.diarybackend.controllers.marks;

import com.minin.diarybackend.config.security.custom.CustomPrincipal;
import com.minin.diarybackend.controllers.marks.requests.MarkCreateRequest;
import com.minin.diarybackend.controllers.marks.requests.MarkUpdateRequest;
import com.minin.diarybackend.dtos.marks.AvgMark;
import com.minin.diarybackend.dtos.marks.MarkDto;
import com.minin.diarybackend.models.Teacher;
import com.minin.diarybackend.services.marks.IMarkService;
import com.minin.diarybackend.services.teacher.ITeacherService;
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
    private final ITeacherService teacherService;

    @PostMapping("new")
    public MarkDto createNewMark(CustomPrincipal principal, @RequestBody MarkCreateRequest markCreateRequest) {

        Teacher teacher = teacherService.findByIdentityId(principal.getIdentityId());
        markCreateRequest.setTeacherId(teacher.getId());

        return markService.create(markCreateRequest);
    }

    @PostMapping("{markId}")
    public void update(@PathVariable UUID markId, @RequestBody MarkUpdateRequest markUpdateRequest) {
        markService.update(markId, markUpdateRequest);
    }

    @GetMapping("{studentId}")
    public List<MarkDto> findMarksByStudentId(@PathVariable UUID studentId) {
        return markService.findAllMarksByStudentId(studentId);
    }

    @GetMapping("{studentId}/{subjectId}")
    public List<MarkDto> findMarksByStudentIdAndSubjectId(@PathVariable UUID studentId, @PathVariable UUID subjectId) {
        return markService.findAllMarksByStudentIdAndSubjectId(studentId, subjectId);
    }

    @GetMapping("avg/{studentId}")
    public AvgMark findAvgMarkByStudentId(@PathVariable UUID studentId) {
        return markService.findAvgMarkByStudentId(studentId);
    }

    @GetMapping("avg/{studentId}/{subjectId}")
    public AvgMark findAvgMarkByStudentIdAndSubjectId(@PathVariable UUID studentId, @PathVariable UUID subjectId) {
        return markService.findAvgMarkByStudentIdAndSubjectId(studentId, subjectId);
    }

}
