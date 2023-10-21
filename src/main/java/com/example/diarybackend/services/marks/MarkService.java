package com.example.diarybackend.services.marks;

import com.example.diarybackend.controllers.marks.requests.MarkCreateRequest;
import com.example.diarybackend.dtos.StudentDto;
import com.example.diarybackend.dtos.SubjectDto;
import com.example.diarybackend.dtos.marks.AvgMark;
import com.example.diarybackend.dtos.marks.AvgMarkBySubjectDto;
import com.example.diarybackend.dtos.marks.MarkDto;
import com.example.diarybackend.exceptions.BadRequestException;
import com.example.diarybackend.mappers.MarkMapper;
import com.example.diarybackend.mappers.StudentMapper;
import com.example.diarybackend.mappers.SubjectMapper;
import com.example.diarybackend.models.Mark;
import com.example.diarybackend.models.Student;
import com.example.diarybackend.models.Subject;
import com.example.diarybackend.models.Teacher;
import com.example.diarybackend.repositories.MarkRepository;
import com.example.diarybackend.services.student.IStudentService;
import com.example.diarybackend.services.subjects.ISubjectsService;
import com.example.diarybackend.services.teacher.ITeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MarkService implements IMarkService {

    private final MarkRepository markRepository;

    private final MarkMapper markMapper;
    private final StudentMapper studentMapper;
    private final SubjectMapper subjectMapper;

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

    @Override
    public AvgMarkBySubjectDto findAvgMarkByStudentIdAndSubjectId(UUID studentId, UUID subjectId) {

        Student student = studentService.findById(studentId);
        StudentDto studentDto = studentMapper.entityToDto(student);

        Subject subject = subjectsService.findById(subjectId);
        SubjectDto subjectDto = subjectMapper.entityToDto(subject);

        List<Mark> marks = markRepository.findMarksByStudentId(studentId);

        int counter = 0;
        double sum = 0;

        for (Mark mark : marks) {
            try {
                int num = Integer.parseInt(mark.getMark());
                sum += num;
                counter++;
            } catch (NumberFormatException ex) {
                log.error(ex.toString());
            }
        }

        if (counter == 0) {
            return new AvgMarkBySubjectDto(studentDto, subjectDto, 0);
        }

        double result = sum/counter;
        double roundedResult = 0;

        try {
            roundedResult = Double.parseDouble(String.format("%.2f", result)
                    .replace(',', '.'));
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("error_casting_to_the_required_type");
        }

        return new AvgMarkBySubjectDto(studentDto, subjectDto, roundedResult);
    }

    @Override
    public AvgMark findAvgMarkByStudentId(UUID studentId) {

        Student student = studentService.findById(studentId);
        StudentDto studentDto = studentMapper.entityToDto(student);

        List<Mark> marks = markRepository.findMarksByStudentId(studentId);

        int counter = 0;
        double sum = 0;

        for (Mark mark : marks) {
            try {
                int num = Integer.parseInt(mark.getMark());
                sum += num;
                counter++;
            } catch (NumberFormatException ex) {
                log.error(ex.toString());
            }
        }

        if (counter == 0) {
            return new AvgMark(studentDto, 0);
        }

        double result = sum/counter;
        double roundedResult = 0;

        try {
            roundedResult = Double.parseDouble(String.format("%.2f", result)
                    .replace(',', '.'));
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("error_casting_to_the_required_type");
        }

        return new AvgMark(studentDto, roundedResult);
    }

}
