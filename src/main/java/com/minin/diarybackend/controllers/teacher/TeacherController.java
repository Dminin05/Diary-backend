package com.minin.diarybackend.controllers.teacher;

import com.minin.diarybackend.dtos.TeacherDto;
import com.minin.diarybackend.services.teacher.ITeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/teachers")
public class TeacherController {

    private final ITeacherService teacherService;

    @GetMapping
    public List<TeacherDto> findAll() {
        return teacherService.findAll();
    }

    @GetMapping("{id}")
    public TeacherDto findById(@PathVariable UUID id) {
        return teacherService.findBaseInfoById(id);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable UUID id) {
        teacherService.deleteById(id);
    }

    @PostMapping("{teacherId}/group/{groupId}")
    public void addGroupToTeacher(@PathVariable UUID teacherId, @PathVariable UUID groupId) {
        teacherService.addGroupToTeacher(teacherId, groupId);
    }

    @PostMapping("{teacherId}/subject/{subjectId}")
    public void addSubjectToTeacher(@PathVariable UUID teacherId, @PathVariable UUID subjectId) {
        teacherService.addSubjectToTeacher(teacherId, subjectId);
    }

    @PostMapping("{identityId}/methodist")
    public void addRoleMethodist(@PathVariable UUID identityId) {
        teacherService.addRoleMethodist(identityId);
    }

}
