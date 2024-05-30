package com.seroja.easystudyapi.dto.query;

import com.seroja.easystudyapi.dto.CourseDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ApplicationWithFullInfoDto {
    @NotNull
    Integer id;
    @NotNull
    ProfileDto student;
    @NotNull
    CourseDto course;
    @NotNull
    LocalDate dateOfCreation;
    @NotNull
    Boolean applicationStatus;

}
