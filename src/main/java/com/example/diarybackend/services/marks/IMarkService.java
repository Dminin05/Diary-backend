package com.example.diarybackend.services.marks;

import com.example.diarybackend.controllers.marks.requests.MarkCreateRequest;
import com.example.diarybackend.dtos.MarkDto;
import com.example.diarybackend.models.Mark;

import java.util.List;
import java.util.UUID;

public interface IMarkService {

    MarkDto create(MarkCreateRequest markCreateRequest);

    List<Mark> findAllMarksByStudentId(UUID studentId);

    List<Mark> findAllMarksByStudentIdAndSubjectId(UUID studentId, UUID subjectId);

}
