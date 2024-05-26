package com.seroja.easystudyapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.seroja.easystudyapi.entity.Certificate}
 */
@Data
public class CourseDto implements Serializable {
    @NotNull
    Integer id;
    @NotNull
    @Size(max = 250)
    String courseName;
    @NotNull
    Integer categoryId;
    @NotNull
    LocalDate courseStartDate;
    @NotNull
    LocalDate courseEndDate;
    @NotNull
    Integer coursePrice;
    @NotNull
    Integer teacherId;
}