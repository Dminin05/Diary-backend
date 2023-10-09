package com.example.diarybackend.services.teacher;

import com.example.diarybackend.controllers.auth.requests.IdentityRegisterRequest;
import com.example.diarybackend.models.Teacher;
import com.example.diarybackend.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherService implements ITeacherService{

    private final TeacherRepository teacherRepository;

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> findById(UUID id) {
        return teacherRepository.findById(id);
    }

    @Override
    public Teacher create(IdentityRegisterRequest.TeacherCreateRequest createRequest) {

        Teacher teacher = new Teacher();

        teacher.setFirstName(createRequest.firstName());
        teacher.setLastName(createRequest.lastName());
        teacher.setPatronymic(createRequest.patronymic());

        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher update(Teacher admin) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {
        teacherRepository.deleteById(id);
    }

}
