package com.anderson.senaibackend.dto;

import com.anderson.senaibackend.domain.model.Client;
import com.anderson.senaibackend.domain.model.OS;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;


public record OSDto(
                    Long id,
                   @NotBlank(message = "não pode ser vazio") String description,
                   @NotBlank(message = "não pode ser vazio") String material,
                    @JsonFormat(pattern = "dd/MM/yyyy") LocalDate estimatedTime){

    public OS toEntity(OSDto dto){
        return new OS(
                dto.id(),
                LocalDate.now(),
                dto.description(),
                dto.material(),
                dto.estimatedTime()
        );
    }
}
