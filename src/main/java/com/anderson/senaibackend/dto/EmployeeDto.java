package com.anderson.senaibackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmployeeDto(Long id,
                         @NotBlank String firstName,
                         @NotBlank String lastName,
                         @NotBlank @Email String email,
                         @NotBlank String password,
                         @NotBlank String phoneNumber,
                         String typeEmployee){
}
