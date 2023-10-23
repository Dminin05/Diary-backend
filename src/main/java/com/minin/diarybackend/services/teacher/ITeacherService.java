package com.minin.diarybackend.services.teacher;

import com.minin.diarybackend.controllers.auth.requests.TeacherRegisterRequest;
import com.minin.diarybackend.dtos.TeacherDto;
import com.minin.diarybackend.models.Teacher;

import java.util.List;
import java.util.UUID;

public interface ITeacherService {

    List<TeacherDto> findAll();

    Teacher findById(UUID id);

    TeacherDto findBaseInfoById(UUID id);

    Teacher create(TeacherRegisterRequest createRequest);

    Teacher findByIdentityId(UUID identityId);

    void deleteById(UUID id);

    void addGroupToTeacher(UUID teacherId, UUID groupId);

    void addSubjectToTeacher(UUID teacherId, UUID subjectId);

    void addRoleMethodist(UUID identityId);

}
