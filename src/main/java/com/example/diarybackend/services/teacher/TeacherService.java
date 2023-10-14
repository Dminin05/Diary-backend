package com.example.diarybackend.services.teacher;

import com.example.diarybackend.controllers.auth.requests.TeacherRegisterRequest;
import com.example.diarybackend.exceptions.AlreadyExistsException;
import com.example.diarybackend.exceptions.ResourceNotFoundException;
import com.example.diarybackend.models.Group;
import com.example.diarybackend.models.Subject;
import com.example.diarybackend.models.Teacher;
import com.example.diarybackend.repositories.TeacherRepository;
import com.example.diarybackend.services.group.IGroupService;
import com.example.diarybackend.services.subjects.ISubjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherService implements ITeacherService{

    private final TeacherRepository teacherRepository;
    private final IGroupService groupService;
    private final ISubjectsService subjectsService;

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher findById(UUID id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("teacher_with_id_'%s'_not_found", id)));
    }

    @Override
    public Teacher create(TeacherRegisterRequest createRequest) {

        Teacher teacher = new Teacher();

        teacher.setFirstName(createRequest.getFirstName());
        teacher.setLastName(createRequest.getLastName());
        teacher.setPatronymic(createRequest.getPatronymic());

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

    @Override
    public void addGroupToTeacher(UUID teacherId, UUID groupId) {

        Group group = groupService.findById(groupId);
        Teacher teacher = findById(teacherId);

        if (teacher.getGroups().contains(group)) {
            throw new AlreadyExistsException("group_is_already_linked");
        }

        teacher.getGroups().add(group);

        teacherRepository.save(teacher); // TODO method update in service
    }

    @Override
    public void addSubjectToTeacher(UUID teacherId, UUID subjectId) {

        Subject subject = subjectsService.findById(subjectId);
        Teacher teacher = findById(teacherId);

        if (teacher.getSubjects().contains(subject)) {
            throw new AlreadyExistsException("subject_is_already_linked");
        }

        teacher.getSubjects().add(subject);

        teacherRepository.save(teacher); // TODO method update in service
    }

}
