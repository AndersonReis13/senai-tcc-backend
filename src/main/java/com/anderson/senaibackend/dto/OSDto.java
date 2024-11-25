package com.anderson.senaibackend.dto;

import java.time.LocalDateTime;

public record OSDto(Long clientId,
                    String description,
                    String material,
                    LocalDateTime estimatedTime){
}
