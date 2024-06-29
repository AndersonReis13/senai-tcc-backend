package com.anderson.senaibackend.dto;

import java.math.BigDecimal;

public record ProductDto(Long id,
                         String name,
                         String brand,
                         BigDecimal price,
                         Integer quantity,
                         Long status) {
}
