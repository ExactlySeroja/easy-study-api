package com.seroja.easystudyapi.dto;

import com.seroja.easystudyapi.entity.AppUser;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

/**
 * DTO for {@link AppUser}
 */
@Data
public class UserDto implements Serializable {
    Integer id;
    @NotNull
    @Size(max = 250)
    String fullName;
    @NotNull
    @Size(max = 250)
    String username;
    @NotNull
    @Size(max = 250)
    String password;
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
    @NotNull
    @Size(max = 250)
    Set<String> role;
}