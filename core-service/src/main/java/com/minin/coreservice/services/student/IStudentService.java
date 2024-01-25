package com.minin.coreservice.services.student;

import com.minin.coreservice.controllers.auth.requests.registration.StudentRegisterRequest;
import com.minin.dtos.students.StudentDto;
import com.minin.dtos.students.StudentProfile;
import com.minin.coreservice.models.Student;

import java.util.List;
import java.util.UUID;

public interface IStudentService {

    List<StudentDto> findAll();

    Student findById(UUID id);

    StudentProfile findProfileByIdentityId(UUID identityId);

    Student create(StudentRegisterRequest createRequest);

    void deleteById(UUID id);

}
