package com.minin.coreservice.services.marks;

import com.minin.coreservice.controllers.marks.requests.MarkCreateRequest;
import com.minin.coreservice.controllers.marks.requests.MarkUpdateRequest;
import com.minin.dtos.marks.AttendanceDto;
import com.minin.dtos.marks.AvgMark;
import com.minin.dtos.marks.MarkDto;
import com.minin.coreservice.models.Mark;

import java.util.List;
import java.util.UUID;

public interface IMarkService {

    Mark findById(UUID id);

    void create(MarkCreateRequest markCreateRequest);

    void update(UUID markId, MarkUpdateRequest markUpdateRequest);

    List<MarkDto> findAllMarksByStudentId(UUID studentId);

    AvgMark findAvgMarkByStudentId(UUID studentId);

    List<AttendanceDto> findAttendanceByStudentId(UUID studentId);

}
