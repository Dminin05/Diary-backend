package com.example.diarybackend.services.marks;

import com.example.diarybackend.controllers.marks.requests.MarkCreateRequest;
import com.example.diarybackend.models.Mark;

import java.util.List;
import java.util.UUID;

public interface IMarkService {

    Mark create(MarkCreateRequest markCreateRequest);

    List<Mark> findAllMarksByStudentId(UUID studentId);

    List<Mark> findAllMarksByStudentIdAndSubjectId(UUID studentId, UUID subjectId);

}
