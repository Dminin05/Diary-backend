package com.minin.diarybackend.services.marks;

import com.minin.diarybackend.controllers.marks.requests.MarkCreateRequest;
import com.minin.diarybackend.controllers.marks.requests.MarkUpdateRequest;
import com.minin.diarybackend.dtos.StudentDto;
import com.minin.diarybackend.dtos.SubjectDto;
import com.minin.diarybackend.dtos.marks.AvgMark;
import com.minin.diarybackend.dtos.marks.AvgMarkBySubjectDto;
import com.minin.diarybackend.dtos.marks.MarkDto;
import com.minin.diarybackend.exceptions.BadRequestException;
import com.minin.diarybackend.exceptions.ResourceNotFoundException;
import com.minin.diarybackend.mappers.MarkMapper;
import com.minin.diarybackend.mappers.StudentMapper;
import com.minin.diarybackend.mappers.SubjectMapper;
import com.minin.diarybackend.models.Mark;
import com.minin.diarybackend.models.Student;
import com.minin.diarybackend.models.Subject;
import com.minin.diarybackend.models.Teacher;
import com.minin.diarybackend.repositories.MarkRepository;
import com.minin.diarybackend.services.student.IStudentService;
import com.minin.diarybackend.services.subjects.ISubjectsService;
import com.minin.diarybackend.services.teacher.ITeacherService;
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
    public Mark findById(UUID id) {
        return markRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("mark_not_found"));
    }

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
    public void update(UUID markId, MarkUpdateRequest markUpdateRequest) {

        Mark mark = findById(markId);
        mark.setMark(markUpdateRequest.getMark());

        markRepository.save(mark);
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
