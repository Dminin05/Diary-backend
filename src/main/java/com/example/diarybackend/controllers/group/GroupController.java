package com.example.diarybackend.controllers.group;

import com.example.diarybackend.controllers.group.requests.GroupCreateRequest;
import com.example.diarybackend.models.Group;
import com.example.diarybackend.models.Student;
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

    @PostMapping("new")
    public ResponseEntity<?> createNewGroup(@RequestBody GroupCreateRequest groupCreateRequest) {

        Group result = groupService.create(groupCreateRequest);

        return ResponseEntity.ok(result);
    }

    @GetMapping("{id}/students")
    public List<Student> findAllStudentsInGroup(@PathVariable UUID id) {
        return groupService.findAllStudentsInGroupById(id);
    }

}
