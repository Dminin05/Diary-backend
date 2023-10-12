package com.example.diarybackend.services.group;

import com.example.diarybackend.controllers.group.requests.GroupCreateRequest;
import com.example.diarybackend.models.Group;
import com.example.diarybackend.models.Student;
import com.example.diarybackend.repositories.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupService implements IGroupService {

    private final GroupRepository groupRepository;

    @Override
    public Optional<Group> findById(UUID id) {
        return groupRepository.findById(id);
    }

    @Override
    public Group create(GroupCreateRequest groupCreateRequest) {

        Group group = new Group();

        group.setTitle(groupCreateRequest.getTitle());
        group.setYear(groupCreateRequest.getYear());
        group.setStudents(new ArrayList<>());

        return groupRepository.save(group);
    }

    @Override
    public List<Student> findAllStudentsInGroupById(UUID id) {
        Group group = findById(id)
                .orElseThrow(); // TODO exception handler

        return group.getStudents();
    }
}
