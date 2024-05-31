package com.seroja.easystudyapi.dto.query;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    @NotNull
    Integer id;
    @NotNull
    @Size(max = 250)
    String fullName;
    @NotNull
    @Size(max = 250)
    String username;
    @NotNull
    @Past
    LocalDate dateOfBirth;
    @NotNull
    @Pattern(regexp = "(\\+380|0)[0-9]{9}")
    @Size(max = 15)
    String phoneNumber;
    @NotNull
    @Email
    @Size(max = 250)
    String email;
}
