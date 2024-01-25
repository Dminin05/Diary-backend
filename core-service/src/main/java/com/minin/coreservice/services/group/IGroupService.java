package com.minin.coreservice.services.group;

import com.minin.coreservice.controllers.group.requests.GroupCreateRequest;
import com.minin.dtos.groups.GroupDto;
import com.minin.dtos.students.StudentDto;
import com.minin.dtos.students.StudentDtoWithScore;
import com.minin.coreservice.models.Group;

import java.util.List;
import java.util.UUID;

public interface IGroupService {

    Group findById(UUID id);

    Group create(GroupCreateRequest groupCreateRequest);

    List<StudentDto> findAllStudentsInGroupById(UUID id);

    GroupDto findGroupInfoById(UUID id);

    List<GroupDto> findGroupsInfo();

    void addSubjectToTheGroupById(UUID groupId, UUID subjectId);

    void removeSubjectFromGroupById(UUID groupId, UUID subjectId);

    List<StudentDtoWithScore> findStudentsByRating(UUID groupOd);

}
