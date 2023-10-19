package com.example.diarybackend.controllers.student;

import com.example.diarybackend.dtos.StudentDto;
import com.example.diarybackend.mappers.StudentMapper;
import com.example.diarybackend.models.Student;
import com.example.diarybackend.services.student.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/students")
public class StudentController {

    private final IStudentService studentService;
    private final StudentMapper studentMapper;

    @GetMapping
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping("{id}")
    public StudentDto findById(@PathVariable UUID id) {
        return studentMapper.entityToDto(studentService.findById(id));
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable UUID id) {
        studentService.deleteById(id);
    }

}
