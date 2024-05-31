package com.seroja.easystudyapi.dto.query;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StudentTaskPerformanceDto {
    private Integer studentId;
    private String studentName;
    private List<TaskPerformanceDetailsDto> taskPerformances;

    public StudentTaskPerformanceDto(Integer studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.taskPerformances = new ArrayList<>();
    }

    public void addTaskPerformance(TaskPerformanceDetailsDto taskPerformanceDetailsDto) {
        this.taskPerformances.add(taskPerformanceDetailsDto);
    }
}