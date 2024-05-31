package com.seroja.easystudyapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @Size(max = 250)
    private String name;
    @NotNull
    Integer themeId;
    @NotNull
    String content;
    @NotNull
    LocalDate dateOfUpload;
}