package com.example.diarybackend.controllers.marks;

import com.example.diarybackend.config.security.custom.CustomPrincipal;
import com.example.diarybackend.controllers.marks.requests.MarkCreateRequest;
import com.example.diarybackend.dtos.marks.AvgMark;
import com.example.diarybackend.dtos.marks.AvgMarkBySubjectDto;
import com.example.diarybackend.dtos.marks.MarkDto;
import com.example.diarybackend.models.Teacher;
import com.example.diarybackend.services.marks.IMarkService;
import com.example.diarybackend.services.teacher.ITeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public AvgMarkBySubjectDto findAvgMarkByStudentIdAndSubjectId(@PathVariable UUID studentId, @PathVariable UUID subjectId) {
        return markService.findAvgMarkByStudentIdAndSubjectId(studentId, subjectId);
    }

}
