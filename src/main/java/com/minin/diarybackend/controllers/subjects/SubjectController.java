package com.minin.diarybackend.controllers.subjects;

import com.minin.diarybackend.dtos.SubjectDto;
import com.minin.diarybackend.models.Subject;
import com.minin.diarybackend.services.subjects.ISubjectsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Subjects")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/subjects")
public class SubjectController {

    private final ISubjectsService subjectsService;

    @PostMapping("new")
    public Subject createNewSubject(@RequestBody SubjectDto subjectDto) {
        return subjectsService.create(subjectDto);
    }

}
