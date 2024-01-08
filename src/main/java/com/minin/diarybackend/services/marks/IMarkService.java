package com.minin.diarybackend.services.marks;

import com.minin.diarybackend.controllers.marks.requests.MarkCreateRequest;
import com.minin.diarybackend.controllers.marks.requests.MarkUpdateRequest;
import com.minin.diarybackend.dtos.marks.AvgMark;
import com.minin.diarybackend.dtos.marks.MarkDto;
import com.minin.diarybackend.models.Mark;

import java.util.List;
import java.util.UUID;

public interface IMarkService {

    Mark findById(UUID id);

    MarkDto create(MarkCreateRequest markCreateRequest);

    void update(UUID markId, MarkUpdateRequest markUpdateRequest);

    List<MarkDto> findAllMarksByStudentId(UUID studentId);

    List<MarkDto> findAllMarksByStudentIdAndSubjectId(UUID studentId, UUID subjectId);

    AvgMark findAvgMarkByStudentIdAndSubjectId(UUID studentId, UUID subjectId);

    AvgMark findAvgMarkByStudentId(UUID studentId);

}
