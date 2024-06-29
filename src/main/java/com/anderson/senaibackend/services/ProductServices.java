package com.anderson.senaibackend.services;

import com.anderson.senaibackend.domain.model.Product;
import com.anderson.senaibackend.domain.repositories.ProductRepository;
import com.anderson.senaibackend.domain.repositories.ProductStatusRepository;
import com.anderson.senaibackend.dto.ProductDto;
import com.anderson.senaibackend.exceptions.ResourceNotFoundException;

import java.util.List;

public class ProductServices {

    private final ProductRepository productRepository;
    private final ProductStatusRepository productStatusRepository;

    public ProductServices(ProductRepository productRepository, ProductStatusRepository productStatusRepository) {
        this.productRepository = productRepository;
        this.productStatusRepository = productStatusRepository;
    }


    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("produto não encontrado!"));
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product create(ProductDto dto){
        // produto cadastrado terá que entrar como em "estoque"

        return null;
    }

    public Product update(ProductDto dto){
       // Verificar a quantidade e fazer automaticamente a conversão em estoque ou finalizado

        return null;
    }

    public void delete(Long id){

        var productDb = productRepository.findById(id)
                        .orElseThrow(()-> new ResourceNotFoundException("produto não encontrado"));

        productRepository.delete(productDb);
    }
}
