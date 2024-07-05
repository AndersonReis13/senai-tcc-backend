package com.anderson.senaibackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PhoneDto(Long id,
                       @NotBlank String brand,
                       @NotBlank String model,
                       @NotBlank String problemDescription,
                       @NotNull String phoneStatus){
}
