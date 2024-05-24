package com.seroja.easystudyapi.mapper;


import com.seroja.easystudyapi.dto.CourseDto;
import com.seroja.easystudyapi.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDto toDto(Course course);

    Course toEntity(CourseDto courseDto);

    List<CourseDto> toDtoList(List<Course> courseList);

    List<Course> toEntityList(List<CourseDto> courseDtoList);

    @Mapping(ignore = true, source = "id", target = "id")
    void update(@MappingTarget Course entity, Course updatedCertificate);
}
