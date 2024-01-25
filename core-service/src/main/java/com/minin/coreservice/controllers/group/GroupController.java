package com.minin.coreservice.controllers.group;

import com.minin.coreservice.controllers.group.requests.GroupCreateRequest;
import com.minin.dtos.groups.GroupDto;
import com.minin.dtos.students.StudentDto;
import com.minin.dtos.students.StudentDtoWithScore;
import com.minin.coreservice.models.Group;
import com.minin.coreservice.services.group.IGroupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Groups")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/groups")
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

    @GetMapping("{groupId}/students/rating")
    public List<StudentDtoWithScore> findAllStudentsInGroupByRating(@PathVariable UUID groupId) {
        return groupService.findStudentsByRating(groupId);
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

    @DeleteMapping("{groupId}/{subjectId}")
    public void removeSubjectFromGroup(@PathVariable UUID groupId, @PathVariable UUID subjectId) {
        groupService.removeSubjectFromGroupById(groupId, subjectId);
    }

}
