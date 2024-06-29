package com.anderson.senaibackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmployeeDto(Long id,
                         @NotBlank String firstName,
                         @NotBlank String lastName,
                         @NotBlank @Email String email,
                         @NotBlank String password,
                         @NotBlank String phoneNumber,
                         @NotNull Long typeEmployeeId) {
}
