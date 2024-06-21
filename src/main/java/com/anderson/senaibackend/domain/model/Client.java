package com.anderson.senaibackend.domain.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "client")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fist_name",length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "email", length = 150, unique = true)
    private String email;

    @Column(name = "cpf", length = 11, unique = true)
    private String cpf;

    @Column(name = "address", length = 200)
    private String address;

    @Column(name = "phone_number", length = 11)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "phone_id")
    private Phone phoneId;

    public Client(){
    }


    public Client(Long id, String firstName, String lastName, String email, String cpf, String address, String phoneNumber, Phone phoneId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cpf = cpf;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.phoneId = phoneId;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Phone getPhoneId() {
        return phoneId;
    }

    public void setPhoneId(Phone phoneId) {
        this.phoneId = phoneId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        return Objects.equals(getId(), client.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
