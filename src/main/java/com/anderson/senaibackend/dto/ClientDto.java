package com.anderson.senaibackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ClientDto(
                        Long id,
                        @NotBlank String firstName,
                        @NotBlank String lastName,
                        @NotBlank @Email String email,
                        @NotBlank @Size(min = 11, max = 11, message = "O cpf tem que conter dois digitos") String cpf,
                        @NotBlank String address,
                        @NotBlank @Size(min = 9, max = 11, message = "número de telefone invalido") String phoneNumber,
                        @NotNull Long phoneId){
}
