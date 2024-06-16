package com.anderson.generationos.domain.model;

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
    private LocalDate emissionDate = LocalDate.now();

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client clientId;

    @Column(name = "description", length = 250, nullable = false)
    private String description;

    @Column(name = "material", length = 250, nullable = false)
    private String material;

    @Column(name = "estimated_time", nullable = false)
    private LocalDateTime estimatedTime;
}
