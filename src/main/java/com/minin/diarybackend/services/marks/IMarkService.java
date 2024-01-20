package com.minin.diarybackend.services.marks;

import com.minin.diarybackend.controllers.marks.requests.MarkCreateRequest;
import com.minin.diarybackend.controllers.marks.requests.MarkUpdateRequest;
import com.minin.diarybackend.dtos.marks.AvgMark;
import com.minin.diarybackend.dtos.marks.MarkBaseInfo;
import com.minin.diarybackend.models.Mark;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface IMarkService {

    Mark findById(UUID id);

    void create(MarkCreateRequest markCreateRequest);

    void update(UUID markId, MarkUpdateRequest markUpdateRequest);

    Map<String, List<MarkBaseInfo>> findAllMarksByStudentId(UUID studentId);

    AvgMark findAvgMarkByStudentId(UUID studentId);

}
