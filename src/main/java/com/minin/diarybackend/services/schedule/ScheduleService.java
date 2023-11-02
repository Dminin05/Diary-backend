package com.minin.diarybackend.services.schedule;

import com.minin.diarybackend.controllers.schedule.requests.ScheduleCreateRequest;
import com.minin.diarybackend.dtos.ScheduleDto;
import com.minin.diarybackend.exceptions.ConflictException;
import com.minin.diarybackend.exceptions.ResourceNotFoundException;
import com.minin.diarybackend.mappers.ScheduleMapper;
import com.minin.diarybackend.models.Group;
import com.minin.diarybackend.models.Schedule;
import com.minin.diarybackend.models.Subject;
import com.minin.diarybackend.models.Teacher;
import com.minin.diarybackend.repositories.ScheduleRepository;
import com.minin.diarybackend.services.group.IGroupService;
import com.minin.diarybackend.services.subjects.ISubjectsService;
import com.minin.diarybackend.services.teacher.ITeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ScheduleService implements IScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    private final ITeacherService teacherService;
    private final IGroupService groupService;
    private final ISubjectsService subjectsService;

    @Override
    public Schedule findById(UUID id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("schedule_not_found"));
    }

    @Override
    public void create(ScheduleCreateRequest scheduleCreateRequest) {

        UUID teacherId = scheduleCreateRequest.getTeacherId();
        int pair = scheduleCreateRequest.getPair();
        Date date = scheduleCreateRequest.getDate();

        if (existsByTeacherIdAndPairAndDate(teacherId, pair, date)) {
            throw new ConflictException("teacher_already_has_a_pair");
        }

        Teacher teacher = teacherService.findById(teacherId);
        Group group = groupService.findById(scheduleCreateRequest.getGroupId());
        Subject subject = subjectsService.findById(scheduleCreateRequest.getSubjectId());

        Schedule schedule = new Schedule();
        schedule.setTeacher(teacher);
        schedule.setGroup(group);
        schedule.setSubject(subject);
        schedule.setStatus(Schedule.Status.PLANNED);
        schedule.setPair(pair);
        schedule.setDate(date);

        scheduleRepository.save(schedule);
    }

    @Override
    public ScheduleDto getBaseInfoById(UUID id) {

        Schedule schedule = findById(id);

        return scheduleMapper.entityToDto(schedule);
    }

    private boolean existsByTeacherIdAndPairAndDate(UUID teacherId, int pair, Date date) {
        return scheduleRepository.existsByPairAndDateAndTeacher_Id(pair, date, teacherId);
    }

}
