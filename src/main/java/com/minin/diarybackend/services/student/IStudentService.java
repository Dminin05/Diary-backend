package com.minin.diarybackend.services.student;

import com.minin.diarybackend.controllers.auth.requests.registration.StudentRegisterRequest;
import com.minin.diarybackend.dtos.students.StudentDto;
import com.minin.diarybackend.models.Student;

import java.util.List;
import java.util.UUID;

public interface IStudentService {

    List<StudentDto> findAll();

    Student findById(UUID id);

    Student create(StudentRegisterRequest createRequest);

    void deleteById(UUID id);

}
