package com.example.diarybackend.services.marks;

import com.example.diarybackend.controllers.marks.requests.MarkCreateRequest;
import com.example.diarybackend.dtos.MarkDto;
import com.example.diarybackend.exceptions.BadRequestException;
import com.example.diarybackend.mappers.MarkMapper;
import com.example.diarybackend.mappers.StudentMapper;
import com.example.diarybackend.mappers.SubjectMapper;
import com.example.diarybackend.mappers.TeacherMapper;
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
    private final StudentMapper studentMapper;
    private final SubjectMapper subjectMapper;
    private final TeacherMapper teacherMapper;

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

        return markMapper.entityToDto(
                teacherMapper.entityToDto(teacher),
                studentMapper.entityToDto(student),
                subjectMapper.entityToDto(subject),
                markCreateRequest.getMark()
        );
    }

    @Override
    public List<Mark> findAllMarksByStudentId(UUID studentId) {
        return markRepository.findMarksByStudentId(studentId);
    }

    @Override
    public List<Mark> findAllMarksByStudentIdAndSubjectId(UUID studentId, UUID subjectId) {
        return markRepository.findMarksByStudentIdAndSubjectId(studentId, subjectId);
    }

}
