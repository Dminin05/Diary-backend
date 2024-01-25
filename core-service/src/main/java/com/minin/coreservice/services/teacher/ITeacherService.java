package com.minin.coreservice.services.teacher;


import com.minin.coreservice.controllers.auth.requests.registration.TeacherRegisterRequest;
import com.minin.dtos.TeacherDto;
import com.minin.coreservice.models.Teacher;

import java.util.List;
import java.util.UUID;

public interface ITeacherService {

    List<TeacherDto> findAll();

    Teacher findById(UUID id);

    TeacherDto findBaseInfoById(UUID id);

    Teacher create(TeacherRegisterRequest createRequest);

    void deleteById(UUID id);

    void addGroupToTeacher(UUID teacherId, UUID groupId);

    void addSubjectToTeacher(UUID teacherId, UUID subjectId);

    void addRoleMethodist(UUID identityId);

}
