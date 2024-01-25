package com.minin.coreservice.mappers;

import com.minin.coreservice.models.Mark;
import com.minin.coreservice.models.Schedule;
import com.minin.coreservice.models.Student;
import com.minin.coreservice.models.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MarkMapper {

    @Mapping(source = "teacher", target = "teacher")
    @Mapping(source = "student", target = "student")
    @Mapping(source = "schedule", target = "schedule")
    @Mapping(source = "mark", target = "mark")
    @Mapping(target = "id", ignore = true)
    Mark requestToEntity(String mark, Teacher teacher, Student student, Schedule schedule);

}
