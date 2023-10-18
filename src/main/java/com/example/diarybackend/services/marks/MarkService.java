package com.example.diarybackend.services.marks;

import com.example.diarybackend.controllers.marks.requests.MarkCreateRequest;
import com.example.diarybackend.dtos.MarkDto;
import com.example.diarybackend.exceptions.BadRequestException;
import com.example.diarybackend.mappers.MarkMapper;
import com.example.diarybackend.models.Mark;
import com.example.diarybackend.models.Student;
import com.example.diarybackend.models.Subject;
import com.example.diarybackend.models.Teacher;
import com.example.diarybackend.repositories.MarkRepository;
import com.example.diarybackend.services.student.IStudentService;
import com.example.diarybackend.services.subjects.ISubjectsService;
import com.example.diarybackend.services.teacher.ITeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MarkService implements IMarkService {

    private final MarkRepository markRepository;
    private final MarkMapper markMapper;

    private final ITeacherService teacherService;
    private final IStudentService studentService;
    private final ISubjectsService subjectsService;

    @Override
    public MarkDto create(MarkCreateRequest markCreateRequest) {

        Teacher teacher = teacherService.findById(markCreateRequest.getTeacherId());
        Student student = studentService.findById(markCreateRequest.getStudentId());
        Subject subject = subjectsService.findById(markCreateRequest.getSubjectId());

        if (!student.getGroup().getSubjects().contains(subject)) {
            throw new BadRequestException("student_does_not_has_this_subject");
        }

        Mark mark = markMapper.requestToEntity(markCreateRequest.getMark(), teacher, student, subject);
        markRepository.save(mark);

        return markMapper.entityToDto(mark);
    }

    @Override
    public List<MarkDto> findAllMarksByStudentId(UUID studentId) {

        List<Mark> marks = markRepository.findMarksByStudentId(studentId);

        return marks.stream()
                .map(markMapper::entityToDto)
                .toList();
    }

    @Override
    public List<MarkDto> findAllMarksByStudentIdAndSubjectId(UUID studentId, UUID subjectId) {

        List<Mark> marks =  markRepository.findMarksByStudentIdAndSubjectId(studentId, subjectId);

        return marks.stream()
                .map(markMapper::entityToDto)
                .toList();
    }

}
