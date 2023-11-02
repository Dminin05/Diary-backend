package com.minin.diarybackend.services.group;

import com.minin.diarybackend.controllers.group.requests.GroupCreateRequest;
import com.minin.diarybackend.dtos.groups.GroupDto;
import com.minin.diarybackend.dtos.StudentDto;
import com.minin.diarybackend.models.Group;

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

}
