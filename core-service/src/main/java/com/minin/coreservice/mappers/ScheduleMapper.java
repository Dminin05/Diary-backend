package com.minin.coreservice.mappers;

import com.minin.dtos.ScheduleDto;
import com.minin.coreservice.models.Schedule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {

    @Mapping(source = "subject.title", target = "subject")
    ScheduleDto entityToDto(Schedule schedule);

}
