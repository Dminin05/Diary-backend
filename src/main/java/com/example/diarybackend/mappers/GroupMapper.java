package com.example.diarybackend.mappers;

import com.example.diarybackend.dtos.GroupDto;
import com.example.diarybackend.dtos.StudentDto;
import com.example.diarybackend.dtos.SubjectDto;
import com.example.diarybackend.models.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    @Mapping(target = "students", source = "students")
    @Mapping(target = "subjects", source = "subjects")
    GroupDto entityToDto(Group group, List<StudentDto> students, List<SubjectDto> subjects);

}
