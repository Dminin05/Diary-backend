package com.minin.diarybackend.services.student;

import com.minin.diarybackend.controllers.auth.requests.StudentRegisterRequest;
import com.minin.diarybackend.exceptions.ResourceNotFoundException;
import com.minin.diarybackend.models.Group;
import com.minin.diarybackend.models.Student;
import com.minin.diarybackend.repositories.StudentRepository;
import com.minin.diarybackend.services.group.IGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService{

    private final StudentRepository studentRepository;
    private final IGroupService groupService;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(UUID id) {

        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("student_not_found"));
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
    public Student update(Student student) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {
        studentRepository.deleteById(id);
    }

}
