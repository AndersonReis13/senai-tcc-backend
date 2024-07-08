package com.anderson.senaibackend.services;

import com.anderson.senaibackend.domain.model.Product;
import com.anderson.senaibackend.domain.repositories.ProductRepository;
import com.anderson.senaibackend.dto.ProductDto;
import com.anderson.senaibackend.exceptions.BadRequestFoundException;
import com.anderson.senaibackend.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("produto não encontrado!"));
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product addProduct(ProductDto dto){

        if(dto.id() != null){
            throw new BadRequestFoundException("O id do client tem que ser null");
        }

        if(dto.quantity() <= 0){
            throw new BadRequestFoundException("Insira uma quantidade maior que 0");
        }

        return productRepository.save(dto.toEntity(dto));
    }

    public Product modifyProduct(ProductDto dto){
        return productRepository.save(dto.toEntity(dto));
    }

    public void removeProduct(Long id){
        var productDb = productRepository.findById(id)
                        .orElseThrow(()-> new ResourceNotFoundException("produto não encontrado"));
        productRepository.delete(productDb);
    }
}
