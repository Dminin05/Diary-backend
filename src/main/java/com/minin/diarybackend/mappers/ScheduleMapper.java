package com.minin.diarybackend.mappers;

import com.minin.diarybackend.dtos.ScheduleDto;
import com.minin.diarybackend.dtos.SubjectDto;
import com.minin.diarybackend.dtos.TeacherDto;
import com.minin.diarybackend.dtos.groups.GroupBaseInfo;
import com.minin.diarybackend.models.Group;
import com.minin.diarybackend.models.Schedule;
import com.minin.diarybackend.models.Subject;
import com.minin.diarybackend.models.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    @Mapping(source = "subject.title", target = "subject")
    ScheduleDto entityToDto(Schedule schedule);

}
