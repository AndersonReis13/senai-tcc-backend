package com.anderson.senaibackend.dto;

import com.anderson.senaibackend.domain.model.Employee;
import com.anderson.senaibackend.domain.model.enums.TypeEmployee;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmployeeDto(Long id,
                         @NotBlank(message = "não pode ser vazio")
                         @JsonProperty(value = "first_name") String firstName,

                         @NotBlank(message = "não pode ser vazio")
                          @JsonProperty(value = "last_name")String lastName,

                         @NotBlank @Email(message = "insira um email valido")
                          @JsonProperty(value = "email")String email,

                         @NotBlank(message = "não pode ser vazio")
                          @Size(min = 11, message = "o password tem que ter no minimo 11 digitos")
                          @JsonProperty(value = "password") String password,

                         @NotBlank(message = "não pode ser vazio")
                          @Size(min = 11, max = 11, message = "O número contém 11 digitos")
                          @JsonProperty(value = "phone_number") String phoneNumber,

                          @NotBlank(message = "não pode ser vazio")
                          @JsonProperty(value = "type_employee") String typeEmployee){

    public Employee toEntity(EmployeeDto dto){
        return new Employee(
                dto.id(),
                dto.firstName(),
                dto.lastName(),
                dto.email(),
                dto.password(),
                dto.phoneNumber(),
                TypeEmployee.valueOf(dto.typeEmployee().toUpperCase())
        );
    }
}
