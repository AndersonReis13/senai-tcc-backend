package com.anderson.senaibackend.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private LocalDateTime estimatedTime;
}
