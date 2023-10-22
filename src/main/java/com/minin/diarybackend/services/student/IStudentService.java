package com.minin.diarybackend.services.student;

import com.minin.diarybackend.controllers.auth.requests.StudentRegisterRequest;
import com.minin.diarybackend.models.Student;

import java.util.List;
import java.util.UUID;

public interface IStudentService {

    List<Student> findAll();

    Student findById(UUID id);

    Student create(StudentRegisterRequest createRequest);

    Student update(Student admin);

    void deleteById(UUID id);

}
