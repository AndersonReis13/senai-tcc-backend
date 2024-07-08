package com.anderson.senaibackend.dto;

import com.anderson.senaibackend.domain.model.Phone;
import com.anderson.senaibackend.domain.model.enums.PhoneStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PhoneDto(Long id,
                       @NotBlank(message = "não pode ser vazio")
                       @JsonProperty(value = "brand") String brand,

                       @NotBlank(message = "não pode ser vazio")
                       @JsonProperty(value = "model") String model,

                       @NotNull(message = "não pode ser vazio")
                       @JsonProperty(value = "problem_description") String problemDescription,

                       @NotBlank(message = "não pode ser vazio")
                       @JsonProperty(value = "phone_status") String phoneStatus){
    public Phone toEntity(PhoneDto dto){
        return new Phone(
                dto.id(),
                dto.brand(),
                dto.model(),
                dto.problemDescription(),
                PhoneStatus.valueOf(dto.phoneStatus().toUpperCase())
        );
    }
}
