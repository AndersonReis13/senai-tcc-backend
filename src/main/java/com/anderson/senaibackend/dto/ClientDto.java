package com.anderson.senaibackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClientDto(
                        Long id,
                        @NotBlank String firstName,
                        @NotBlank String lastName,
                        @NotBlank String email,
                        @NotBlank String cpf,
                        @NotBlank String address,
                        @NotBlank String phoneNumber,
                        @NotNull Long phoneId){
}
