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

    @Mapping(source = "teacher", target = "teacherDto", qualifiedByName = "teacherDto")
    @Mapping(source = "subject", target = "subjectDto", qualifiedByName = "subjectDto")
    @Mapping(source = "group", target = "groupBaseInfo", qualifiedByName = "groupBaseInfo")
    ScheduleDto entityToDto(Schedule schedule);

    @Named("teacherDto")
    default TeacherDto teacherToTeacherDto(Teacher teacher) {
        return Mappers.getMapper(TeacherMapper.class).entityToDto(teacher);
    }

    @Named("subjectDto")
    default SubjectDto subjectToSubjectDto(Subject subject) {
        return Mappers.getMapper(SubjectMapper.class).entityToDto(subject);
    }

    @Named("groupBaseInfo")
    default GroupBaseInfo groupToGroupBaseInfo(Group group) {
        return Mappers.getMapper(GroupMapper.class).entityToBaseInfo(group);
    }

}
