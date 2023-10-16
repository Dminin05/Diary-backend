package com.example.diarybackend.controllers.group;

import com.example.diarybackend.controllers.group.requests.GroupCreateRequest;
import com.example.diarybackend.dtos.GroupDto;
import com.example.diarybackend.dtos.StudentDto;
import com.example.diarybackend.models.Group;
import com.example.diarybackend.services.group.IGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/group")
public class GroupController {

    private final IGroupService groupService;

    @GetMapping
    public List<GroupDto> findAllGroups() {
        return groupService.findGroupsInfo();
    }

    @GetMapping("{groupId}")
    public GroupDto findGroupById(@PathVariable UUID groupId) {
        return groupService.findGroupInfoById(groupId);
    }

    @GetMapping("{groupId}/students")
    public List<StudentDto> findAllStudentsInGroup(@PathVariable UUID groupId) {
        return groupService.findAllStudentsInGroupById(groupId);
    }

    @PostMapping("new")
    public ResponseEntity<?> createNewGroup(@RequestBody GroupCreateRequest groupCreateRequest) {

        Group result = groupService.create(groupCreateRequest);

        return ResponseEntity.ok(result);
    }

    @PostMapping("{groupId}/{subjectId}")
    public void addSubjectToTheGroup(@PathVariable UUID groupId, @PathVariable UUID subjectId) {
        groupService.addSubjectToTheGroupById(groupId, subjectId);
    }

}
