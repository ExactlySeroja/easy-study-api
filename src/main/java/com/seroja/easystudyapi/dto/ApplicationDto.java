package com.seroja.easystudyapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

/**
 * DTO for {@link com.seroja.easystudyapi.entity.Application}
 */
@Data
public class ApplicationDto {
    Integer id;
    @NotNull
    Integer studentId;
    @NotNull
    Integer courseId;
    @NotNull
    LocalDate dateOfCreation;
    @NotNull
    Boolean applicationStatus;
}