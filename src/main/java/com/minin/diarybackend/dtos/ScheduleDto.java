package com.minin.diarybackend.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.minin.diarybackend.dtos.groups.GroupBaseInfo;
import com.minin.diarybackend.models.Schedule;
import lombok.Data;

import java.util.Date;

@Data
public class ScheduleDto {
    private TeacherDto teacherDto;
    private GroupBaseInfo groupBaseInfo;
    private SubjectDto subjectDto;
    private int pair;
    private Schedule.Status status;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Moscow")
    private Date date;
}
