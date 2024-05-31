package com.seroja.easystudyapi.dto.query;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskPerformanceDetailsDto {
    private Integer taskId;
    private String educationalMaterialName;
    private LocalDate dateOfCompletion;
    private String answer;
    private Integer grade;
    private Integer studentId;

    public TaskPerformanceDetailsDto(Integer taskId, String educationalMaterialName, LocalDate dateOfCompletion, String answer, Integer grade, Integer studentId) {
        this.taskId = taskId;
        this.educationalMaterialName = educationalMaterialName;
        this.dateOfCompletion = dateOfCompletion;
        this.answer = answer;
        this.grade = grade;
        this.studentId = studentId;
    }
}