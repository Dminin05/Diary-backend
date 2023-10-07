package com.example.diarybackend.services.teacher;

import com.example.diarybackend.controllers.identity.request.IdentityCreateRequest;
import com.example.diarybackend.models.Teacher;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ITeacherService {

    List<Teacher> findAll();

    Optional<Teacher> findById(UUID id);

    Teacher create(IdentityCreateRequest.TeacherCreateRequest createRequest);

    Teacher update(Teacher admin);

    void deleteById(UUID id);

}
