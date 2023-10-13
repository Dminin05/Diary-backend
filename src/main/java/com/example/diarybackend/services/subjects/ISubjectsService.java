package com.example.diarybackend.services.subjects;

import com.example.diarybackend.dtos.SubjectDto;
import com.example.diarybackend.models.Subject;

import java.util.UUID;

public interface ISubjectsService {

    Subject findById(UUID id);

    Subject create(SubjectDto subjectDto);

}
