package com.anderson.senaibackend.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;

public class ProductStatus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status", nullable = false)
    private String status;

    public ProductStatus() {
    }

    public ProductStatus(Long id, String status) {
        this.id = id;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public enum Enum{
        PENDENDTE(1L, "pendente"),
        CONCLUIDO(2L, "concluido");

        Enum(Long id, String description){
            this.id = id;
            this.description = description;
        }

        private Long id;
        private String description;

        ProductStatus get(){
            return new ProductStatus(id, description);
        }

    }


}
