package com.example.diarybackend.services.student;

import com.example.diarybackend.controllers.auth.requests.IdentityRegisterRequest;
import com.example.diarybackend.models.Student;
import com.example.diarybackend.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService{

    private final StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findById(UUID id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student create(IdentityRegisterRequest.StudentCreateRequest createRequest) {

        Student student = new Student();

        student.setFirstName(createRequest.firstName());
        student.setLastName(createRequest.lastName());
        student.setPatronymic(createRequest.patronymic());

        return studentRepository.save(student);
    }

    @Override
    public Student update(Student admin) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {
        studentRepository.deleteById(id);
    }

}
