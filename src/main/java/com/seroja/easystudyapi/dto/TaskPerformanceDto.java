package com.seroja.easystudyapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.seroja.easystudyapi.entity.TaskPerformance}
 */
@Data
public class TaskPerformanceDto implements Serializable {
    Integer id;
    @NotNull
    EducationalMaterialDto edMaterial;
    @NotNull
    Integer studentId;
    @NotNull
    LocalDate dateOfCompletion;
    @NotNull
    String answer;
    @NotNull
    Integer grade;
}