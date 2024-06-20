package com.anderson.senaibackend.domain.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "product_status")
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

        public ProductStatus get(){
            return new ProductStatus(id, description);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductStatus that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
