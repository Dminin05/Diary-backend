package com.example.diarybackend.services.identity;

import com.example.diarybackend.controllers.identity.request.IdentityCreateRequest;
import com.example.diarybackend.models.Identity;
import com.example.diarybackend.models.Student;
import com.example.diarybackend.models.Teacher;
import com.example.diarybackend.models.types.IdentityType;
import com.example.diarybackend.repositories.IdentityRepository;
import com.example.diarybackend.services.credentials.ICredentialsService;
import com.example.diarybackend.services.student.IStudentService;
import com.example.diarybackend.services.teacher.ITeacherService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class IdentityService implements IIdentityService {

    private final IdentityRepository identityRepository;
    private final IStudentService studentService;
    private final ITeacherService teacherService;
    private final ICredentialsService credentialsService;

    @Override
    public List<Identity> findAll() {
        return identityRepository.findAll();
    }

    @Override
    public Optional<Identity> findById(UUID id) {
        return identityRepository.findById(id);
    }

    @Override
    @Transactional
    public Identity create(IdentityCreateRequest identityCreateRequest) {

        Identity identity = new Identity();

        switch (identityCreateRequest) {
            case IdentityCreateRequest.StudentCreateRequest obj -> {

                Student student = studentService.create(obj);

                identity.setStudentId(student.getId());
                identity.setType(IdentityType.STUDENT);
                identityRepository.save(identity);

                credentialsService.create(obj.username(), obj.password(), obj.email(), identity);

            }
            case IdentityCreateRequest.TeacherCreateRequest obj -> {

                Teacher teacher = teacherService.create(obj);

                identity.setTeacherId(teacher.getId());
                identity.setType(IdentityType.TEACHER);
                identityRepository.save(identity);

                credentialsService.create(obj.username(), obj.password(), obj.email(), identity);


            }
            default -> throw new IllegalStateException("Unexpected value: " + identityCreateRequest);
        }

        return identity;
    }

    @Override
    public Identity update(Identity identity) {
        return identityRepository.save(identity);
    }

    @Override
    public void deleteById(UUID id) {
        identityRepository.deleteById(id);
    }

}
