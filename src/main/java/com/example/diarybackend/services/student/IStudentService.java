package com.example.diarybackend.services.student;

import com.example.diarybackend.controllers.identity.request.IdentityCreateRequest;
import com.example.diarybackend.models.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IStudentService {

    List<Student> findAll();

    Optional<Student> findById(UUID id);

    Student create(IdentityCreateRequest.StudentCreateRequest createRequest);

    Student update(Student admin);

    void deleteById(UUID id);

}
