package com.anderson.generationos.domain.model;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fist_name",length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "email", length = 150)
    private String email;

    @Column(name = "cpf", length = 20)
    private String password;

    @Column(name = "phone_number", length = 11)
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "type_employee_id")
    private TypeEmployee typeEmployeeId;

    public Employee() {
    }

    public Employee(Long id, String firstName, String lastName, String email, String password, String phoneNumber, TypeEmployee typeEmployeeId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.typeEmployeeId = typeEmployeeId;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public TypeEmployee getTypeEmployeeId() {
        return typeEmployeeId;
    }

    public void setTypeEmployeeId(TypeEmployee typeEmployeeId) {
        this.typeEmployeeId = typeEmployeeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return Objects.equals(getId(), employee.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
