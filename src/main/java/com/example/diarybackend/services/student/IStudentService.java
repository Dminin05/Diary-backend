package com.example.diarybackend.services.student;

import com.example.diarybackend.controllers.auth.requests.IdentityRegisterRequest;
import com.example.diarybackend.dtos.StudentDto;
import com.example.diarybackend.models.Student;

import java.util.List;
import java.util.UUID;

public interface IStudentService {

    List<Student> findAll();

    StudentDto findById(UUID id);

    Student create(IdentityRegisterRequest.StudentCreateRequest createRequest);

    Student update(Student admin);

    void deleteById(UUID id);

}
