package com.seroja.easystudyapi.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link com.seroja.easystudyapi.entity.Theme}
 */
@Data
public class ThemeDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 250)
    String themeName;
    @NotNull
    Integer courseId;
}