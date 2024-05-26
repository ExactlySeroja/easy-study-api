package com.seroja.easystudyapi.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.seroja.easystudyapi.entity.EducationalMaterial}
 */
@Data
public class EducationalMaterialDto implements Serializable {
    Integer id;
    @NotNull
    @Max(250)
    private String name;
    @NotNull
    Integer themeId;
    @NotNull
    String content;
    @NotNull
    LocalDate dateOfUpload;
}