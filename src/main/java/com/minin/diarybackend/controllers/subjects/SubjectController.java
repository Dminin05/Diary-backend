package com.minin.diarybackend.controllers.subjects;

import com.minin.diarybackend.dtos.SubjectDto;
import com.minin.diarybackend.models.Subject;
import com.minin.diarybackend.services.subjects.ISubjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
