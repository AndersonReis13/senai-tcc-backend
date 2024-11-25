package com.anderson.senaibackend.domain.model;

import com.anderson.senaibackend.domain.model.enums.TypeEmployee;
import com.anderson.senaibackend.exceptions.BadRequestFoundException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Employee")
public class Employee implements Serializable, UserDetails {

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

    @Column(name = "password", length = 250)
    @JsonProperty(value = "password")
    private String password;

    @Column(name = "phone_number", length = 11)
    @JsonProperty(value = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "employee_status", nullable = false)
    private TypeEmployee employeeStatus;

    public Employee() {
    }

    public Employee(Long id, String firstName, String lastName, String email, String cpf, String password, String phoneNumber, TypeEmployee employeeStatus) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cpf = cpf;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.employeeStatus = employeeStatus;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if(this.employeeStatus == null) throw new BadRequestFoundException("O employee status não pode ser null");

        if(this.employeeStatus == TypeEmployee.GERENTE) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
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
        return employeeStatus;
    }

    public void setTypeEmployeeId(TypeEmployee typeEmployeeId) {
        this.employeeStatus = typeEmployeeId;
    }

    public String getCpf() {
        return cpf;
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
