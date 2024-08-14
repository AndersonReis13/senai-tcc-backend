package com.anderson.senaibackend.controllers;

import com.anderson.senaibackend.domain.model.Product;
import com.anderson.senaibackend.dto.ProductDto;
import com.anderson.senaibackend.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "cos/product")
public class ProductControllers {

    public final ProductService productServices;

    public ProductControllers(ProductService productServices) {
        this.productServices = productServices;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(productServices.findById(id));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Product> create(@Valid @RequestBody ProductDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productServices.addProduct(dto));
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Product> update(@Valid @RequestBody ProductDto dto){
        return ResponseEntity.ok().body(productServices.modifyProduct(dto));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id")Long id){
        productServices.removeProduct(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
