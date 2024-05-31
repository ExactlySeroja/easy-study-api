package com.seroja.easystudyapi.dto.query;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EducationalMaterialWithTaskPerformanceDto {
    private Integer materialId;
    private String materialName;
    private String content;
    private LocalDate dateOfUpload;
    private Integer taskPerformanceId;
    private LocalDate dateOfCompletion;
    private String answer;
    private Integer grade;

    public EducationalMaterialWithTaskPerformanceDto(Integer materialId, String materialName, String content, LocalDate dateOfUpload,
                                                     Integer taskPerformanceId, LocalDate dateOfCompletion, String answer, Integer grade) {
        this.materialId = materialId;
        this.materialName = materialName;
        this.content = content;
        this.dateOfUpload = dateOfUpload;
        this.taskPerformanceId = taskPerformanceId;
        this.dateOfCompletion = dateOfCompletion;
        this.answer = answer;
        this.grade = grade;
    }
}