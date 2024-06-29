package com.anderson.senaibackend.mapper;

import com.anderson.senaibackend.domain.model.Product;
import com.anderson.senaibackend.domain.model.ProductStatus;
import com.anderson.senaibackend.dto.ProductDto;

public class ProductMapper {
    public static Product toEntity(ProductDto dto, ProductStatus status){
        return new Product(dto.id(),
                dto.name(),
                dto.brand(),
                dto.price(),
                dto.quantity(),
                type);

    }
}
