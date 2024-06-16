package com.anderson.generationos.domain.model;

import jakarta.persistence.*;
import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_phone")
public class Phone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand",length = 100)
    private String brand;

    @Column(name = "model", length = 100)
    private String model;

    @Column(name = "problem_description")
    private String problemDescription;

    @OneToOne
    @JoinColumn(name = "status")
    private PhoneStatus status;

    public Phone() {
    }

    public Phone(Long id, String brand, String model, String problemDescription, PhoneStatus status) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.problemDescription = problemDescription;
        this.status = status;
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
        return status;
    }

    public void setStatus(PhoneStatus status) {
        this.status = status;
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
}
