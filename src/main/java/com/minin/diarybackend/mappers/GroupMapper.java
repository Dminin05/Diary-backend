package com.minin.diarybackend.mappers;

import com.minin.diarybackend.dtos.GroupDto;
import com.minin.diarybackend.dtos.StudentDto;
import com.minin.diarybackend.dtos.SubjectDto;
import com.minin.diarybackend.models.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    @Mapping(target = "students", source = "students")
    @Mapping(target = "subjects", source = "subjects")
    GroupDto entityToDto(Group group, List<StudentDto> students, List<SubjectDto> subjects);

}
