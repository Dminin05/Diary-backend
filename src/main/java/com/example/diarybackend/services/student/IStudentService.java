package com.example.diarybackend.services.student;

import com.example.diarybackend.controllers.auth.requests.StudentRegisterRequest;
import com.example.diarybackend.models.Student;

import java.util.List;
import java.util.UUID;

public interface IStudentService {

    List<Student> findAll();

    Student findById(UUID id);

    Student create(StudentRegisterRequest createRequest);

    Student update(Student admin);

    void deleteById(UUID id);

}
