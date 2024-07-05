package com.anderson.senaibackend.mapper;

import com.anderson.senaibackend.domain.model.Product;
import com.anderson.senaibackend.domain.model.enums.ProductStatus;
import com.anderson.senaibackend.dto.ProductDto;

public class ProductMapper {
    public static Product toEntity(ProductDto dto){
        return new Product(dto.id(),
                dto.name(),
                dto.brand(),
                dto.price(),
                dto.quantity(),
                ProductStatus.valueOf(dto.productStatus().toUpperCase()));
    }
}
