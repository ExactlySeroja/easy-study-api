package com.seroja.easystudyapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateApplicationRequestDto {
    @NotNull
    Integer courseId;
}
