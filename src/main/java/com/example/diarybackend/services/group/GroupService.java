package com.example.diarybackend.services.group;

import com.example.diarybackend.controllers.group.requests.GroupCreateRequest;
import com.example.diarybackend.exceptions.ResourceNotFoundException;
import com.example.diarybackend.models.Group;
import com.example.diarybackend.models.Student;
import com.example.diarybackend.repositories.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupService implements IGroupService {

    private final GroupRepository groupRepository;

    @Override
    public Group findById(UUID id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("group_with_id_'%s'_not_found", id)));
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

        Group group = findById(id);

        return group.getStudents();
    }

}
