package com.minin.diarybackend.services.subjects;

import com.minin.diarybackend.dtos.SubjectDto;
import com.minin.diarybackend.models.Subject;

import java.util.UUID;

public interface ISubjectsService {

    Subject findById(UUID id);

    Subject create(SubjectDto subjectDto);

}
