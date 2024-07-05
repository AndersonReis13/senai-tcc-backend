package com.anderson.senaibackend.services;

import com.anderson.senaibackend.domain.model.Employee;
import com.anderson.senaibackend.domain.model.enums.TypeEmployee;
import com.anderson.senaibackend.domain.repositories.EmployeeRepository;
import com.anderson.senaibackend.dto.EmployeeDto;
import com.anderson.senaibackend.exceptions.BadRequestFoundException;
import com.anderson.senaibackend.exceptions.ResourceNotFoundException;
import com.anderson.senaibackend.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService{

    private final EmployeeRepository employeeRepository;


    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Employee findById(Long id){
        return employeeRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("funcionario não encontrado"));
    }

    public Employee create(EmployeeDto dto){

        checkFieldEmailInDataBase(dto.email());// verificando se ja existe um email cadastrado

        if(dto.typeEmployee() == null){
            throw new BadRequestFoundException("typeEmployee não pode ser null");
        }

        checkEnumInField(dto.typeEmployee()); // verificando se exisite o tipo selecionado

        return employeeRepository.save(EmployeeMapper.toEntity(dto));
    }

    public Employee updateEmployee(EmployeeDto dto){

        employeeRepository.findById(dto.id())
                .orElseThrow(()-> new ResourceNotFoundException("Funcionario não encontrado"));

        if(dto.typeEmployee() == null){
            throw new BadRequestFoundException("typeEmployee não pode ser null");
        }

        checkEnumInField(dto.typeEmployee());

        return employeeRepository.save(EmployeeMapper.toEntity(dto));
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

    public void checkEnumInField(String status){
        if(!TypeEmployee.existsEnum(status)){
            throw new BadRequestFoundException("Não existe este tipo de empregador");
        }
    }
}
