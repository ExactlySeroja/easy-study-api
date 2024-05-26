package com.seroja.easystudyapi.dto.query;

import com.seroja.easystudyapi.dto.TaskPerformanceDto;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EdMaterialAndTaskPerformanceQueryDto {
    @NotNull
    Integer id;
    @NotNull
    String name;
    @NotNull
    LocalDate dateOfUpload;
    TaskPerformanceDto taskPerformanceDto;
}
