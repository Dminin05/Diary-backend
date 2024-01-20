package com.minin.diarybackend.mappers;

import com.minin.diarybackend.models.*;
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
