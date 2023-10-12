package com.example.diarybackend.services.group;

import com.example.diarybackend.controllers.group.requests.GroupCreateRequest;
import com.example.diarybackend.models.Group;
import com.example.diarybackend.models.Student;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IGroupService {

    Optional<Group> findById(UUID id);

    Group create(GroupCreateRequest groupCreateRequest);

    List<Student> findAllStudentsInGroupById(UUID id);

}
