package com.seroja.easystudyapi.mapper;


import com.seroja.easystudyapi.dto.TaskPerformanceDto;
import com.seroja.easystudyapi.entity.TaskPerformance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskPerformanceMapper {
    @Mapping(source = "edMaterial.id", target = "edMaterialId")
    @Mapping(source = "doneBy.id", target = "studentId")
    TaskPerformanceDto toDto(TaskPerformance taskPerformance);

    @Mapping(source = "edMaterialId", target = "edMaterial.id")
    @Mapping(source = "studentId", target = "doneBy.id")
    TaskPerformance toEntity(TaskPerformanceDto taskPerformanceDto);

    List<TaskPerformanceDto> toDtoList(List<TaskPerformance> taskPerformanceList);

    List<TaskPerformance> toEntity(List<TaskPerformanceDto> taskPerformanceDtoList);

    @Mapping(ignore = true, source = "id", target = "id")
    void update(@MappingTarget TaskPerformance entity, TaskPerformance updatedTaskPerformance);

}
