package com.anderson.senaibackend.dto;

import com.anderson.senaibackend.domain.model.Employee;
import com.anderson.senaibackend.domain.model.enums.TypeEmployee;

public record EmployeeRegisterDto(String email,
                                  String password,
                                  String typeEmployee) {
}
