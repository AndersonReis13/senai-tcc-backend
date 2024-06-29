package com.anderson.senaibackend.controllers;

import com.anderson.senaibackend.domain.model.Product;
import com.anderson.senaibackend.services.ProductServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ProductControllers {

    public final ProductServices productServices;

    public ProductControllers(ProductServices productServices) {
        this.productServices = productServices;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Product> findById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(productServices.findById(id));
    }
}
