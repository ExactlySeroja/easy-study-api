package com.seroja.easystudyapi.mapper;

import com.seroja.easystudyapi.dto.ApplicationDto;
import com.seroja.easystudyapi.dto.query.ApplicationWithFullInfoDto;
import com.seroja.easystudyapi.entity.Application;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "course.id", target = "courseId")
    ApplicationDto toDto(Application application);

    @Mapping(source = "studentId", target = "student.id")
    @Mapping(source = "courseId", target = "course.id")
    Application toEntity(ApplicationDto applicationDto);

    ApplicationWithFullInfoDto toWithFullInfoDto(Application application);

    List<ApplicationWithFullInfoDto> toWithFullInfoDtoList(List<Application> applications);

    List<ApplicationDto> toDtoList(List<Application> applications);

    List<Application> toEntityList(List<ApplicationDto> applicationDtos);

    @Mapping(ignore = true, source = "id", target = "id")
    void update(@MappingTarget Application entity, Application updatedApplication);

}
