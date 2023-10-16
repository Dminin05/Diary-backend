package com.example.diarybackend.services.group;

import com.example.diarybackend.controllers.group.requests.GroupCreateRequest;
import com.example.diarybackend.dtos.StudentDto;
import com.example.diarybackend.models.Group;

import java.util.List;
import java.util.UUID;

public interface IGroupService {

    Group findById(UUID id);

    Group create(GroupCreateRequest groupCreateRequest);

    List<StudentDto> findAllStudentsInGroupById(UUID id);

}
