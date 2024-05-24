package com.seroja.easystudyapi.mapper;

import com.seroja.easystudyapi.dto.ApplicationDto;
import com.seroja.easystudyapi.entity.Application;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {

    ApplicationDto toDto(Application application);

    Application toEntity(ApplicationDto applicationDto);

    List<ApplicationDto> toDtoList(List<Application> applications);

    List<Application> toEntityList(List<ApplicationDto> applicationDtos);

    @Mapping(ignore = true, source = "id", target = "id")
    void update(@MappingTarget Application entity, Application updatedApplication);

}
