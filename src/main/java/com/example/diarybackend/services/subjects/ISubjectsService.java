package com.example.diarybackend.services.subjects;

import com.example.diarybackend.dtos.SubjectDto;
import com.example.diarybackend.models.Subject;

public interface ISubjectsService {

    Subject create(SubjectDto subjectDto);

}
