package com.seroja.easystudyapi.dto.query;

import com.seroja.easystudyapi.dto.TaskPerformanceDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EdMaterialAndTaskPerformanceQueryDto {
    @NotNull
    Integer id;
    @NotNull
    String name;
    @NotNull
    LocalDate dateOfUpload;
    TaskPerformanceDto taskPerformanceDto;
}
