package com.minin.diarybackend.controllers.student;

import com.minin.diarybackend.dtos.StudentDto;
import com.minin.diarybackend.mappers.StudentMapper;
import com.minin.diarybackend.models.Student;
import com.minin.diarybackend.services.student.IStudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Students")
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/students")
public class StudentController {

    private final IStudentService studentService;
    private final StudentMapper studentMapper;

    @GetMapping
    public List<StudentDto> findAll() {
        return studentService.findAll();
    }

    @GetMapping("{id}")
    public StudentDto findById(@PathVariable UUID id) {
        return studentMapper.entityToDto(studentService.findById(id));
    }

}
