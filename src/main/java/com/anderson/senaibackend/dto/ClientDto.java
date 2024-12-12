package com.anderson.senaibackend.dto;

import com.anderson.senaibackend.domain.model.Client;
import com.anderson.senaibackend.domain.model.OS;
import com.anderson.senaibackend.domain.model.Phone;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ClientDto(
                        Long id,
                        @NotBlank(message = "não pode ser vazio")
                        @JsonProperty(value = "first_name") String firstName,

                        @NotBlank(message = "não pode ser vazio")
                        @JsonProperty(value = "last_name") String lastName,

                        @NotBlank @Email(message = "insira um email válido")
                        @JsonProperty(value = "email") String email,

                        @NotBlank @Size(min = 11, max = 11, message = "O cpf tem que conter 11 digitos")
                        @JsonProperty("cpf") String cpf,

                        @NotBlank(message = "O endereço não pode ser vazio")
                        @JsonProperty(value = "address") String address,

                        @NotBlank @Size(min = 9, max = 11, message = "número de telefone invalido")
                        @JsonProperty(value = "phone_number") String phoneNumber,

                        @NotNull(message = "o id nao pode ser vazio")
                        @JsonProperty(value = "phone_id") Long phoneId,

                        @NotNull(message = "o id nao pode ser vazio")
                        @JsonProperty(value = "order_id") Long orderId){

    public Client toEntity(ClientDto dto, Phone phone, OS os){
        return new Client(
                dto.id(),
                dto.firstName(),
                dto.lastName(),
                dto.email(),
                dto.cpf(),
                dto.address(),
                dto.phoneNumber(),
                phone,
                os);
    }
}
