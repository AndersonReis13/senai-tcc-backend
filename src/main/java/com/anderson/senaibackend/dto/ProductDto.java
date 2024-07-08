package com.anderson.senaibackend.dto;

import com.anderson.senaibackend.domain.model.Product;
import com.anderson.senaibackend.domain.model.enums.ProductStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductDto(Long id,
                         @NotBlank(message = "não pode ser vazio")
                         @JsonProperty(value = "name") String name,

                         @NotBlank(message = "não pode ser vazio")
                         @JsonProperty(value = "brand") String brand,

                         @PositiveOrZero(message = "o valor tem que ser positivo ou 0")
                         @JsonProperty(value = "price") BigDecimal price,

                         @NotNull(message = "o valor não pode ser vazio")
                         @JsonProperty(value = "quantity") Integer quantity,

                         @NotBlank(message = "não pode ser vazio")
                         String productStatus) {
    public Product toEntity(ProductDto dto){
        return new Product(dto.id(),
                dto.name(),
                dto.brand(),
                dto.price(),
                dto.quantity(),
                ProductStatus.valueOf(dto.productStatus().toUpperCase()));
    }
}
