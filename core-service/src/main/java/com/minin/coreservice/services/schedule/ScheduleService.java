package com.minin.coreservice.services.schedule;

import com.minin.coreservice.controllers.schedule.requests.ScheduleCreateRequest;
import com.minin.dtos.ScheduleDto;
import com.minin.exceptions.ConflictException;
import com.minin.exceptions.ResourceNotFoundException;
import com.minin.coreservice.mappers.ScheduleMapper;
import com.minin.coreservice.models.Group;
import com.minin.coreservice.models.Schedule;
import com.minin.coreservice.models.Subject;
import com.minin.coreservice.models.Teacher;
import com.minin.coreservice.repositories.ScheduleRepository;
import com.minin.coreservice.services.group.IGroupService;
import com.minin.coreservice.services.subjects.ISubjectsService;
import com.minin.coreservice.services.teacher.ITeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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
    public List<ScheduleDto> findAllSchedulesByDateAndGroupTitle(Date dateFrom, Date dateTo, String groupTitle) {

        return scheduleRepository.findAllByDateBetweenAndGroup_Title(dateFrom, dateTo, groupTitle)
                .stream()
                .map(scheduleMapper::entityToDto)
                .toList();
    }

    private boolean existsByTeacherIdAndPairAndDate(UUID teacherId, int pair, Date date) {
        return scheduleRepository.existsByPairAndDateAndTeacher_Id(pair, date, teacherId);
    }

}
