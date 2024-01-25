package com.minin.coreservice.services.student;

import com.minin.coreservice.controllers.auth.requests.registration.StudentRegisterRequest;
import com.minin.dtos.students.StudentDto;
import com.minin.dtos.students.StudentProfile;
import com.minin.exceptions.ResourceNotFoundException;
import com.minin.coreservice.mappers.StudentMapper;
import com.minin.coreservice.models.Credentials;
import com.minin.coreservice.models.Group;
import com.minin.coreservice.models.Student;
import com.minin.coreservice.repositories.StudentRepository;
import com.minin.coreservice.services.credentials.ICredentialsService;
import com.minin.coreservice.services.group.IGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService{

    private final StudentRepository studentRepository;
    private final IGroupService groupService;
    private final ICredentialsService credentialsService;

    private final StudentMapper studentMapper;

    @Override
    public List<StudentDto> findAll() {
        return studentRepository.findAll().stream()
                .map(studentMapper::entityToDto)
                .toList();
    }

    @Override
    public Student findById(UUID id) {

        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("student_not_found"));
    }

    @Override
    public StudentProfile findProfileByIdentityId(UUID identityId) {

        Credentials credentials = credentialsService.findByIdentityId(identityId);
        Student student = credentials.getIdentity().getStudent();

        return studentMapper.entityToStudentProfile(student, credentials.getEmail(), credentials.isEmailVerified());
    }

    @Override
    public Student create(StudentRegisterRequest createRequest) {

        Group group = groupService.findById(createRequest.getGroupId());

        Student student = new Student();

        student.setFirstName(createRequest.getFirstName());
        student.setLastName(createRequest.getLastName());
        student.setPatronymic(createRequest.getPatronymic());
        student.setGroup(group);

        return studentRepository.save(student);
    }

    @Override
    public void deleteById(UUID id) {
        studentRepository.deleteById(id);
    }

}
