package com.anderson.senaibackend.domain.model;

import com.anderson.senaibackend.domain.model.enums.PhoneStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "phone")
public class Phone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand",length = 100)
    @JsonProperty(value = "brand")
    private String brand;

    @Column(name = "model", length = 100)
    @JsonProperty(value = "model")
    private String model;

    @Column(name = "problem_description")
    @JsonProperty(value = "problem_description")
    private String problemDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "phone_status", nullable = false)
    @JsonProperty(value = "phone_status")
    private PhoneStatus phoneStatus;

    public Phone() {
    }

    public Phone(Long id, String brand, String model, String problemDescription, PhoneStatus status) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.problemDescription = problemDescription;
        this.phoneStatus = status;
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PhoneStatus getStatus() {
        return phoneStatus;
    }

    public void setStatus(PhoneStatus status) {
        this.phoneStatus = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Phone phone)) return false;
        return Objects.equals(getId(), phone.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Phone{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", problemDescription='" + problemDescription + '\'' +
                ", status=" + phoneStatus +
                '}';
    }
}
