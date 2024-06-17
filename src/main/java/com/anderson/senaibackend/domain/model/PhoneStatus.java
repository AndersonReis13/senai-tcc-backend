package com.anderson.senaibackend.domain.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "phone_status")
public class PhoneStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status", length = 150)
    private String description;

    public PhoneStatus() {
    }

    public PhoneStatus(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public enum Enum{
        PENDENTE(1L, "pendente"),
        CONCLUIDO(2L, "description");

        Enum(Long id, String description){
            this.id = id;
            this.description = description;
        }

        private Long id;
        private String description;

        public PhoneStatus get(){
            return new PhoneStatus(id, description);
        }


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhoneStatus that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
