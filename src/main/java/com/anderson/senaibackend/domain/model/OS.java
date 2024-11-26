package com.anderson.senaibackend.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "order_of_service")
public class OS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "emission_date")
    @JsonProperty(value = "emission_date")
    private LocalDate emissionDate = LocalDate.now();

    @OneToOne
    @JoinColumn(name = "client_id")
    @JsonProperty(value = "client_id")
    private Client clientId;

    @Column(name = "description", length = 250, nullable = false)
    @JsonProperty(value = "description")
    private String description;

    @Column(name = "material", length = 250, nullable = false)
    @JsonProperty(value = "material")
    private String material;

    @Column(name = "estimated_time", nullable = false)
    @JsonProperty(value = "estimated_time")
    private LocalDate estimatedTime;

    public OS() {
    }

    public OS(Long id, LocalDate emissionDate, Client clientId, String description, String material, LocalDate estimatedTime) {
        this.id = id;
        this.emissionDate = emissionDate;
        this.clientId = clientId;
        this.description = description;
        this.material = material;
        this.estimatedTime = estimatedTime;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getEmissionDate() {
        return emissionDate;
    }

    public void setEmissionDate(LocalDate emissionDate) {
        this.emissionDate = emissionDate;
    }

    public Client getClientId() {
        return clientId;
    }

    public void setClientId(Client clientId) {
        this.clientId = clientId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public LocalDate getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(LocalDate estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OS os)) return false;
        return Objects.equals(getId(), os.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
