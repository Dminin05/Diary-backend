package com.minin.diarybackend.services.marks;

import com.minin.diarybackend.controllers.marks.requests.MarkCreateRequest;
import com.minin.diarybackend.controllers.marks.requests.MarkUpdateRequest;
import com.minin.diarybackend.dtos.marks.AvgMark;
import com.minin.diarybackend.dtos.marks.MarkBaseInfo;
import com.minin.diarybackend.exceptions.BadRequestException;
import com.minin.diarybackend.exceptions.ResourceNotFoundException;
import com.minin.diarybackend.mappers.MarkMapper;
import com.minin.diarybackend.models.*;
import com.minin.diarybackend.repositories.MarkRepository;
import com.minin.diarybackend.services.identity.IIdentityService;
import com.minin.diarybackend.services.schedule.IScheduleService;
import com.minin.diarybackend.services.student.IStudentService;
import com.minin.diarybackend.services.subjects.ISubjectsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MarkService implements IMarkService {

    private final MarkRepository markRepository;

    private final MarkMapper markMapper;

    private final IStudentService studentService;
    private final ISubjectsService subjectsService;
    private final IIdentityService identityService;
    private final IScheduleService scheduleService;

    @Override
    public Mark findById(UUID id) {
        return markRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("mark_not_found"));
    }

    @Override
    public void create(MarkCreateRequest markCreateRequest) {

        Teacher teacher = identityService.findById(markCreateRequest.getIdentityId()).getTeacher();
        Student student = studentService.findById(markCreateRequest.getStudentId());
        Subject subject = subjectsService.findById(markCreateRequest.getSubjectId());
        Schedule schedule = scheduleService.findById(markCreateRequest.getScheduleId());

        if (!schedule.getSubject().getTitle().equals(subject.getTitle())) {
            throw new BadRequestException("mismatch_subjects_in_schedule");
        }

        if (!student.getGroup().getSubjects().contains(subject)) {
            throw new BadRequestException("student_does_not_has_this_subject");
        }

        Mark mark = markMapper.requestToEntity(markCreateRequest.getMark(), teacher, student, subject, schedule);
        markRepository.save(mark);
    }

    @Override
    public void update(UUID markId, MarkUpdateRequest markUpdateRequest) {

        Mark mark = findById(markId);
        mark.setMark(markUpdateRequest.getMark());

        markRepository.save(mark);
    }

    @Override
    public Map<String, List<MarkBaseInfo>> findAllMarksByStudentId(UUID studentId) {

        List<Mark> marks = markRepository.findMarksByStudentId(studentId);
        Map<String, List<MarkBaseInfo>> map = new HashMap<>();

        for (Mark mark : marks) {

            Schedule schedule = mark.getSchedule();
            String subjectTitle = schedule.getSubject().getTitle();
            MarkBaseInfo markBaseInfo = new MarkBaseInfo(schedule.getDate(), mark.getMark());

            if (!map.containsKey(subjectTitle)) {
                map.put(subjectTitle, new ArrayList<>());
            }

            map.get(subjectTitle).add(markBaseInfo);
        }

        return map;
    }

    @Override
    public AvgMark findAvgMarkByStudentIdAndSubjectId(UUID studentId, UUID subjectId) {

        List<Mark> marks = markRepository.findMarksByStudentIdAndSubjectId(studentId, subjectId);

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
            return new AvgMark(0);
        }

        double result = sum/counter;
        double roundedResult = 0;

        try {
            roundedResult = Double.parseDouble(String.format("%.2f", result)
                    .replace(',', '.'));
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("error_casting_to_the_required_type");
        }

        return new AvgMark(roundedResult);
    }

    @Override
    public AvgMark findAvgMarkByStudentId(UUID studentId) {

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
            return new AvgMark(0);
        }

        double result = sum/counter;
        double roundedResult = 0;

        try {
            roundedResult = Double.parseDouble(String.format("%.2f", result)
                    .replace(',', '.'));
        } catch (NumberFormatException ex) {
            throw new NumberFormatException("error_casting_to_the_required_type");
        }

        return new AvgMark(roundedResult);
    }

}
