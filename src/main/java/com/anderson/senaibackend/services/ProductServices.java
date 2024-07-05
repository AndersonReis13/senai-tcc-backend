package com.anderson.senaibackend.services;

import com.anderson.senaibackend.domain.model.Product;
import com.anderson.senaibackend.domain.model.enums.ProductStatus;
import com.anderson.senaibackend.domain.repositories.ProductRepository;
import com.anderson.senaibackend.dto.ProductDto;
import com.anderson.senaibackend.exceptions.BadRequestFoundException;
import com.anderson.senaibackend.exceptions.ResourceNotFoundException;
import com.anderson.senaibackend.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices {

    private final ProductRepository productRepository;

    public ProductServices(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("produto não encontrado!"));
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product create(ProductDto dto){
        if(dto.quantity() <= 0){
            throw new BadRequestFoundException("Insira uma quantidade maior que 0");
        }

        return productRepository.save(ProductMapper.toEntity(dto));
    }

    public Product update(ProductDto dto){
        return productRepository.save(ProductMapper.toEntity(dto));
    }

    public void delete(Long id){
        var productDb = productRepository.findById(id)
                        .orElseThrow(()-> new ResourceNotFoundException("produto não encontrado"));
        productRepository.delete(productDb);
    }
}
