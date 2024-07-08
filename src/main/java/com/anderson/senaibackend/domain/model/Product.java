package com.anderson.senaibackend.domain.model;

import com.anderson.senaibackend.domain.model.enums.ProductStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "product")
public class Product implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 150, nullable = false)
    @JsonProperty(value = "name")
    private String name;

    @Column(name = "brand", length= 150,nullable = false)
    @JsonProperty(value = "brand")
    private String brand;

    @Column(name = "price", nullable = false)
    @JsonProperty(value = "price")
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    @JsonProperty(value = "quantity")
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_status", nullable = false)
    @JsonProperty(value = "status")
    private ProductStatus status;

    public Product() {
    }

    public Product(Long id, String name, String brand, BigDecimal price, Integer quantity, ProductStatus status) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
