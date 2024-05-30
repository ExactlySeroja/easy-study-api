package com.seroja.easystudyapi.dto.query;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ApplicationRequestDto {
    @NotNull
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
