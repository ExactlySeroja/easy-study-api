package com.seroja.easystudyapi.dto.query;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoryRequestDto {
    @NotNull
    Integer categoryId;
    @NotNull
    @Size(max = 250)
    String categoryName;

}
