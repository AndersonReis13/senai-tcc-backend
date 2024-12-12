package com.anderson.senaibackend.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(value = "first_name")
    private String firstName;

    @Column(name = "last_name", length = 100)
    @JsonProperty(value = "last_name")
    private String lastName;

    @Column(name = "email", length = 150, unique = true)
    @JsonProperty(value = "email")
    private String email;

    @Column(name = "cpf", length = 11, unique = true)
    @JsonProperty(value = "cpf")
    private String cpf;

    @Column(name = "address", length = 200)
    @JsonProperty(value = "address")
    private String address;

    @Column(name = "phone_number", length = 11)
    @JsonProperty(value = "phone_number")
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "phone_id")
    @JsonProperty(value = "phone_id")
    private Phone phoneId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonProperty(value = "order_id")
    private OS orderId;

    public Client(){
    }


    public Client(Long id, String firstName, String lastName, String email, String cpf, String address, String phoneNumber, Phone phoneId, OS osId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cpf = cpf;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.phoneId = phoneId;
        this.orderId = osId;
    }

    public Long getId() {
        return id;
    }

    public OS getOrderId() {
        return orderId;
    }

    public void setOrderId(OS orderId) {
        this.orderId = orderId;
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
