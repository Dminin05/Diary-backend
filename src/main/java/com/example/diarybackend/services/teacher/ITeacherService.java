package com.example.diarybackend.services.teacher;

import com.example.diarybackend.controllers.auth.requests.TeacherRegisterRequest;
import com.example.diarybackend.models.Teacher;

import java.util.List;
import java.util.UUID;

public interface ITeacherService {

    List<Teacher> findAll();

    Teacher findById(UUID id);

    Teacher create(TeacherRegisterRequest createRequest);

    Teacher update(Teacher admin);

    void deleteById(UUID id);

    void addGroupToTeacher(UUID teacherId, UUID groupId);

    void addSubjectToTeacher(UUID teacherId, UUID subjectId);

}
