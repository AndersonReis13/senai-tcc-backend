package com.anderson.senaibackend.dto;

import com.anderson.senaibackend.domain.model.Client;
import com.anderson.senaibackend.domain.model.OS;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;


public record OSDto(Long clientId,
                   @NotBlank(message = "não pode ser vazio") String description,
                   @NotBlank(message = "não pode ser vazio") String material,
                   @JsonFormat(pattern = "yyyy-MM-dd") LocalDate estimatedTime){

    public OS toEntity(OSDto dto, Client client){
        return new OS(
                dto.clientId(),
                LocalDate.now(),
                client,
                dto.description(),
                dto.material(),
                dto.estimatedTime()
        );
    }
}
