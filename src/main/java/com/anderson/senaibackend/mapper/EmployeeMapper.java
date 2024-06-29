package com.anderson.senaibackend.mapper;

import com.anderson.senaibackend.domain.model.Employee;
import com.anderson.senaibackend.domain.model.TypeEmployee;
import com.anderson.senaibackend.dto.EmployeeDto;

public class EmployeeMapper {

    public static Employee toEntity(EmployeeDto dto, TypeEmployee typeEmployee){
       return new Employee(
                dto.id(),
                dto.firstName(),
                dto.lastName(),
                dto.email(),
                dto.password(),
                dto.phoneNumber(),
                typeEmployee
        );
    }
}
