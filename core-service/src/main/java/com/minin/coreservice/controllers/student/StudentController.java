package com.minin.coreservice.controllers.student;

import com.minin.custom.CustomPrincipal;
import com.minin.dtos.students.StudentDto;
import com.minin.dtos.students.StudentProfile;
import com.minin.coreservice.mappers.StudentMapper;
import com.minin.coreservice.services.student.IStudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
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

    @GetMapping("/profile")
    public StudentProfile findStudentProfile(Authentication authentication) {
        CustomPrincipal principal = (CustomPrincipal) authentication.getPrincipal();
        return studentService.findProfileByIdentityId(principal.getIdentityId());
    }

    @GetMapping("{id}")
    public StudentDto findById(@PathVariable UUID id) {
        return studentMapper.entityToDto(studentService.findById(id));
    }

}
