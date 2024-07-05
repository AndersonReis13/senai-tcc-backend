package com.anderson.senaibackend.controllers;

import com.anderson.senaibackend.domain.model.Product;
import com.anderson.senaibackend.dto.ProductDto;
import com.anderson.senaibackend.services.ProductServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "cos/product")
public class ProductControllers {

    public final ProductServices productServices;

    public ProductControllers(ProductServices productServices) {
        this.productServices = productServices;
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Product> findById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok().body(productServices.findById(id));
    }

    @PostMapping(value = "create")
    public ResponseEntity<Product> create(@RequestBody ProductDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productServices.create(dto));
    }

    @PutMapping(value = "update")
    public ResponseEntity<Product> update(@RequestBody ProductDto dto){
        return ResponseEntity.ok().body(productServices.update(dto));
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id")Long id){
        productServices.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
