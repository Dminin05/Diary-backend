package com.example.diarybackend.controllers.marks;

import com.example.diarybackend.config.security.custom.CustomPrincipal;
import com.example.diarybackend.controllers.marks.requests.MarkCreateRequest;
import com.example.diarybackend.models.Mark;
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
    public Mark createNewMark(CustomPrincipal principal, @RequestBody MarkCreateRequest markCreateRequest) {

        Teacher teacher = teacherService.findByIdentityId(principal.getIdentityId());
        markCreateRequest.setTeacherId(teacher.getId());

        return markService.create(markCreateRequest);
    }

    @GetMapping("{studentId}")
    public List<Mark> findMarksByStudentId(@PathVariable UUID studentId) {
        return markService.findAllMarksByStudentId(studentId);
    }

    @GetMapping("{studentId}/{subjectId}")
    public List<Mark> findMarksByStudentId(@PathVariable UUID studentId, @PathVariable UUID subjectId) {
        return markService.findAllMarksByStudentIdAndSubjectId(studentId, subjectId);
    }

}
