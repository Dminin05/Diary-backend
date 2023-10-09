package com.example.diarybackend.controllers.teacher;

import com.example.diarybackend.models.Teacher;
import com.example.diarybackend.services.teacher.ITeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/teacher")
public class TeacherController {

    private final ITeacherService teacherService;

    @GetMapping
    public List<Teacher> findAll() {
        return teacherService.findAll();
    }

    @GetMapping("{id}")
    public Teacher findById(@PathVariable UUID id) {
        return teacherService.findById(id).get();
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable UUID id) {
        teacherService.deleteById(id);
    }

}