package com.minin.coreservice.mappers;

import com.minin.dtos.SubjectDto;
import com.minin.dtos.groups.GroupDto;
import com.minin.dtos.students.StudentDto;
import com.minin.coreservice.models.Group;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    @Mapping(target = "students", source = "students")
    @Mapping(target = "subjects", source = "subjects")
    GroupDto entityToDto(Group group, List<StudentDto> students, List<SubjectDto> subjects);

}
