package com.minin.coreservice.services.subjects;


import com.minin.dtos.SubjectDto;
import com.minin.coreservice.models.Subject;

import java.util.UUID;

public interface ISubjectsService {

    Subject findById(UUID id);

    Subject create(SubjectDto subjectDto);

}
