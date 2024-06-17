package com.anderson.senaibackend.domain.model;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "employee_type")
public class TypeEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    public TypeEmployee(){
    }

    public TypeEmployee(Long id, String description) {
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
        ATENDENTE(1L, "atendente"),
        GERENTE(2L, "gerente"),
        TECNICO(3L, "tecnico");

        Enum(Long id, String description){
            this.id = id;
            this.description = description;
        }

        private Long id;

        private String description;

        public TypeEmployee get(){
            return new TypeEmployee(id, description);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TypeEmployee that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
