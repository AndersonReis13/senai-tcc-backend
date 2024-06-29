package com.anderson.senaibackend.services;

import com.anderson.senaibackend.domain.model.Employee;
import com.anderson.senaibackend.domain.model.TypeEmployee;
import com.anderson.senaibackend.domain.repositories.EmployeeRepository;
import com.anderson.senaibackend.domain.repositories.TypeEmployeeRepository;
import com.anderson.senaibackend.dto.EmployeeDto;
import com.anderson.senaibackend.exceptions.BadRequestFoundException;
import com.anderson.senaibackend.exceptions.ResourceNotFoundException;
import com.anderson.senaibackend.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService{

    private final EmployeeRepository employeeRepository;
    private final TypeEmployeeRepository typeEmployeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository, TypeEmployeeRepository typeEmployeeRepository) {
        this.employeeRepository = employeeRepository;
        this.typeEmployeeRepository = typeEmployeeRepository;
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Employee findById(Long id){
        return employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("funcionario não encontrado"));
    }

    public Employee create(EmployeeDto dto){
        var employeeStatusDb = typeEmployeeRepository.findById(dto.typeEmployeeId())
                .orElseThrow(()-> new ResourceNotFoundException("tipo de empregador não encontrado"));
        checkFieldEmailInDataBase(dto.email());

        TypeEmployee typeEmployee = employeeStatusDb;

        // apenas gerente poderá realizar o cadastro de novos funcionarios

        return employeeRepository.save(EmployeeMapper.toEntity(dto, typeEmployee));
    }

    public Employee updateEmployee(EmployeeDto dto){
        var employeeStatusDb = typeEmployeeRepository.findById(dto.typeEmployeeId())
                .orElseThrow(()-> new ResourceNotFoundException("tipo de empregador não encontrado"));

        TypeEmployee typeEmployee = employeeStatusDb;

        return employeeRepository.save(EmployeeMapper.toEntity(dto, typeEmployee));
    }

    public void deleteEmployee(Long id){
        var EmployeeDb = employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Funcionario não encontrado"));

        Employee employee = EmployeeDb;

        employeeRepository.delete(employee);
    }

    public void checkFieldEmailInDataBase(String email){
        if(employeeRepository.existsByEmail(email)){
            throw new BadRequestFoundException("Ja existe um email como esse cadastrado no sistema");
        }
    }

}
