package com.minin.diarybackend.services.group;

import com.minin.diarybackend.controllers.group.requests.GroupCreateRequest;
import com.minin.diarybackend.dtos.GroupDto;
import com.minin.diarybackend.dtos.StudentDto;
import com.minin.diarybackend.dtos.SubjectDto;
import com.minin.diarybackend.exceptions.BadRequestException;
import com.minin.diarybackend.exceptions.ResourceNotFoundException;
import com.minin.diarybackend.mappers.GroupMapper;
import com.minin.diarybackend.mappers.StudentMapper;
import com.minin.diarybackend.mappers.SubjectMapper;
import com.minin.diarybackend.models.Group;
import com.minin.diarybackend.models.Subject;
import com.minin.diarybackend.repositories.GroupRepository;
import com.minin.diarybackend.services.subjects.ISubjectsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupService implements IGroupService {

    private final GroupRepository groupRepository;
    private final ISubjectsService subjectsService;

    private final StudentMapper studentMapper;
    private final GroupMapper groupMapper;
    private final SubjectMapper subjectMapper;

    @Override
    public Group findById(UUID id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("group_not_found"));
    }

    @Override
    public Group create(GroupCreateRequest groupCreateRequest) {

        Group group = new Group();

        group.setTitle(groupCreateRequest.getTitle());
        group.setYear(groupCreateRequest.getYear());
        group.setStudents(new ArrayList<>());
        group.setSubjects(new ArrayList<>());

        return groupRepository.save(group);
    }

    @Override
    public List<StudentDto> findAllStudentsInGroupById(UUID id) {

        Group group = findById(id);

        return group.getStudents().stream()
                .map(studentMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public GroupDto findGroupInfoById(UUID id) {

        Group group = findById(id);

        List<StudentDto> students = group.getStudents().stream()
                .map(studentMapper::entityToDto)
                .toList();

        List<SubjectDto> subjects = group.getSubjects().stream()
                .map(subjectMapper::entityToDto)
                .toList();

        return groupMapper.entityToDto(group, students, subjects);
    }

    @Override
    public List<GroupDto> findGroupsInfo() {

        List<Group> groups = groupRepository.findAll();

        return groups.stream()
                .map(this::findGroupInfo)
                .toList();
    }

    @Override
    public void addSubjectToTheGroupById(UUID groupId, UUID subjectId) {

        Group group = findById(groupId);
        Subject subject = subjectsService.findById(subjectId);

        if (group.getSubjects().contains(subject)) {
           throw new BadRequestException("subject_already_linked_to_this_group");
        }

        group.getSubjects().add(subject);
        groupRepository.save(group);
    }

    private GroupDto findGroupInfo(Group group) {

        List<StudentDto> students = group.getStudents().stream()
                .map(studentMapper::entityToDto)
                .toList();

        List<SubjectDto> subjects = group.getSubjects().stream()
                .map(subjectMapper::entityToDto)
                .toList();

        return groupMapper.entityToDto(group, students, subjects);
    }

    @Override
    public void removeSubjectFromGroupById(UUID groupId, UUID subjectId) {

        Group group = findById(groupId);
        Subject subject = subjectsService.findById(subjectId);

        group.getSubjects().remove(subject);

        groupRepository.save(group);
    }

}
