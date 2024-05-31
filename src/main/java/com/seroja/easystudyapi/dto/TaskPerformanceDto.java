package com.seroja.easystudyapi.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.seroja.easystudyapi.entity.TaskPerformance}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskPerformanceDto implements Serializable {
    Integer id;
    @NotNull
    Integer edMaterialId;
    @NotNull
    Integer studentId;
    @NotNull
    LocalDate dateOfCompletion;
    @NotNull
    String answer;
    @Max(100)
    @Min(0)
    Integer grade;
}