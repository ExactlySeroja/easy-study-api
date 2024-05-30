package com.seroja.easystudyapi.mapper;


import com.seroja.easystudyapi.dto.CourseDto;
import com.seroja.easystudyapi.dto.query.GetCoursesRequestDto;
import com.seroja.easystudyapi.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(source = "category.categoryId", target = "categoryId")
    @Mapping(source = "teacher.id", target = "teacherId")
    CourseDto toDto(Course course);

    @Mapping(source = "categoryId", target = "category.categoryId")
    @Mapping(source = "teacherId", target = "teacher.id")
    Course toEntity(CourseDto courseDto);

    GetCoursesRequestDto toGetCoursesRequestDto(Course course);

    List<GetCoursesRequestDto> toGetCoursesRequestDtoList(List<Course> courses);

    List<CourseDto> toDtoList(List<Course> courseList);

    List<Course> toEntityList(List<CourseDto> courseDtoList);

    @Mapping(ignore = true, source = "id", target = "id")
    void update(@MappingTarget Course entity, Course updatedCertificate);
}
