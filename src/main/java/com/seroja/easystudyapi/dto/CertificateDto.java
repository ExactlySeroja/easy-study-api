package com.seroja.easystudyapi.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.seroja.easystudyapi.entity.Certificate}
 */
@Data
public class CertificateDto implements Serializable {
    Integer id;
    @NotNull
    Integer applicationId;
    @NotNull
    LocalDate dateOfIssue;
    @NotNull
    Integer studentId;
}