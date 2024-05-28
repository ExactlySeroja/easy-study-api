package com.seroja.easystudyapi.dto.query;

import java.time.LocalDate;

public interface EdMaterialAndTaskPerformanceProjection {
    Integer getId();

    String getName();

    LocalDate getDateOfUpload();

    Integer getTaskPerformanceDtoId();

    Integer getTaskPerformanceDtoEdMaterialId();

    Integer getTaskPerformanceDtoStudentId();

    LocalDate getTaskPerformanceDtoDateOfCompletion();

    String getTaskPerformanceDtoAnswer();

    Integer getTaskPerformanceDtoGrade();
}
