package com.example.diarybackend.services.marks;

import com.example.diarybackend.controllers.marks.requests.MarkCreateRequest;
import com.example.diarybackend.dtos.MarkDto;

import java.util.List;
import java.util.UUID;

public interface IMarkService {

    MarkDto create(MarkCreateRequest markCreateRequest);

    List<MarkDto> findAllMarksByStudentId(UUID studentId);

    List<MarkDto> findAllMarksByStudentIdAndSubjectId(UUID studentId, UUID subjectId);

}
