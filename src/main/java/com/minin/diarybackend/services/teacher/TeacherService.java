package com.minin.diarybackend.services.teacher;

import com.minin.diarybackend.controllers.auth.requests.registration.TeacherRegisterRequest;
import com.minin.diarybackend.dtos.TeacherDto;
import com.minin.diarybackend.exceptions.AlreadyExistsException;
import com.minin.diarybackend.exceptions.BadRequestException;
import com.minin.diarybackend.exceptions.ResourceNotFoundException;
import com.minin.diarybackend.mappers.TeacherMapper;
import com.minin.diarybackend.models.*;
import com.minin.diarybackend.models.types.IdentityType;
import com.minin.diarybackend.repositories.TeacherRepository;
import com.minin.diarybackend.services.credentials.ICredentialsService;
import com.minin.diarybackend.services.group.IGroupService;
import com.minin.diarybackend.services.identity.IIdentityService;
import com.minin.diarybackend.services.role.IRoleService;
import com.minin.diarybackend.services.subjects.ISubjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherService implements ITeacherService{

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    private final IGroupService groupService;
    private final ISubjectsService subjectsService;

    private final ICredentialsService credentialsService;
    private final IIdentityService identityService;
    private final IRoleService roleService;

    @Override
    public List<TeacherDto> findAll() {
        return teacherRepository.findAll().stream()
                .map(teacherMapper::entityToDto)
                .toList();
    }

    @Override
    public Teacher findById(UUID id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("teacher_not_found"));
    }

    @Override
    public TeacherDto findBaseInfoById(UUID id) {
        return teacherMapper.entityToDto(findById(id));
    }

    @Override
    public Teacher findByIdentityId(UUID identityId) {
        return identityService.findById(identityId)
                .getTeacher();
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

        update(teacher);
    }

    @Override
    public void addSubjectToTeacher(UUID teacherId, UUID subjectId) {

        Subject subject = subjectsService.findById(subjectId);
        Teacher teacher = findById(teacherId);

        if (teacher.getSubjects().contains(subject)) {
            throw new AlreadyExistsException("subject_is_already_linked");
        }

        teacher.getSubjects().add(subject);

        update(teacher);
    }

    @Override
    public void addRoleMethodist(UUID identityId) {

        Identity identity = identityService.findById(identityId);

        if (!identity.getType().equals(IdentityType.TEACHER)) {
            throw new BadRequestException("identity_is_not_a_teacher");
        }

        Credentials credentials = credentialsService.findByIdentityId(identityId);
        Role role = roleService.findRoleByName("ROLE_METHODIST");

        if (credentials.getRoles().contains(role)) {
            throw new BadRequestException("teacher_already_has_this_role");
        }

        credentials.getRoles().add(role);
    }

    private void update(Teacher teacher) {
        teacherRepository.save(teacher);
    }

}
