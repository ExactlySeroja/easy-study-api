package com.seroja.easystudyapi.dto.query;

import com.seroja.easystudyapi.dto.ApplicationDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class GetCoursesRequestDto implements Serializable {
    @NotNull
    Integer id;
    @NotNull
    @Size(max = 250)
    String courseName;
    @NotNull
    CategoryRequestDto category;
    @NotNull
    LocalDate courseStartDate;
    @NotNull
    LocalDate courseEndDate;
    @NotNull
    Integer coursePrice;
    @NotNull
    ProfileDto teacher;

    ApplicationDto application;
}
