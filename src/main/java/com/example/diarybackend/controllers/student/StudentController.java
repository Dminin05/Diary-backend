package com.example.diarybackend.controllers.student;

import com.example.diarybackend.models.Student;
import com.example.diarybackend.services.student.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/student")
public class StudentController {

    private final IStudentService studentService;

    @GetMapping
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping("{id}")
    public Student findById(@PathVariable UUID id) {
        return studentService.findById(id).get();
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable UUID id) {
        studentService.deleteById(id);
    }

}